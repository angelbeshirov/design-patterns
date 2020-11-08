package com.design.patterns.behavioural;

/**
 * In computer programming, the strategy pattern (also known as the policy pattern) is a behavioral software design
 * pattern that enables selecting an algorithm at runtime. Instead of implementing a single algorithm directly,
 * code receives run-time instructions as to which in a family of algorithms to use.
 * <p>
 * Strategy lets the algorithm vary independently from clients that use it.[2] Strategy is one of the patterns included
 * in the influential book Design Patterns by Gamma et al.[3] that popularized the concept of using design patterns
 * to describe how to design flexible and reusable object-oriented software. Deferring the decision about which
 * algorithm to use until runtime allows the calling code to be more flexible and reusable.
 * <p>
 * For instance, a class that performs validation on incoming data may use the strategy pattern to select a
 * validation algorithm depending on the type of data, the source of the data, user choice, or other discriminating factors.
 * These factors are not known until run-time and may require radically different validation to be performed. The validation
 * algorithms (strategies), encapsulated separately from the validating object, may be used by other validating objects in
 * different areas of the system (or even different systems) without code duplication.
 * <p>
 * Typically the strategy pattern stores a reference to some code in a data structure and retrieves it. This can be
 * achieved by mechanisms such as the native function pointer, the first-class function, classes or class instances
 * in object-oriented programming languages, or accessing the language implementation's internal storage of code via
 * reflection.
 * <p>
 * ----------------------------------------------------------------------------------------------------------------------------------
 * According to the strategy pattern, the behaviors of a class should not be inherited. Instead they should be encapsulated using interfaces.
 * This is compatible with the open/closed principle (OCP), which proposes that classes should be open for extension but closed for modification.
 * <p>
 * As an example, consider a car class. Two possible functionality for car are brake and accelerate. Since accelerate and brake
 * behaviors change frequently between models, a common approach is to implement these behaviors in subclasses. This approach has
 * significant drawbacks: accelerate and brake behaviors must be declared in each new Car model. The work of managing these behaviors
 * increases greatly as the number of models increases, and requires code to be duplicated across models. Additionally, it is not
 * easy to determine the exact nature of the behavior for each model without investigating the code in each.
 * <p>
 * The strategy pattern uses composition instead of inheritance. In the strategy pattern, behaviors are defined as separate
 * interfaces and specific classes that implement these interfaces. This allows better decoupling between the behavior and the
 * class that uses the behavior. The behavior can be changed without breaking the classes that use it, and the classes can
 * switch between behaviors by changing the specific implementation used without requiring any significant code changes.
 * Behaviors can also be changed at run-time as well as at design-time.
 * -----------------------------------------------------------------------------------------------------------------------------------
 *
 * @author angel.beshirov
 */

interface Strategy {
    int getPriceFor(int price);

    static Strategy normalPrice() {
        return x -> x;
    }

    static Strategy happyHourPrice() {
        return x -> (int) (x * 0.5);
    }
}

class Bar {
    private Strategy strategy;

    public Bar(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int getPriceFor(int price) {
        return strategy.getPriceFor(price);
    }
}

class DriverStrategy {
    public static void main(String... args) {
        Bar bar = new Bar(Strategy.normalPrice());

        System.out.println(bar.getPriceFor(11));
        System.out.println(bar.getPriceFor(15));

        bar.setStrategy(Strategy.happyHourPrice());
        System.out.println(bar.getPriceFor(11));
        System.out.println(bar.getPriceFor(15));
    }
}
