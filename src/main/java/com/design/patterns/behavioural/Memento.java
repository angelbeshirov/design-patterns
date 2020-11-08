package com.design.patterns.behavioural;

import java.util.ArrayList;
import java.util.List;

/**
 * The memento pattern is a software design pattern that provides the ability to restore an object to its previous state (undo via rollback).
 * The memento pattern is implemented with three objects: the originator, a caretaker and a memento.
 * The originator is some object that has an internal state. The caretaker is going to do something to the originator,
 * but wants to be able to undo the change. The caretaker first asks the originator for a memento object.
 * Then it does whatever operation (or sequence of operations) it was going to do. To roll back to the state
 * before the operations, it returns the memento object to the originator. The memento object itself is an
 * opaque object (one which the caretaker cannot, or should not, change). When using this pattern,
 * care should be taken if the originator may change other objects or resources - the memento pattern
 * operates on a single object.
 * <p>
 * <p>
 * <p>
 * The Memento design pattern is one of the twenty-three well-known GoF design patterns that describe how to solve
 * recurring design problems to design flexible and reusable object-oriented software, that is, objects that are easier
 * to implement, change, test, and reuse.
 * <p>
 * What problems can the Memento design pattern solve?
 * <p>
 * The internal state of an object should be saved externally so that the object can be restored to this state later.
 * The object's encapsulation must not be violated.
 * The problem is that a well designed object is encapsulated so that its representation (data structure) is hidden
 * inside the object and can't be accessed from outside the object.
 * <p>
 * What solution does the Memento design pattern describe?
 * <p>
 * Make an object (originator) itself responsible for saving its internal state to a (memento) object and
 * restoring to a previous state from a (memento) object.
 * Only the originator that created a memento is allowed to access it.
 * <p>
 * A client (caretaker) can request a memento from the originator (to save the internal state of the originator) and pass
 * a memento back to the originator (to restore to a previous state).
 * This enables to save and restore the internal state of an originator without violating its encapsulation.
 *
 * @author angel.beshirov
 */
class Originator {
    private String state;

    public Originator(String state) {
        System.out.printf("Setting state to %s \n", state);
        this.state = state;
    }

    public void setState(String state) {
        System.out.printf("Setting state to %s \n", state);
        this.state = state;
    }

    public Memento saveToMemento() {
        return new Memento(state);
    }

    public void restoreState(Memento memento) {
        setState(memento.getState());
    }

    public static class Memento {
        private String state;

        public Memento(String state) {
            this.state = state;
        }

        private String getState() {
            return state;
        }
    }
}

class Caretaker {
    public static void main(String... args) {
        List<Originator.Memento> states = new ArrayList<>();
        Originator originator = new Originator("initial state");
        states.add(originator.saveToMemento());
        originator.setState("new state");
        originator.restoreState(states.get(0));
    }
}
