package Framework.FactoryPattern;


public interface Trace {
    void debug(String message);

    void error(String message);

    void setDebug(boolean debug);


}
