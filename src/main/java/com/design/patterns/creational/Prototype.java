package design.creational;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Version in which we use the clone method of the Object class.
 *
 * Another option would be to add a clone method in the Tree class which returns a Tree directly instead of object
 * And use copy constructor to clone it. (probably better)
 * @author angel.beshirov
 */
abstract class Tree implements Cloneable {
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Object clone() {
        Object result = null;
        try {
            result = super.clone();
        } catch (CloneNotSupportedException e) {
            // logging
            System.out.println("Error");
        }

        return result;
    }
}

class ChristmasTree extends Tree {

    private int price;

    public ChristmasTree(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Clone returns a shallow copy, which is okay in this case.
     */
    @Override
    public Object clone() {
        return super.clone();
    }
}

class OldTree extends Tree {

    private int age;

    public OldTree(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Clone returns a shallow copy, which is okay in this case.
     */
    @Override
    public Object clone() {
        return super.clone();
    }
}

class TestPrototype {
    public static void main(String... args) {

        List<Tree> someTrees = Arrays.asList(new ChristmasTree(100), new OldTree(200));

        List<Tree> generateNewTrees = someTrees.stream()
                .map(Tree::clone)
                .map((Object s) -> (Tree) s)
                .collect(Collectors.toList());
    }
}
