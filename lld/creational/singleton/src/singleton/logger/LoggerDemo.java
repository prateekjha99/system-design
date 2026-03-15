package singleton.logger;

/**
 * Demonstrates Logger singleton (real-world example).
 */
public class LoggerDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.setMinLevel(Logger.LogLevel.INFO);

        System.out.println("Logger Singleton Example");
        System.out.println("-------------------------");
        logger.info("Application started");
        logger.warn("High memory usage detected");
        logger.error("Failed to connect to database");
    }
}
