package service;


import Framework.IteratorPattern.ConcreteIterator;
import Framework.IteratorPattern.Iterator;
import db.ConnectionConfiguration;
import model.user.Auction;
import model.user.Bid;
import model.user.Category;
import model.user.Product;
import model.user.Report.AuctionReport;
import org.springframework.stereotype.Service;
import utils.Utils;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

@Service("auctionService")
public class AuctionServiceImpl implements AuctionService {
    public static void main(String arg[]) throws ParseException {
        AuctionServiceImpl a = new AuctionServiceImpl();
        List<Auction> ls = a.selectRunning();
        for (Auction ac : ls) {
            System.out.println("-------->" + ac.getId() + " " + ac.getProduct());
        }
        //a.processCurrentWinningBid();
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
    public void processCurrentWinningBid() {

    }

    @Override
    public AuctionReport calculateWinningBid(List<AuctionReport> auctions) {
        return null;
    }

    @Override
    public List<AuctionReport> selectAuctionReport(Date startDate, Date endDate, boolean isRunning) {
        return null;
    }

    @Override
    public List<Auction> selectRunning() {
        List<Auction> auctions = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("select a.* , p.id as pid , p.name , p.desc from auction a inner join product p on a.product = p.id where isrunning = 1");
            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {

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
    public void create(Auction auction) {

    }

    @Override
    public Auction selectById(int id) {
        return null;
    }

    @Override
    public List<Auction> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Auction auction, int id) {

    }
    /*

    @Override
    public Auction selectById(int id) {

        Auction auction = new Auction();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM auction WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                auction.setId(resultSet.getInt("id"));
                auction.setStartDate(resultSet.getDate("startDate"));
                auction.setEndDate(resultSet.getDate("endDate"));
                auction.setRunning(resultSet.getBoolean("isRunning"));
                auction.setMinimumPrice(resultSet.getBigDecimal("minimumPrice"));
                auction.setCurrentWinner(resultSet.getLong("currentWinner"));
                auction.setCurrentWinningBid(resultSet.getLong("currentWinningBid"));
                auction.setWinner(resultSet.getLong("winner"));

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

        return null;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
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
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.
                    prepareStatement("INSERT INTO auction (startDate, endDate, minimumPrice, bidOwner,isRunning,product) "
                            + " VALUES (?, ?, ?, ?, ?,?)");

            preparedStatement.setDate(1, auction.getStartDate());
            preparedStatement.setDate(2, auction.getEndDate());
            preparedStatement.setBigDecimal(3, auction.getMinimumPrice());
            preparedStatement.setLong(4, auction.getBidOwner());

            preparedStatement.setBoolean(5, auction.isRunning());//TODO this should be default
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
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection
                    .prepareStatement("UPDATE auction SET " + "startDate = ?, endDate = ?, minimumPrice = ?, bidOwner=? " +
                            ", isRunning= ?,product = ? WHERE id = ?");

            preparedStatement.setDate(1, auction.getStartDate());
            preparedStatement.setDate(2, auction.getEndDate());
            preparedStatement.setBigDecimal(3, auction.getMinimumPrice());
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
    public List<AuctionReport> selectAuctionReport(Date startDate, Date endDate, boolean isRunning) {

        List<AuctionReport> auctions = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("select " +
                    "b.id bidId," +
                    "a.id auctionId,startDate,endDate,bidDate,b.amount bidAmount,a.minimumPrice," +
                    " case  when isRunning = 1 then 'Open' else 'Closed'  end isRunning," +
                    "(select name from product where id = a.product ) product," +
                    " currentWinningBid," +
                    "(select concat (first_name ,' ' , last_name) from user where id = a.winner ) winner," +
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
                auction.setWinner(resultSet.getString("winner"));
                auction.setBidAmount(resultSet.getDouble("bidAmount"));
                auction.setProduct(resultSet.getString("product"));
                auction.setBidUser(resultSet.getString("bidUser"));

                auction.setBidDate(resultSet.getDate("bidDate"));

                auction.setBidOwner(resultSet.getString("bidOwner"));


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


    private List<Bid> currentWinningBid() {
        List<Bid> bids = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("select " +
                    " user,auction," +
                    "  Max(amount) currentWinningBid" +
                    "from bid b, auction a" +
                    "where  a.id = b.auction " +
                    "and a.isRunning = 1" +
                    "group by auction");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Bid bid = new Bid();
                bid.setUser(resultSet.getLong("user"));
                bid.setAuction(resultSet.getLong("auction"));
                bid.setAmount(resultSet.getDouble("currentWinningBid"));

                bids.add(bid);
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

        return bids;
    }

    private void updateCurrentWinningBid(Bid bid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection
                    .prepareStatement("update bidauction set currentWinner =?" +
                            "where auctionId = ?");

            preparedStatement.setLong(1, bid.getUser());
            preparedStatement.setLong(2, bid.getAuction());


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
    public void processCurrentWinningBid() {
        try {
            List<Bid> currentWinningBids = currentWinningBid();
            for (Bid b : currentWinningBids) {
                updateCurrentWinningBid(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AuctionReport calculateWinningBid(List<AuctionReport> auctionReport) {


        ConcreteIterator auctionList = new ConcreteIterator(auctionReport);
        Iterator auctionIterator = auctionList.getIterator();

        while (auctionIterator.hasNext()) {


        }

        return null;
    }*/
}

