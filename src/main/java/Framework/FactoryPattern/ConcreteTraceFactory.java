package Framework.FactoryPattern;

public class ConcreteTraceFactory implements TraceFactory {

    private static TraceFactory factory = new ConcreteTraceFactory();

    private ConcreteTraceFactory() {

    }

    public static TraceFactory getFactory() {
        return factory;
    }

    @Override
    public Trace getTracer(String vql) {
        switch (vql) {
            case "trace.log":
                return new FileTrace();
            case "console":
                return new ConsoleTrace();
        }
        return null;
    }
}
