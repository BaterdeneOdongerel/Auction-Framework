package props;


import db.ConnectionConfiguration;

import java.io.IOException;
import java.util.Properties;

/**
 */
public enum EmailProp {

    INSTANCE;
    private Properties prop;

    private EmailProp() {
        prop = new Properties();
        try {
            prop.load(ConnectionConfiguration.class.getClassLoader().getResourceAsStream("email.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getProp(String value) {
        return prop.getProperty(value);
    }
}
