package props;


import db.ConnectionConfiguration;

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
            e.printStackTrace();
        }
    }


    public String getProp(String value) {
        return prop.getProperty(value);
    }
}
