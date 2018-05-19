package service;


import Framework.IteratorPattern.ConcreteIterator;
import Framework.IteratorPattern.Iterator;
import Framework.SingletonPattern.Singleton;
import Framework.TemplateMethodPattern.AbstractAuctionTemplate;

import model.User;

import model.Auction;
import model.Bid;
import model.Product;
import model.Report.AuctionReport;
import org.springframework.stereotype.Service;

import utils.Utils;

import java.sql.*;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

@Service("auctionService")
public class AuctionServiceImpl extends AbstractAuctionTemplate implements AuctionService {

    private Connection singletonDbConnection = Singleton.INSTANCE.getConnection();

    public static void main(String arg[]) throws ParseException {

      /*  TraceFactory traceFactory  = ConcreteTraceFactory.getFactory();
        Trace t = traceFactory.getTracer(TraceValue.TRACE.name().toLowerCase() + ".log");
        t.debug("uuuu");
        */
        Utils.logEvent("Testing Lukman");

        AuctionServiceImpl a = new AuctionServiceImpl();

        List<Auction> auctions = new ArrayList<>();
        Auction ab = new Auction();
        ab.setStartDate(Utils.sqlCurrentDate());
        ab.setEndDate(Utils.sqlCurrentDate());
        ab.setMinimumPrice(200.0);

        Auction aa = new Auction();
        aa.setStartDate(Utils.sqlCurrentDate());
        aa.setEndDate(Utils.sqlCurrentDate());
        aa.setMinimumPrice(300.0);
        auctions.add(ab);
        auctions.add(aa);

        a.calculateWinningBidTest(auctions);





        String str3 = "05/17/2019";
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat sdfmt2 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dDate = sdfmt1.parse(str3);
        String strOutput = sdfmt2.format(dDate);

        String str = "2015-03-31";
        String str2 = "17/05/2019";
        Date date = Date.valueOf(str);//converting string into sql date
        //Date date2=Date.valueOf(str2);//converting string into sql date
        System.out.println(date);
        System.out.println(strOutput);


        List<Auction> ls = a.selectRunning();
        for (Auction ac : ls) {
            System.out.println("-------->" + ac.getId() + " " + ac.getStartDate());
        }

        Auction auction = a.selectById(1);
        System.out.println("-------->" + auction.getId() + " " + auction.bidOwnerUser.getLastName());
        System.out.println("-------->" + auction.getId() + " " + auction.current_product.getName());

        a.processCurrentWinningBid(a.currentWinningBid());


        List<Auction> auctionFinal = a.processAuction();
        ConcreteIterator auctionList = new ConcreteIterator(auctionFinal);
        Iterator auctionIterator = auctionList.getIterator();

        while (auctionIterator.hasNext()) {

            Auction n = (Auction) auctionIterator.next();
            System.out.println("Auction Id:" + n.getId() + ",EndDate:" + n.getEndDate() + ",Winner:" + n.getWinner() + ",CurrentWinningBid:" + n.getCurrentWinningBid());

        }


        //ConcreteIterator auctionList = new ConcreteIterator(getLists);
        //Iterator auctionIterator = auctionList.getIterator();

        // while (auctionIterator.hasNext()) {

        //  AuctionReport n = (AuctionReport)auctionIterator.next();
        // System.out.println(n.getBidOwner() + "  " + n.getBidUser());
        // }
        // System.out.println("winningBid start:" );
        //double winningBid = a.calculateCurrentWinningBid(getLists);
        //System.out.println("winningBid:" + winningBid);
        //System.out.println("winningBid end:" );

    }


