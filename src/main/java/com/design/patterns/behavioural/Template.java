package com.design.patterns.behavioural;

/**
 * The template method is a method in a superclass, usually an abstract superclass, and defines the skeleton
 * of an operation in terms of a number of high-level steps. These steps are themselves implemented by additional
 * helper methods in the same class as the template method.
 *
 * The helper methods may be either abstract methods, for which case subclasses are required to provide concrete
 * implementations, or hook methods, which have empty bodies in the superclass. Subclasses can (but are not required to)
 * customize the operation by overriding the hook methods. The intent of the template method is to define the overall
 * structure of the operation, while allowing subclasses to refine, or redefine, certain steps.
 *
 *
 * This pattern has two main parts:
 *
 * The "template method" is implemented as a method in a base class (usually an abstract class).
 * This method contains code for the parts of the overall algorithm that are invariant. The template ensures that
 * the overarching algorithm is always followed.[1] In the template method, portions of the algorithm that may vary
 * are implemented by sending self messages that request the execution of additional helper methods. In the base class,
 * these helper methods are given a default implementation, or none at all (that is, they may be abstract methods).
 *
 * Subclasses of the base class "fill in" the empty or "variant" parts of the "template" with specific algorithms that
 * vary from one subclass to another.[3] It is important that subclasses do not override the template method itself.
 * At run-time, the algorithm represented by the template method is executed by sending the template message to an instance
 * of one of the concrete subclasses. Through inheritance, the template method in the base class starts to execute.
 * When the template method sends a message to self requesting one of the helper methods, the message will be received
 * by the concrete sub-instance. If the helper method has been overridden, the overriding implementation in the sub-instance
 * will execute; if it has not been overridden, the inherited implementation in the base class will execute. This mechanism
 * ensures that the overall algorithm follows the same steps every time, while allowing the details of some steps to depend
 * on which instance received the original request to execute the algorithm.
 *
 * This pattern is an example of inversion of control because the high-level code no longer determines what algorithms to run;
 * a lower-level algorithm is instead selected at run-time.
 *
 * Some of the self messages sent by the template method may be to hook methods. These methods are implemented in the same
 * base class as the template method, but with empty bodies (i.e., they do nothing). Hook methods exist so that subclasses
 * can override them, and can thus fine-tune the action of the algorithm without the need to override the template method itself.
 * In other words, they provide a "hook" on which to "hang" variant implementations.
 * @author angel.beshirov
 */

abstract class GameTemplate {
    public abstract void initializeGame();
    public abstract void startGame();
    public abstract void endGame();

    public final void play() {
        System.out.println("Initializing game:");
        initializeGame();

        System.out.println("Starting game:");
        startGame();

        System.out.println("Ending game:");
        endGame();
    }
}

class Mario extends GameTemplate {

    @Override
    public void initializeGame() {
        System.out.println("Initializing mario game");
    }

    @Override
    public void startGame() {
        System.out.println("Starting mario game");
    }

    @Override
    public void endGame() {
        System.out.println("Ending mario game");
    }
}

class Tetris extends GameTemplate {

    @Override
    public void initializeGame() {
        System.out.println("Initializing tetris game");
    }

    @Override
    public void startGame() {
        System.out.println("Starting tetris game");
    }

    @Override
    public void endGame() {
        System.out.println("Ending tetris game");
    }
}

class TemplateDriver {
    /**
     * Good example of inversion of control
     */
    public static void main(String... args) {
        GameTemplate game = new Mario();
        game.play();

        game = new Tetris();
        game.play();
    }
}
