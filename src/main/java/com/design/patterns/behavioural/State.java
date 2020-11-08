package com.design.patterns.behavioural;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * The state pattern is a behavioral software design pattern that allows an object to alter its behavior when
 * its internal state changes. This pattern is close to the concept of finite-state machines. The state
 * pattern can be interpreted as a strategy pattern, which is able to switch a strategy through invocations
 * of methods defined in the pattern's interface.
 *
 * The state pattern is used in computer programming to encapsulate varying behavior for the same object,
 * based on its internal state. This can be a cleaner way for an object to change its behavior at runtime
 * without resorting to conditional statements and thus improve maintainability.
 *
 * The state pattern is set to solve two main problems:[4]
 *
 * An object should change its behavior when its internal state changes.
 * State-specific behavior should be defined independently. That is, adding new states should not affect the
 * behavior of existing states.
 * Implementing state-specific behavior directly within a class is inflexible because it commits the class to a
 * particular behavior and makes it impossible to add a new state or change the behavior of an existing state
 * later independently from (without changing) the class. In this, the pattern describes two solutions:
 *
 * Define separate (state) objects that encapsulate state-specific behavior for each state. That is, define an
 * interface (state) for performing state-specific behavior, and define classes that implement the interface
 * for each state.
 * A class delegates state-specific behavior to its current state object instead of implementing state-specific
 * behavior directly.
 * This makes a class independent of how state-specific behavior is implemented. New states can be added by
 * defining new state classes. A class can change its behavior at run-time by changing its current state object.
 *
 * @author angel.beshirov
 */

interface State {
    void doWork(StateContext stateContext, String value);
}

class LowerCaseState implements State {

    @Override
    public void doWork(StateContext stateContext, String value) {
        System.out.println(value.toLowerCase());
        stateContext.setState(new UpperCaseState());
    }
}

class UpperCaseState implements State {

    private static final int LIMIT = 3;
    private int internalCounter = 0;

    @Override
    public void doWork(StateContext stateContext, String value) {
        System.out.println(value.toUpperCase());
        if(LIMIT <= ++internalCounter) {
            internalCounter = 0;
            stateContext.setState(new LowerCaseState());
        }
    }
}

class StateContext {
    private State state;

    public StateContext() {
        this.state = new LowerCaseState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void doWork(String value) {
        state.doWork(this, value);
    }
}

class StateDriver {
    public static void main(String... args) {
        List<String> names = Arrays.asList("Ivan", "Mariq", "Ana", "Zdravko", "Yordan","Zahari", "Todor");
        final StateContext stateContext = new StateContext();

        Consumer<String> getLambdaProf = stateContext::doWork;
        names.stream()
                .forEach(getLambdaProf);
    }

}
