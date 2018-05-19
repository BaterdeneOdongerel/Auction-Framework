package Framework.SingletonPattern;


import props.DatabaseProp;
import utils.Utils;


import java.sql.Connection;
import java.sql.DriverManager;


public enum Singleton {

    INSTANCE;

    public static Connection getConnection() {
        Connection connection = null;
        DatabaseProp prop = DatabaseProp.INSTANCE;

        try {

            String databaseName = prop.getProp("MySQL.database");
            String baseUrl = prop.getProp("MySQL.path");
            String user = prop.getProp("MySQL.user");
            String password = prop.getProp("MySQL.pass");

            String URL = String.format("%s/%s?user=%s&password=%s&useSSL=false&serverTimezone=UTC", baseUrl, databaseName, user, password);
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL);
        } catch (Exception e) {
            Utils.logEvent(e.getMessage());
        }
        return connection;
    }

}
