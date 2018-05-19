package props;


import db.ConnectionConfiguration;
import utils.Utils;

import java.io.IOException;
import java.util.Properties;

/**
 */
public enum DatabaseProp {

    INSTANCE;
    private Properties prop;
    private DatabaseProp() {
        prop = new Properties();
        try {
            prop.load(ConnectionConfiguration.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            Utils.logEvent(e.getMessage());
        }
    }


    public String getProp(String value) {
        return prop.getProperty(value);
    }
}
