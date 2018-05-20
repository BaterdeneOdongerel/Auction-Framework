package props;


import db.ConnectionConfiguration;
import utils.Utils;

import java.io.IOException;
import java.util.Properties;

/**
 */
public enum MessagesProp {

    INSTANCE;
    private Properties prop;
    private MessagesProp() {
        prop = new Properties();
        try {
            prop.load(ConnectionConfiguration.class.getClassLoader().getResourceAsStream("messages.properties"));
        } catch (IOException e) {
            Utils.logEvent(e.getMessage());
        }
    }


    public String getProp(String value) {
        return prop.getProperty(value);
    }
}
