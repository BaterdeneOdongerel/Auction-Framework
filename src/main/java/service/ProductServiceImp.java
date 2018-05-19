package service;

import Framework.IteratorPattern.ConcreteIterator;
import Framework.IteratorPattern.Iterator;
import Framework.SingletonPattern.Singleton;
import db.ConnectionConfiguration;

import model.LabelValue;

import model.Auction;
import model.Product;
import model.User;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImp implements ProductService {

    private Connection singletonDBConnection = Singleton.INSTANCE.getConnection();
    public static void main(String args[]){

        ProductServiceImp psi = new ProductServiceImp();
        Product p = new Product();

        p.setName("name");
        p.setDesc("desc");
        p.setCatid(1);
        p.setImagePath("C:\\w\\awawdawd\\awdawd");
        psi.create(p);

        List<Product> ps = psi.selectAll();
        for (Product cp : ps) {
            System.out.println(cp.getId() + " " + cp.getName());
        }


    }

    @Override
    public void create(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = singletonDBConnection;
            preparedStatement = connection.prepareStatement("INSERT INTO product (name, `desc` , catid, image_path) "
                    + " VALUES (?, ? , ? , ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDesc());
            preparedStatement.setInt(3, product.getCatid());
            preparedStatement.setString(4, product.getImagePath());
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
    public Product selectById(int id) {
        Product product = new Product();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = singletonDBConnection;
            preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDesc(resultSet.getString("desc"));
                product.setCatid(resultSet.getInt("catid"));
                product.setImagePath(resultSet.getString("image_path"));
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
        return product;
    }

    @Override
    public List<Product> selectAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = singletonDBConnection;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product");

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));

                product.setImagePath(resultSet.getString("image_path"));
                product.setDesc(resultSet.getString("desc"));
                product.setCatid(resultSet.getInt("catid"));
                products.add(product);
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

        return products;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = singletonDBConnection;

            preparedStatement = connection.prepareStatement("DELETE from product where id = ?");
            preparedStatement.setInt(1, id);
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
    public void update(Product product, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = singletonDBConnection;

            preparedStatement = connection.prepareStatement("UPDATE product set name = ? , `desc` = ? , catid = ? , image_path = ?  " +
                    "where id = ?");
            preparedStatement.setInt(5, id);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDesc());
            preparedStatement.setInt(3, product.getCatid());
            preparedStatement.setString(4, product.getImagePath());
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
    public List<Product> selectByCatid(int catid) {
        List<Product> products = new ArrayList<Product>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = singletonDBConnection;
            preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE catid = ?");
            preparedStatement.setInt(1, catid);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setDesc(resultSet.getString("desc"));
                product.setId(resultSet.getInt("id"));

                product.setCatid(resultSet.getInt("catid"));
                product.setImagePath(resultSet.getString("image_path"));
                products.add(product);
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

        return products;
    }

    @Override
    public List<Auction> selectWithAuctions() {
        List<Auction> auctions = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = singletonDBConnection;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from product join auction on product.id = auction.product");

            while (resultSet.next()) {
                Auction auction = new Auction();
                auction.setId(resultSet.getInt("id"));
                auction.setName(resultSet.getString("name"));
                auction.setRunning(resultSet.getBoolean("isrunning"));
                auction.setImageLink(resultSet.getString("image_path"));
                auction.setStartDate(resultSet.getDate("startDate"));
                auction.setEndDate(resultSet.getDate("endDate"));
                auction.setMinimumPrice(resultSet.getDouble("minimumPrice"));
                auction.setCurrentWinningBid(resultSet.getLong("currentWinningBid"));
                auctions.add(auction);
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

        return auctions;
    }

    @Override
    public List<LabelValue> getProductListForDropDown() {
        List<LabelValue> selectItems = new ArrayList<>();

        ConcreteIterator productList = new ConcreteIterator(selectAll());
        Iterator productIterator = productList.getIterator();

        while (productIterator.hasNext()) {

            Product p = (Product) productIterator.next();
            LabelValue l = new LabelValue();
            l.setLabel(p.getName());
            l.setValue(p.getId());
            selectItems.add(l);
        }
/*
        for (Product p : selectAll()) {

            LabelValue l = new LabelValue();
            l.setLabel(p.getName());
            l.setValue(p.getId());
            selectItems.add(l);

        }
*/
        return selectItems;
    }
}
