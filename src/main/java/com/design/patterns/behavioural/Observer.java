package design.behavioural;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The observer pattern is a software design pattern in which an object, called the subject,
 * maintains a list of its dependents, called observers, and notifies them automatically of any state changes,
 * usually by calling one of their methods.
 *
 * A one-to-many dependency between objects should be defined without making the objects tightly coupled.
 * It should be ensured that when one object changes state an open-ended number of dependent objects are updated automatically.
 * It should be possible that one object can notify an open-ended number of other objects.
 *
 * Subject maintains a list of observers, when the subject changes it has to notify all observers.
 * @author angel.beshirov
 */
interface Observer {
    void update(String data);
}

class KeywordObserver implements Observer {

    @Override
    public void update(String data) {
        if(Arrays.asList(data.split("\\s+")).contains("keyword")) {
            System.out.println("Keyword encountered");
        }
    }
}

class NumberObserver implements Observer {

    @Override
    public void update(String data) {
        if(data.matches("^.*[0-9].*$")) {
            System.out.println("Number encountered");
        }
    }
}

interface InputReader {
    void read();
    void registerObserver(Observer observer);
    void notifyObservers(String input);
}

class ConsoleInputReader implements InputReader {

    private List<Observer> observers;

    public ConsoleInputReader() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void read() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            notifyObservers(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String input) {
        for(Observer observer: observers) {
            observer.update(input);
        }
    }
}

class TestObserver {

    public static void main(String[] args) {
        InputReader ir = new ConsoleInputReader();
        Observer observer = new KeywordObserver();
        Observer observer1 = new NumberObserver();
        ir.registerObserver(observer);
        ir.registerObserver(observer1);
        ir.read();
    }
}
