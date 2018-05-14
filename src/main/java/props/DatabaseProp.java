package props;


import db.ConnectionConfiguration;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Van on 4/23/18.
 */
public enum DatabaseProp {

    INSTANCE;
    private Properties prop;
    private DatabaseProp() {
        prop = new Properties();
        try {
            prop.load(ConnectionConfiguration.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getProp(String value) {
        return prop.getProperty(value);
    }
}
