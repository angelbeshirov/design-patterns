package com.design.patterns.structural;

import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * In computer programming, flyweight is a software design pattern. A flyweight is an object that minimizes memory
 * usage by sharing as much data as possible with other similar objects; it is a way to use objects in large numbers
 * when a simple repeated representation would use an unacceptable amount of memory. Often some parts of the object
 * state can be shared, and it is common practice to hold them in external data structures and pass them to the
 * objects temporarily when they are used.
 * <p>
 * A classic example usage of the flyweight pattern is the data structures for graphical representation of
 * characters in a word processor. It might be desirable to have, for each character in a document, a glyph
 * object containing its font outline, font metrics, and other formatting data, but this would amount to
 * hundreds or thousands of bytes for each character. Instead, for every character there might be a reference
 * to a flyweight glyph object shared by every instance of the same character in the document; only the position
 * of each character (in the document and/or the page) would need to be stored internally.
 * <p>
 * Another example is string interning.
 * <p>
 * In other contexts the idea of sharing identical data structures is called hash consing.
 * <p>
 * ---------------------------------------------------------------------------------------------------------------------
 * The Flyweight design pattern is one of the twenty-three well-known GoF design patterns that describe how to
 * solve recurring design problems to design flexible and reusable object-oriented software, that is, objects that
 * are easier to implement, change, test, and reuse.
 * <p>
 * What problems can the Flyweight design pattern solve?
 * <p>
 * 1) Large numbers of objects should be supported efficiently.
 * 2) Creating large numbers of objects should be avoided.
 * 3) When representing large text documents, for example, creating an object for each character in the document
 * would result in a huge number of objects that could not be processed efficiently.
 * <p>
 * What solution does the Flyweight design pattern describe?
 * <p>
 * Define Flyweight objects that store intrinsic (invariant) state that can be shared and
 * provide an interface through which extrinsic (variant) state can be passed in.
 * <p>
 * This enables clients to reuse (share) Flyweight objects (instead of creating a new object each time)
 * and pass in extrinsic state when they invoke a Flyweight operation.
 * This greatly reduces the number of physically created objects.
 * <p>
 * Intrinsic state is invariant (context independent) and therefore can be shared (for example, the code of
 * character 'A' in a given character set).
 * Extrinsic state is variant (context dependent) and therefore can not be shared and must be passed in
 * (for example, the position of character 'A' in a text document).
 * <p>
 * <p>
 * Note:
 * To enable safe sharing, between clients and threads, Flyweight objects must be immutable.
 * Flyweight objects are by definition value objects. The identity of the object instance is of no
 * consequence therefore two Flyweight instances of the same value are considered equal.
 *
 * @author angel.beshirov
 */

class ReusableObject {
    private static final WeakHashMap<String, ReusableObject> cache = new WeakHashMap<>();
    private final String name;

    private ReusableObject(String name) {
        this.name = name;
    }

    public static synchronized ReusableObject intern(String name) {
        return cache.computeIfAbsent(name, x -> new ReusableObject(name));
    }

    public static synchronized int sizeOfCache() {
        return cache.size();
    }

    @Override
    public String toString() {
        return "ReusableObject{" +
                "name='" + name + '\'' +
                '}';
    }
}

interface Handler {
    void create(Integer size);

    static Handler of(String name) {
        return (Integer size1) -> {
            ReusableObject object = ReusableObject.intern(name);
            System.out.println("Creating " + object.toString() + " of size " + size1);
        };
    }
}

class FlyweightDriver {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        final List<String> names = Arrays.asList("building 1", "building 2", "building 3", "building 4", "building 5");

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                for (int j = 10; j <= 50; j += 10) {
                    Handler handler = Handler.of(names.get((j - 1) / 10));
                    handler.create(j);
                }
            });
        }

        executor.shutdown();
        try {
            // Wait a while for existing tasks to terminate
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!executor.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Cache size is:" + ReusableObject.sizeOfCache());
    }
}
