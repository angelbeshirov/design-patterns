package design.creational;

/**
 * @author angel.beshirov
 */

class Shop {
    private static final Shop INSTANCE = new Shop();

    private Shop() {}

    public static Shop getInstance() {
        return INSTANCE;
    }

    public void printSomething() {
        System.out.println("DO STH");
    }
}

public class Singleton {

    public static void main(String... args) {
        Shop.getInstance().printSomething();
    }

}
