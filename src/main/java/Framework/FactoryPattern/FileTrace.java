package Framework.FactoryPattern;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class FileTrace implements Trace {
    private static final String LOG_FILE_NAME = "trace.log";
    private static final String ERR_LOG_FILE_NAME = "err_trace.log";

    private volatile boolean isDebuggable;


    private void writeToFile(String fileName, String message) {
        try (FileOutputStream fos = new FileOutputStream(fileName, true)) {
            Writer writer = new PrintWriter(fos);
            // writer.write("\n");
            writer.write("\n" + message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDebug(boolean debug) {
        this.isDebuggable = debug;
    }

    @Override
    public void debug(String message) {
        writeToFile(LOG_FILE_NAME, message);
    }

    @Override
    public void error(String message) {
        writeToFile(LOG_FILE_NAME, message);
    }


}



