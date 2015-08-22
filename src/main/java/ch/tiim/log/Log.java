package ch.tiim.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private static Appender appender;
    private static int minLogLevel = 3;
    private final String clazz;
    private final String[] LOGLEVELS = {
            "ERROR", "WARNING", "INFO", "DEBUG"
    };

    public Log(Class clazz) {
        this.clazz = clazz.getName();
        appender = new ConsoleAppender();
    }

    public void error(String m) {
        log(0, m, null);
    }

    private void log(int logLvl, String msg, Throwable ex) {
        if (logLvl < 0 || logLvl > 3) logLvl = 0;
        if (logLvl > minLogLevel) return;
        StringBuilder b = new StringBuilder();
        b.append(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()));
        b.append(" ").append(clazz);
        b.append(" - ");
        b.append(LOGLEVELS[logLvl]).append(": ");
        if (msg != null) {
            b.append(msg);
        }
        if (msg != null && ex != null) {
            b.append("\n");
        }
        if (ex != null) {
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            b.append(ex.getMessage()).append("\n");
            b.append(errors.toString());
        }
        appender.appendString(b.toString());
    }

    public void error(Throwable t) {
        log(0, null, t);
    }

    public void error(String m, Throwable t) {
        log(0, m, t);
    }

    public void warning(String m) {
        log(1, m, null);
    }

    public void warning(Throwable t) {
        log(1, null, t);
    }

    public void warning(String m, Throwable t) {
        log(1, m, t);
    }

    public void info(String m) {
        log(2, m, null);
    }

    public void info(Throwable t) {
        log(2, null, t);
    }

    public void info(String m, Throwable t) {
        log(2, m, t);
    }

    public void debug(String m) {
        log(3, m, null);
    }

    public void debug(Throwable t) {
        log(3, null, t);
    }

    public void debug(String m, Throwable t) {
        log(3, m, t);
    }

    public void debug(ResultSet s) {
        try {
            ResultSetMetaData rsmd = s.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (s.next()) {
                String st = "";
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) st += ",  ";
                    String columnValue = s.getString(i);
                    st += "[" + rsmd.getColumnName(i) + "|" + columnValue + "]";
                }
                log(3, st, null);
            }
        } catch (SQLException ignored) {

        }
    }

    public static Appender getAppender() {
        return appender;
    }

    public static void setAppender(Appender appender) {
        Log.appender = appender;
    }

    public static int getMinLogLevel() {
        return minLogLevel;
    }

    public static void setMinLogLevel(int minLogLevel) {
        if (minLogLevel < 0) minLogLevel = 0;
        if (minLogLevel > 3) minLogLevel = 3;
        Log.minLogLevel = minLogLevel;
    }


}
