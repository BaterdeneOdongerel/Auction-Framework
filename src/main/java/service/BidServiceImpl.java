package service;

import db.ConnectionConfiguration;
import model.Bid;
import org.springframework.stereotype.Service;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service("bidService")
public class BidServiceImpl implements BidService {


    @Override
    public void create(Bid bid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int userId = Services.UserService.getCurrentUser().getUserId();

        try {
            connection = ConnectionConfiguration.getConnection();
            ;
            preparedStatement = connection.
                    prepareStatement("INSERT INTO bid (amount, bidDate, auction, user) "
                            + " VALUES (?, ?, ?, ?)");
            preparedStatement.setDouble(1, bid.getAmount());
            preparedStatement.setDate(2, Utils.sqlCurrentDate());
            preparedStatement.setLong(3, bid.getAuction());
            preparedStatement.setLong(4, userId);

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
    public Bid selectById(int id) {
        Bid bid = new Bid();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            ;
            preparedStatement = connection.prepareStatement("SELECT * FROM bid WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bid.setId(resultSet.getInt("id"));
                bid.setAmount(resultSet.getDouble("amount"));
                bid.setBidDate(resultSet.getDate("bidDate"));
                bid.setUser(resultSet.getLong("user"));
                bid.setAuction(resultSet.getLong("auction"));
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

        return bid;
    }

    @Override
    public List<Bid> selectAll() {
        List<Bid> bids = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            ;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM bid");

            while (resultSet.next()) {
                Bid bid = new Bid();
                bid.setId(resultSet.getInt("id"));
                bid.setAmount(resultSet.getDouble("amount"));
                bid.setBidDate(resultSet.getDate("bidDate"));
                bid.setUser(resultSet.getInt("user"));
                bid.setAuction(resultSet.getInt("auction"));
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
            if (statement != null) {
                try {
                    statement.close();
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

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            ;
            preparedStatement = connection.prepareStatement("DELETE FROM bid WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM bid WHERE id = ?");

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
    public void update(Bid bid, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            ;
            preparedStatement = connection
                    .prepareStatement("UPDATE bid SET " + "amount = ?, bidDate = ?, auction = ?, user=? WHERE id = ?");

            preparedStatement.setDouble(1, bid.getAmount());
            preparedStatement.setDate(2, bid.getBidDate());
            preparedStatement.setLong(3, bid.getAuction());
            preparedStatement.setLong(4, bid.getUser());
            preparedStatement.setLong(5, id);
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
}
