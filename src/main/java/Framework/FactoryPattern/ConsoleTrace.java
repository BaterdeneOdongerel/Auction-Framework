package Framework.FactoryPattern;


public class ConsoleTrace implements Trace {
    private volatile boolean isDebuggable;

    @Override
    public void setDebug(boolean debug) {
        this.isDebuggable = debug;
    }

    @Override
    public void debug(String message) {
        System.out.println(message);
    }

    @Override
    public void error(String message) {
        System.err.println(message);
    }
}