package service;

import db.ConnectionConfiguration;
import model.user.Bid;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service("bidService")
public class BidServiceImpl implements BidService {
    @Override
    public void create(Bid bid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.
                    prepareStatement("INSERT INTO bid (amount, bidDate, auction, user) "
                            + " VALUES (?, ?, ?, ?)");
            preparedStatement.setBigDecimal(1, bid.getAmount());
            preparedStatement.setDate(2, bid.getBidDate());
            preparedStatement.setInt(3, bid.getAuction().getId());
            preparedStatement.setInt(4, bid.getUser().getUserId());

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
    public Bid selectById(int id) {
        Bid bid = new Bid();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM bid WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bid.setId(resultSet.getInt("id"));
                bid.setAmount(resultSet.getBigDecimal("amount"));
                bid.setBidDate(resultSet.getDate("bidDate"));
                //bid.setUser(resultSet.getInt("user"));
                //bid.setAuction(resultSet.getInt("auction"));
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
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM bid");

            while (resultSet.next()) {
                Bid bid = new Bid();
                bid.setId(resultSet.getInt("id"));
                bid.setAmount(resultSet.getBigDecimal("amount"));
                bid.setBidDate(resultSet.getDate("bidDate"));
                // bid.setUser(resultSet.getInt("user"));
                //bid.setAuction(resultSet.getInt("auction"));
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

        return bids;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM bid WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM bid WHERE id = ?");

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
    public void update(Bid bid, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection
                    .prepareStatement("UPDATE bid SET " + "amount = ?, bidDate = ?, auction = ?, user=? WHERE id = ?");

            preparedStatement.setBigDecimal(1, bid.getAmount());
            preparedStatement.setDate(2, bid.getBidDate());
            preparedStatement.setInt(3, bid.getAuction().getId());
            preparedStatement.setInt(4, bid.getUser().getUserId());
            preparedStatement.setInt(5, id);
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
