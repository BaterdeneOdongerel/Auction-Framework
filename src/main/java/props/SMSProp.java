package props;


import db.ConnectionConfiguration;
import utils.Utils;

import java.io.IOException;
import java.util.Properties;

/**
 */
public enum SMSProp {

    INSTANCE;
    private Properties prop;

    private SMSProp() {
        prop = new Properties();
        try {
            prop.load(ConnectionConfiguration.class.getClassLoader().getResourceAsStream("sms.properties"));
        } catch (IOException e) {
            Utils.logEvent(e.getMessage());
        }
    }


    public String getProp(String value) {
        return prop.getProperty(value);
    }
}