    @Override
    public List<Auction> selectRunning() {
        List<Auction> auctions = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = singletonDbConnection;
            preparedStatement = connection.prepareStatement("select a.* , p.id as pid , p.name , p.desc from auction a inner join product p on a.product = p.id where isrunning = 1");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Auction auction = new Auction();
                auction.setId(resultSet.getInt("id"));
                auction.setMinimumPrice(resultSet.getDouble("minimumPrice"));
                auction.setStartDate(resultSet.getDate("startDate"));
                auction.setEndDate(resultSet.getDate("endDate"));
                auction.setRunning(resultSet.getBoolean("isrunning"));
                auction.setProduct(resultSet.getInt("product"));
                auction.setCurrentWinner(resultSet.getInt("currentWinner"));
                auction.setCurrentWinningBid(resultSet.getInt("currentWinningBid"));
                auction.setWinner(resultSet.getInt("winner"));
                auction.current_product = new Product();
                auction.current_product.setId(resultSet.getInt("pid"));
                auction.current_product.setName(resultSet.getString("name"));
                auction.current_product.setDesc(resultSet.getString("desc"));
                auctions.add(auction);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return auctions;
    }

    @Override
    public Auction selectById(int id) {
        Auction auction = new Auction();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = singletonDbConnection;
            preparedStatement = connection.prepareStatement("select a.* , p.id as pid , p.name , p.desc,first_name,last_name from auction a" +
                    " inner join product p on a.product = p.id inner join user u on u.user_id= a.bidOwner " +
                    " WHERE a.id = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                auction.setId(resultSet.getInt("id"));
                auction.setMinimumPrice(resultSet.getDouble("minimumPrice"));
                auction.setStartDate(resultSet.getDate("startDate"));
                auction.setEndDate(resultSet.getDate("endDate"));
                auction.setRunning(resultSet.getBoolean("isrunning"));
                auction.setProduct(resultSet.getInt("product"));
                auction.setCurrentWinner(resultSet.getInt("currentWinner"));
                auction.setCurrentWinningBid(resultSet.getInt("currentWinningBid"));
                auction.setWinner(resultSet.getInt("winner"));
                auction.current_product = new Product();
                auction.current_product.setId(resultSet.getInt("pid"));
                auction.current_product.setName(resultSet.getString("name"));
                auction.current_product.setDesc(resultSet.getString("desc"));
                auction.bidOwnerUser = new User();
                auction.bidOwnerUser.setFirstName(resultSet.getString("first_name"));
                auction.bidOwnerUser.setLastName(resultSet.getString("last_name"));
                auction.setStatus(Utils.status(resultSet.getBoolean("isrunning")));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return auction;
    }

    @Override
    public List<Auction> selectAll() {

        List<Auction> auctions = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = singletonDbConnection;
            preparedStatement = connection.prepareStatement("select a.* , p.id as pid , p.name , p.desc,first_name,last_name " +
                    " from auction a inner join product p on a.product = p.id inner join user u on u.user_id= a.bidOwner ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Auction auction = new Auction();

                auction.current_product = new Product();
                auction.current_product.setId(resultSet.getInt("pid"));
                auction.current_product.setName(resultSet.getString("name"));
                auction.current_product.setDesc(resultSet.getString("desc"));
                auction.setStatus(Utils.status(resultSet.getBoolean("isrunning")));

                auction.setId(resultSet.getInt("id"));
                auction.setMinimumPrice(resultSet.getDouble("minimumPrice"));
                auction.setStartDate(resultSet.getDate("startDate"));
                auction.setEndDate(resultSet.getDate("endDate"));
                auction.setRunning(resultSet.getBoolean("isrunning"));
                auction.setProduct(resultSet.getInt("product"));
                auction.setCurrentWinner(resultSet.getInt("currentWinner"));
                auction.setCurrentWinningBid(resultSet.getInt("currentWinningBid"));
                auction.setWinner(resultSet.getInt("winner"));


                auction.bidOwnerUser = new User();
                auction.bidOwnerUser.setFirstName(resultSet.getString("first_name"));
                auction.bidOwnerUser.setLastName(resultSet.getString("last_name"));
                auctions.add(auction);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return auctions;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = singletonDbConnection;
            preparedStatement = connection.prepareStatement("DELETE FROM auction WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM auction WHERE id = ?");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void create(Auction auction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = singletonDbConnection;
            preparedStatement = connection.
                    prepareStatement("INSERT INTO auction (startDate, endDate, minimumPrice, bidOwner,isRunning,product) "
                            + " VALUES (?, ?, ?, ?, ?,?)");

            preparedStatement.setDate(1, auction.getStartDate());
            preparedStatement.setDate(2, auction.getEndDate());
            preparedStatement.setDouble(3, auction.getMinimumPrice());
            preparedStatement.setLong(4, auction.getBidOwner());

            // preparedStatement.setBoolean(5, auction.isRunning());//TODO this should be default
            preparedStatement.setBoolean(5, true);//TODO this should be default
            preparedStatement.setLong(6, auction.getProduct());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Auction auction, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = singletonDbConnection;
            preparedStatement = connection
                    .prepareStatement("UPDATE auction SET " + "startDate = ?, endDate = ?, minimumPrice = ?, bidOwner=? " +
                            ", isRunning= ?,product = ? WHERE id = ?");

            preparedStatement.setDate(1, auction.getStartDate());
            preparedStatement.setDate(2, auction.getEndDate());
            preparedStatement.setDouble(3, auction.getMinimumPrice());
            preparedStatement.setLong(4, auction.getBidOwner());

            preparedStatement.setBoolean(5, auction.isRunning());
            preparedStatement.setLong(6, auction.getProduct());

            preparedStatement.setInt(7, id);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    Utils.logEvent(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Utils.logEvent(e.getMessage());
                }
            }
        }
    }

    @Override
    public List<AuctionReport> selectAuctionReport(Date startDate, Date endDate, boolean isRunning) {

        List<AuctionReport> auctions = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = singletonDbConnection;
            preparedStatement = connection.prepareStatement("select " +
                    "b.id bidId," +
                    "a.id auctionId,startDate,endDate,bidDate,b.amount bidAmount,a.minimumPrice," +
                    " case  when isRunning = 1 then 'Open' else 'Closed'  end isRunning," +
                    "(select name from product where id = a.product ) product," +
                    " currentWinningBid," +
                    " winner," +
                    "(select concat (first_name ,' ' , last_name) from user where id = b.user ) bidUser," +
                    "(select concat (first_name ,' ' , last_name) from user where id = a.currentWinner) currentWinner," +
                    "(select concat (first_name ,' ' , last_name) from user where id = a.bidOwner ) bidOwner " +
                    "from auction a, bid b" +
                    " where  a.id = b.auction" +
                    " and a.startDate >= ? and a.endDate <= ?" +
                    " and a.isRunning = ?; ");

            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            preparedStatement.setBoolean(3, isRunning);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AuctionReport auction = new AuctionReport();

                auction.setAuctionId(resultSet.getInt("auctionId"));
                auction.setBidId(resultSet.getInt("bidId"));

                auction.setStartDate(resultSet.getDate("startDate"));
                auction.setEndDate(resultSet.getDate("endDate"));
                auction.setIsRunning(resultSet.getString("isRunning"));

                auction.setMinimumPrice(resultSet.getBigDecimal("minimumPrice"));
                auction.setCurrentWinner(resultSet.getString("currentWinner"));
                auction.setCurrentWinningBid(resultSet.getString("currentWinningBid"));
                auction.setWinner(resultSet.getLong("winner"));
                auction.setBidAmount(resultSet.getDouble("bidAmount"));
                auction.setProduct(resultSet.getString("product"));
                auction.setBidUser(resultSet.getString("bidUser"));

                auction.setBidDate(resultSet.getDate("bidDate"));

                auction.setBidOwner(resultSet.getString("bidOwner"));
                // auction.bidWinnerUser = new User();



                auctions.add(auction);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return auctions;
        //return  null;
    }

    @Override
    public List<Bid> currentWinningBid() {
        List<Bid> bids = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = singletonDbConnection;
            preparedStatement = connection.prepareStatement("SELECT b.id,b.user,b.auction,b.amount,b.bidDate, a.endDate " +
                    " FROM bid b,auction a " +
                    " WHERE amount=(SELECT MAX(amount) " +
                    "    FROM bid WHERE auction=b.auction) " +
                    " and a.id = b.auction and  a.isRunning = 1");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Bid bid = new Bid();
                bid.setUser(resultSet.getLong("user"));

                bid.setAmount(resultSet.getDouble("amount"));
                bid.setBidDate(resultSet.getDate("bidDate"));
                bid.setId(resultSet.getLong("id"));

                bid.setAuction(resultSet.getLong("auction"));
                bid.setEndDate(resultSet.getDate("endDate"));

                bids.add(bid);
            }

        } catch (Exception e) {
            Utils.logEvent(e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    Utils.logEvent(e.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    Utils.logEvent(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Utils.logEvent(e.getMessage());
                }
            }
        }

        return bids;
    }

