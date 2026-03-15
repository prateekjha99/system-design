package singleton.logger;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Real-world Singleton: application-wide logger.
 * Ensures all log messages go through a single instance.
 */
public final class Logger {
    private static volatile Logger instance;

    private final List<String> logBuffer = new ArrayList<>();
    private LogLevel minLevel = LogLevel.INFO;

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void setMinLevel(LogLevel level) {
        this.minLevel = level;
    }

    public void log(LogLevel level, String message) {
        if (level.ordinal() >= minLevel.ordinal()) {
            String entry = String.format("[%s] %s: %s", Instant.now(), level, message);
            logBuffer.add(entry);
            System.out.println(entry);
        }
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public List<String> getLogBuffer() {
        return new ArrayList<>(logBuffer);
    }

    public enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }
}
