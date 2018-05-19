package utils;

import Framework.FactoryPattern.Trace;
import service.Services;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;



public class Utils {


    public static void logEvent(String msg) {
        Trace tFile = Services.traceFactory.getTracer(TraceValue.TRACE.name().toLowerCase() + ".log");
        Trace tConsole = Services.traceFactory.getTracer(TraceValue.CONSOLE.name().toLowerCase());
        tConsole.error(sqlCurrentDate() + ":" + msg);
        tFile.error(sqlCurrentDate() + ":" + msg);
    }

    public static String status(boolean value) {
        return value == true ? "Open" : "Closed";
    }

    public static Date convertToSqlDate(String stringDate) throws ParseException {

        SimpleDateFormat sdfmt1 = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat sdfmt2 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dDate = sdfmt1.parse(stringDate);
        //String strOutput = sdfmt2.format( dDate );

        return Date.valueOf(sdfmt2.format(dDate));
    }
    public static Date sqlCurrentDate() {
        long millis = System.currentTimeMillis();
        return new Date(millis);
    }
    public static Timestamp convertTime(LocalDate date) {
        return Timestamp.valueOf(date.atStartOfDay());
    }

    public static LocalDate convertDate(Timestamp date) {
        return date.toLocalDateTime().toLocalDate();
    }

    public static <T> T extractValue(HttpServletRequest request, String name, T defaultValue) {
        String param = request.getParameter(name);
        boolean hasValue = param != null && !param.isEmpty();
        return hasValue ? (T) param : defaultValue;
    }

    public static LocalDate extractDate(HttpServletRequest request, String name, LocalDate defaultValue) {
        String param = request.getParameter(name);
        boolean hasValue = param != null && !param.isEmpty();
        return hasValue ? LocalDate.parse(param) : defaultValue;
    }

    public static Float extractNumber(HttpServletRequest request, String name, Float defaultValue) {
        String param = request.getParameter(name);
        boolean hasValue = param != null && !param.isEmpty();
        return hasValue ? Float.parseFloat(param) : defaultValue;
    }

    public static Integer extractInteger(HttpServletRequest request, String name, Integer defaultValue) {
        String param = request.getParameter(name);
        boolean hasValue = param != null && !param.isEmpty();
        return hasValue ? Integer.parseInt(param) : defaultValue;
    }

    public static boolean isTime(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();
        return date.isAfter(now.minusMinutes(1)) && date.isBefore(now.plusMinutes(1));
    }
}
