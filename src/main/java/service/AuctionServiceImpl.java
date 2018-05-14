package service;


import db.ConnectionConfiguration;
import model.user.Auction;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service("auctionService")
public class AuctionServiceImpl implements AuctionService {
    @Override
    public void create(Auction auction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.
                    prepareStatement("INSERT INTO auction (startDate, endDate, minimumPrice, bidOwner,isRunning" +
                            ",currentWinner,currentWinningBid,winner,bids) "
                            + " VALUES (?, ?, ?, ?, ?,?,?,?,?)");

            preparedStatement.setDate(1, auction.getStartDate());
            preparedStatement.setDate(2, auction.getEndDate());
            preparedStatement.setBigDecimal(3, auction.getMinimumPrice());
            preparedStatement.setInt(4, auction.getBidOwner().getUserId());

            preparedStatement.setBoolean(5, auction.isRunning());
            preparedStatement.setInt(6, auction.getCurrentWinner().getUserId());
            preparedStatement.setInt(7, auction.getCurrentWinningBid().getId());
            preparedStatement.setInt(8, auction.getWinner().getUserId());
            preparedStatement.setInt(9, auction.getBids().get(0).getId());

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
                // auction.setCurrentWinner(resultSet.getInt("currentWinner"));
                // auction.setCurrentWinningBid(resultSet.getInt("currentWinningBid"));
                //  auction.setWinner(resultSet.getInt("winner"));
                // auction.setBids(resultSet.getInt("bids"));

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
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM auction");

            while (resultSet.next()) {
                Auction auction = new Auction();
                auction.setId(resultSet.getInt("id"));
                auction.setStartDate(resultSet.getDate("startDate"));
                auction.setEndDate(resultSet.getDate("endDate"));
                auction.setRunning(resultSet.getBoolean("isRunning"));
                auction.setMinimumPrice(resultSet.getBigDecimal("minimumPrice"));
                // auction.setCurrentWinner(resultSet.getInt("currentWinner"));
                // auction.setCurrentWinningBid(resultSet.getInt("currentWinningBid"));
                //  auction.setWinner(resultSet.getInt("winner"));
                // auction.setBids(resultSet.getInt("bids"));
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
            if (statement != null) {
                try {
                    statement.close();
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
    public void update(Auction auction, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection
                    .prepareStatement("UPDATE auction SET " + "startDate = ?, endDate = ?, minimumPrice = ?, bidOwner=? " +
                            ", isRunning= ?, currentWinner=?,currentWinningBid=?,winner=?,bids=? WHERE id = ?");

            preparedStatement.setDate(1, auction.getStartDate());
            preparedStatement.setDate(2, auction.getEndDate());
            preparedStatement.setBigDecimal(3, auction.getMinimumPrice());
            preparedStatement.setInt(4, auction.getBidOwner().getUserId());

            preparedStatement.setBoolean(5, auction.isRunning());
            preparedStatement.setInt(6, auction.getCurrentWinner().getUserId());
            preparedStatement.setInt(7, auction.getCurrentWinningBid().getId());
            preparedStatement.setInt(8, auction.getWinner().getUserId());
            preparedStatement.setInt(9, auction.getBids().get(0).getId());
            preparedStatement.setInt(10, id);

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

}

