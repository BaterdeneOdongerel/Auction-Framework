package Framework.FactoryPattern;

public class ConcreteTraceFactory implements TraceFactory {
    @Override
    public Trace getTracer(String vql) {
        switch (vql) {
            case "trace.log":
                return new ConsoleTrace();
            case "console":
                return new FileTrace();
        }
        return null;
    }
}
