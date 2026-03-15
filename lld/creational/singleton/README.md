# Singleton Design Pattern

The Singleton pattern ensures that a class has only one object instance and provides a global access point to that instance.

---

## Problem

1. **Single instance** — Control access to shared resources (database, file, config).
2. **Global access** — Access the instance from anywhere, without letting other code overwrite it.

---

## Solution

1. Make the default constructor **private** (blocks `new` from outside).
2. Add a **static creation method** (e.g. `getInstance()`) that creates the object once, caches it in a static field, and returns the same instance on every call.

---

## Structure

```
┌─────────────────────────┐
│       Singleton         │
├─────────────────────────┤
│ - instance: Singleton   │
│ - ...                   │
├─────────────────────────┤
│ - Singleton()           │  ← private
│ + getInstance(): Singleton │  ← static, returns cached instance
└─────────────────────────┘
```

---

## Thread Safety

The basic lazy `getInstance()` is **not** thread-safe. Two threads can both see `instance == null` and create separate instances.

**Common approaches:**


| Approach                   | Pros                | Cons                                            |
| -------------------------- | ------------------- | ----------------------------------------------- |
| **Eager init**             | Simple, always safe | Instance created at class load (even if unused) |
| **Synchronized method**    | Safe                | Slower; every call acquires lock                |
| **Double-checked locking** | Lazy + efficient    | Needs `volatile` on instance field              |


**Double-checked locking** (used in `Logger.java`):

```java
private static volatile Singleton instance;

public static Singleton getInstance() {
    if (instance == null) {
        synchronized (Singleton.class) {
            if (instance == null) {1
                instance = new Singleton();
            }
        }
    }
    return instance;
}
```

`volatile` ensures visibility across threads; `synchronized` prevents race during first creation.

---

## Applicability

- **Logging systems** — One logger across the app
- **Configuration managers** — Central config access
- **Database connections** — Single connection pool
- **Thread pools** — One pool for concurrent tasks
- **Cache managers**, **print spoolers**, **runtime** (e.g. `java.lang.Runtime`)

---

## Drawbacks

- Acts like global state → harder to test
- Can create tight coupling
- Requires care in multithreaded code

---

## Note: Spring

Spring beans are **singleton by default**. A change in a bean’s state in one service is visible in another.

---

## Package structure

```
src/
└── singleton/
    ├── basic/          # Basic (non-thread-safe) Singleton
    │   ├── AppSettings.java
    │   └── BasicDemo.java
    ├── threadsafe/     # Thread-safe with double-checked locking
    │   ├── AppSettingsThreadSafe.java
    │   └── ThreadSafeDemo.java
    └── logger/         # Real-world thread-safe Logger
        ├── Logger.java
        └── LoggerDemo.java
```

## Running demos

Compile first:

```bash
javac -d out src/singleton/basic/*.java src/singleton/threadsafe/*.java src/singleton/logger/*.java
```

Then run:


| Demo                  | Command                                            |
| --------------------- | -------------------------------------------------- |
| Basic Singleton       | `java -cp out singleton.basic.BasicDemo`           |
| Thread-safe Singleton | `java -cp out singleton.threadsafe.ThreadSafeDemo` |
| Logger Singleton      | `java -cp out singleton.logger.LoggerDemo`         |


