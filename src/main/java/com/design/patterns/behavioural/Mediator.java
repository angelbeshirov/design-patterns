package design.behavioural;

import javax.print.attribute.standard.Media;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * In software engineering, the mediator pattern defines an object that encapsulates how a set of objects interact.
 * This pattern is considered to be a behavioral pattern due to the way it can alter the program's running behavior.
 * <p>
 * With the mediator pattern, communication between objects is encapsulated within a mediator object.
 * Objects no longer communicate directly with each other, but instead communicate through the mediator.
 * This reduces the dependencies between communicating objects, thereby reducing coupling.
 * <p>
 * Mediator is a behavioral design pattern that lets you reduce chaotic dependencies between objects.
 * The pattern restricts direct communications between the objects and forces them to collaborate only via a mediator object.
 * <p>
 * <p>
 * <p>
 * Not very good implementation has to be generalized.
 *
 * @author angel.beshirov
 */

class Storage<T> {
    private T data;
    private Mediator<T> mediator;
    private String name;

    public Storage(Mediator<T> mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
        mediator.notifyObservers(name);
    }
}

class ObserverEvent1<T> implements Runnable {

    private final Mediator<T> mediator;

    public ObserverEvent1(Mediator<T> mediator) {
        this.mediator = mediator;
    }

    @Override
    public void run() {
        T newValue = mediator.getValue("event1").orElse(null);
        System.out.println("event1 do sth, new data is" + newValue);
    }
}

class ObserverEvent2<T> implements Runnable {

    private final Mediator<T> mediator;

    public ObserverEvent2(Mediator<T> mediator) {
        this.mediator = mediator;
    }

    @Override
    public void run() {
        T newValue = mediator.getValue("event2").orElse(null);
        System.out.println("event2 do sth, new data is" + newValue);
    }
}

class Mediator<T> {
    private final Map<String, Storage<T>> storage = new HashMap<>();
    private final List<Consumer<String>> observers = new CopyOnWriteArrayList<>();

    public void setValue(final String storageName, final T value) {
        Storage<T> t = storage.computeIfAbsent(storageName, (String value1) -> new Storage<T>(this, storageName));
        t.setData(value);
    }

    public Optional<T> getValue(String storageName) {
        return Optional.ofNullable(storage.get(storageName)).map(Storage<T>::getData);
    }

    public void addObserver(String storageName, Runnable observer) {
        observers.add(eventName -> {
            if (eventName.equals(storageName)) { // accept method
                observer.run();
            }
        });
    }

    public void notifyObservers(String eventName) {
        observers.forEach(observer -> observer.accept(eventName)); // this accept method is of the Consumer functional interface
    }
}

class DriverMediator {
    public static void main(String... args) {
        Mediator<String> testThis = new Mediator<>();
        testThis.addObserver("event1", new ObserverEvent1<>(testThis));
        testThis.addObserver("event2", new ObserverEvent2<>(testThis));

        testThis.setValue("event1", "some random data");
        testThis.setValue("event1", "another data");
    }
}
