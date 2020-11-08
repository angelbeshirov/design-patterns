package com.design.patterns.behavioural;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * Chain of responsibility use cases:
 * 1) When you want to decouple a request’s sender and receiver.
 * 2) Multiple objects, determined at runtime, are candidates to handle a request.
 * 3) When you don’t want to specify handlers explicitly in your code.
 * 4) When you want to issue a request to one of several objects without specifying the receiver explicitly.
 *
 * @author angel.beshirov
 */
@FunctionalInterface
interface Logger {
    void writeMessage(String msg, Level level);

    default Logger appendNext(Logger logger) {
        return (msg, level) -> {
            writeMessage(msg, level);
            if (logger != null) {
                logger.writeMessage(msg, level);
            }
        };
    }
}

enum Level {
    ERROR, WARNING, INFO, DEBUG, TRACE
}

class ConsoleLogger implements Logger {

    private EnumSet<Level> levels;

    public ConsoleLogger(Level... levels) {
        this.levels = EnumSet.copyOf(Arrays.asList(levels));
    }

    @Override
    public void writeMessage(String msg, Level level) {
        if (levels.contains(level)) {
            System.out.printf("Writing %s to console\n", msg);
        }
    }
}

class EmailLogger implements Logger {

    private EnumSet<Level> levels;

    public EmailLogger(Level... levels) {
        this.levels = EnumSet.copyOf(Arrays.asList(levels));
    }

    @Override
    public void writeMessage(String msg, Level level) {
        if (levels.contains(level)) {
            System.out.printf("Writing %s to email\n", msg);
        }
    }
}

class FileLogger implements Logger {

    private EnumSet<Level> levels;

    public FileLogger(Level... levels) {
        this.levels = EnumSet.copyOf(Arrays.asList(levels));
    }

    @Override
    public void writeMessage(String msg, Level level) {
        if (levels.contains(level)) {
            System.out.printf("Writing %s to file\n", msg);
        }
    }
}

class ChainOfResponsibility {

    public static void main(String... args) {
        Logger loggerChain = new ConsoleLogger(Level.values())
                .appendNext(new EmailLogger(Level.ERROR))
                .appendNext(new FileLogger(Level.INFO));

        loggerChain.writeMessage("Test error", Level.ERROR);
        loggerChain.writeMessage("Test info", Level.INFO);
    }
}
