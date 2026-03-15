package singleton.threadsafe;

/**
 * Demonstrates thread-safe Singleton with concurrent access.
 * Multiple threads call getInstance() simultaneously; all receive the same instance.
 */
public class ThreadSafeDemo {
    private static final int THREAD_COUNT = 5;
    private static final AppSettingsThreadSafe[] instances = new AppSettingsThreadSafe[THREAD_COUNT];

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thread-Safe Singleton Example");
        System.out.println("------------------------------");

        // Spawn threads that all call getInstance() concurrently
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int index = i;
            threads[i] = new Thread(() -> instances[index] = AppSettingsThreadSafe.getInstance());
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }

        // Verify all threads got the same instance
        AppSettingsThreadSafe first = instances[0];
        boolean allSame = true;
        for (int i = 1; i < THREAD_COUNT; i++) {
            if (instances[i] != first) allSame = false;
        }
        System.out.println("All " + THREAD_COUNT + " threads received same instance: " + allSame);
    }
}
