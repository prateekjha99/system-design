package singleton.basic;

/**
 * Demonstrates basic (non-thread-safe) Singleton.
 */
public class BasicDemo {
    public static void main(String[] args) {
        AppSettings firstReference = AppSettings.getInstance();
        AppSettings secondReference = AppSettings.getInstance();

        firstReference.setAppName("Inventory Service");

        System.out.println("Singleton Example");
        System.out.println("-----------------");
        System.out.println("App name: " + secondReference.getAppName());
        System.out.println("Same object instance: " + (firstReference == secondReference));
    }
}
