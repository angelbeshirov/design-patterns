package com.design.patterns.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * In software engineering, the composite pattern is a partitioning design pattern. The composite pattern describes
 * a group of objects that are treated the same way as a single instance of the same type of object. The intent of
 * a composite is to "compose" objects into tree structures to represent part-whole hierarchies. Implementing the
 * composite pattern lets clients treat individual objects and compositions uniformly.
 * <p>
 * <p>
 * What problems can the Composite design pattern solve?
 * 1) A part-whole hierarchy should be represented so that clients can treat part and whole objects uniformly.
 * 2) A part-whole hierarchy should be represented as tree structure.
 * When defining Part objects and Whole objects that act as containers for Part objects, clients must treat them separately,
 * which complicates client code.
 * <p>
 * What solution does the Composite design pattern describe?
 * 1) Define a unified Component interface for both part (Leaf) objects and whole (Composite) objects.
 * 2) Individual Leaf objects implement the Component interface directly, and Composite objects forward requests
 * to their child components.
 * <p>
 * This enables clients to work through the Component interface to treat Leaf and Composite objects uniformly:
 * Leaf objects perform a request directly, and Composite objects forward the request to their child components
 * recursively downwards the tree structure. This makes client classes easier to implement, change, test, and reuse.
 * <p>
 * When to use?
 * Composite should be used when clients ignore the difference between compositions of objects and individual objects.
 * If programmers find that they are using multiple objects in the same way, and often have nearly identical code to
 * handle each of them, then composite is a good choice; it is less complex in this situation to treat primitives and
 * composites as homogeneous.
 *
 * @author angel.beshirov
 */
interface Graphic {
    void print();
}


/**
 * Leaf
 */
class Button implements Graphic {

    @Override
    public void print() {
        System.out.println("Button");
    }
}

/**
 * Leaf
 */
class Scroll implements Graphic {

    @Override
    public void print() {
        System.out.println("Scroll");
    }
}

/**
 * Composite
 */
class Page implements Graphic {
    private final List<Graphic> pageGraphics;
    private final String name;

    public Page(String name) {
        this.pageGraphics = new ArrayList<>();
        this.name = name;
    }

    void addGraphic(Graphic graphic) {
        pageGraphics.add(graphic);
    }

    @Override
    public void print() {
        System.out.printf("Page %s, children: \n", name);
        for (Graphic graphic : pageGraphics) {
            graphic.print(); // delegation (group of objects treated as single instance of the object)
        }
    }
}


class CompositeDriver {
    public static void main(String... args) {
        Graphic button1 = new Button();
        Graphic button2 = new Button();
        Graphic button3 = new Button();
        Graphic button4 = new Button();

        Graphic scroll = new Scroll();
        Graphic scroll2 = new Scroll();

        Page page1 = new Page("Page 1");
        page1.addGraphic(button1);
        page1.addGraphic(button2);
        page1.addGraphic(button3);
        page1.addGraphic(scroll);

        Page page2 = new Page("Page 2");
        page2.addGraphic(button4);
        page2.addGraphic(scroll2);

        Page mainPage = new Page("Main page");
        mainPage.addGraphic(page1);
        mainPage.addGraphic(page2);

        mainPage.print();


    }
}
