package design.creational;

/**
 *
 *
 * @author angel.beshirov
 */
interface Car {
    void move();
    void park();
}

class OrdinaryCar implements Car {

    @Override
    public void move() {

    }

    @Override
    public void park() {

    }
}

class Bus implements Car {

    @Override
    public void move() {

    }

    @Override
    public void park() {

    }
}

class Truck implements Car {

    @Override
    public void move() {

    }

    @Override
    public void park() {

    }
}

/**
 * Specifically with the Factory pattern, no, there is no requirement that the factory methods be static.
 * The essence of the pattern is that you have one object which is responsible for creating instances of another class.
 *
 * Example is Calendar.getInstance(locale)
 *
 */
class CarFactory {
    public enum Type {
        ORDINARY_CAR, TRUCK, BUS
    }
    public static Car getCar(Type type) {
        switch (type) {
            case ORDINARY_CAR: {
                return new OrdinaryCar();
            }
            case TRUCK: {
                return new Truck();
            }
            case BUS: {
                return new Bus();
            }
            default: throw new UnsupportedOperationException();
        }
    }
}

class TestFactory {

    public static void main(String... args) {
        Car ordinaryCar = CarFactory.getCar(CarFactory.Type.ORDINARY_CAR);
    }
}
