package threadsafe;

/**
 * Thread-safe Singleton using double-checked locking with volatile.
 * Safe for use in multithreaded environments.
 */
public final class AppSettingsThreadSafe {
    private static volatile AppSettingsThreadSafe instance;

    private String appName;
    private String environment;

    private AppSettingsThreadSafe() {
        this.appName = "Default App";
        this.environment = "Development";
    }

    public static AppSettingsThreadSafe getInstance() {
        if (instance == null) {
            synchronized (AppSettingsThreadSafe.class) {
                if (instance == null) {
                    instance = new AppSettingsThreadSafe();
                }
            }
        }
        return instance;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
