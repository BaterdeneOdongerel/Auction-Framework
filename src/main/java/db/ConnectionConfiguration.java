package db;



import props.DatabaseProp;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfiguration {

    public static Connection getConnection() {
        Connection connection = null;
        DatabaseProp prop = DatabaseProp.INSTANCE;

        try {

            String baseUrl = prop.getProp("MySQL.path");
            String user = prop.getProp("MySQL.user");
            String password = prop.getProp("MySQL.pass");
            String databaseName = prop.getProp("MySQL.database");
            String URL = String.format("%s/%s?user=%s&password=%s&useSSL=false&serverTimezone=UTC", baseUrl, databaseName, user, password);
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(ConnectionConfiguration.getConnection());

    }
}