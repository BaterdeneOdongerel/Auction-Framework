package service;


import db.ConnectionConfiguration;
import model.user.User;
import org.springframework.stereotype.Service;
import props.MessagesProp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private User currentUser;

    public static void main(String[] args) {
        UserServiceImpl userModel = new UserServiceImpl();
        boolean success = userModel.login("vanthuyphan@gmail.com", "123456");
        if (success) {
            System.out.println("Bing go");
            System.out.println(userModel.getCurrentUser().getLastName());
        } else {
            System.out.println(MessagesProp.INSTANCE.getProp("errorLogin"));
        }

        List<User> us = userModel.selectByName("ba");
        User u = userModel.selectByEmail("bati@gmail.com");
        System.out.println("====>" + u.getEmail());
        User n = new User();
        n.setUsername("iiii");
        userModel.create(n);

    }

    @Override
    public User selectByEmail(String email) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("communication"));
                user.setUsername(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
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

        return user;
    }

    @Override
    public User selectById(int id) {
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("communication"));
                user.setPassword(resultSet.getString("password"));
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

        return user;
    }

    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user");

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setFirstName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("communication"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
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

        return users;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE user_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM user WHERE user_id = ?");

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
    public void update(User user, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection
                    .prepareStatement("UPDATE user SET " + "first_name = ?, last_name = ?, email = ? WHERE user_id = ?");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, id);
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
    public boolean login(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean ret = false;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ? and password = ? ");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            User user = new User();
            user.setUserId(-1);
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                ret = true;
                currentUser = user;
            }
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
        return ret;
    }

    @Override
    public void create(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO user (first_name, last_name, email, password, user_name) "
                    + " VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUsername());
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

    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public List<User> selectByName(String name) {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user WHERE user_name like '%"+ name+"%'");
            System.out.print("SELECT * FROM user WHERE user_name like '%"+ name+"%'");
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("user_name"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("communication"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
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

        return users;
    }
}