    private void updateCurrentWinningBid(Bid bid, boolean closeBid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = singletonDbConnection;
            if (closeBid) {
                preparedStatement = connection
                        .prepareStatement("update auction set currentWinner = ?, currentWinningBid = ?, winner = ?, isRunning = 0 " +
                                "  where id = ? ");

                preparedStatement.setLong(1, bid.getUser());
                preparedStatement.setLong(2, bid.getId());
                preparedStatement.setLong(3, bid.getUser());
                preparedStatement.setLong(4, bid.getAuction());

            } else {
                preparedStatement = connection
                        .prepareStatement("update auction set currentWinner = ?, currentWinningBid = ?" +
                                "  where id = ? ");

                preparedStatement.setLong(1, bid.getUser());
                preparedStatement.setLong(2, bid.getId());
                preparedStatement.setLong(3, bid.getAuction());
            }





            preparedStatement.executeUpdate();

        } catch (Exception e) {
            Utils.logEvent(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    Utils.logEvent(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Utils.logEvent(e.getMessage());
                }
            }
        }

    }


    @Override
    public void processCurrentWinningBid(List<Bid> currentWinningBids) {
        try {

            // currentWinningBids = currentWinningBid();

            ConcreteIterator bidList = new ConcreteIterator(currentWinningBids);
            Iterator bidIterator = bidList.getIterator();

            while (bidIterator.hasNext()) {
                Bid b = (Bid) bidIterator.next();
                updateCurrentWinningBid(b, false);
            }

            /*
            for (Bid b : currentWinningBids) {
                updateCurrentWinningBid(b, false);
            }*/

        } catch (Exception e) {
            Utils.logEvent(e.getMessage());
        }
    }


    @Override
    public List<Auction> calculateWinningBid(List<Bid> bids) {

        List<Auction> auctionWinner = new ArrayList<>();
        try {
            ConcreteIterator bidList = new ConcreteIterator(bids);
            Iterator bidIterator = bidList.getIterator();

            while (bidIterator.hasNext()) {

                Bid b = (Bid) bidIterator.next();

                Date currentDate = Utils.sqlCurrentDate();
                Date endDate = b.getEndDate();


                if (currentDate.equals(endDate))//TODO ADD TIME FROM CONFIG
                {
                    updateCurrentWinningBid(b, true);

                    Auction auction = new Auction();
                    auction.setId(b.getAuction());
                    auction.setCurrentWinningBid(b.getId());// TODO CURRENT AND FINAL WINNING BID
                    auction.setWinner(b.getUser());// TODO CURRENT AND FINAL WINNING USER
                    auction.setEndDate(b.getEndDate());

                    auctionWinner.add(auction);


                }
            }
        } catch (Exception e) {
            Utils.logEvent(e.getMessage());
        }
        return auctionWinner;
    }


    public void calculateWinningBidTest(List<Auction> auctions) {

        ConcreteIterator auctionList = new ConcreteIterator(auctions);
        Iterator auctionIterator = auctionList.getIterator();

        while (auctionIterator.hasNext()) {

            Auction v = (Auction) auctionIterator.next();

            System.out.println(v.getMinimumPrice());
        }

    }


}

