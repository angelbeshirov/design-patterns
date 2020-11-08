package design.structural;

import java.util.Arrays;
import java.util.List;

/**
 * Bridge design pattern is used to separate the abstraction from the implementation.
 *
 * @author angel.beshirov
 */

abstract class Car {
    protected List<Action> actions;

    protected Car(Action... actions) {
        this.actions = Arrays.asList(actions);
    }

    public abstract void construct();
}

class OrdinaryCar extends Car {

    public OrdinaryCar(Action... actions) {
        super(actions);
    }

    @Override
    public void construct() {
        for(Action action: actions) {
            action.doAction();
        }
    }
}

class Bus extends Car {

    public Bus(Action... actions) {
        super(actions);
    }

    @Override
    public void construct() {
        for(Action action: actions) {
            action.doAction();
        }
    }
}

interface Action {
    void doAction();
}

class Construct implements Action {

    @Override
    public void doAction() {
        System.out.println("Constructing");
    }
}

class Assemble implements Action {

    @Override
    public void doAction() {
        System.out.println("Assembling");
    }
}

class Bridge {

    public static void main(String... args) {
        Car ordinaryCar = new OrdinaryCar(new Construct(), new Assemble());
        Car bus = new Bus(new Construct(), new Assemble());

        ordinaryCar.construct();
        bus.construct();
    }

}
