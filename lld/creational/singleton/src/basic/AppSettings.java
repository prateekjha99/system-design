package basic;

public final class AppSettings {
    private static AppSettings instance;

    private String appName;

    private AppSettings() {
        this.appName = "Default App";
    }

    public static AppSettings getInstance() {
        if (instance == null) {
            instance = new AppSettings();
        }
        return instance;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
