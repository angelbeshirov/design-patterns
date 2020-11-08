package design.behavioural;

import java.util.HashMap;
import java.util.Map;

/**
 * Command design pattern is used when we have a receiver object which encapsulates all the needed information
 * to perform a particular action and then we have a invoker object, which invokes different commands
 * on this object. The command holds a reference to the receiver object.
 *
 * @author angel.beshirov
 */

class Invoker {
    private final Map<String, Command> commands;

    public Invoker() {
        this.commands = new HashMap<>();
    }

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public void executeCommand(String commandName) {
        Command commandToExecute = commands.get(commandName);

        if (commandToExecute == null) {
            throw new IllegalArgumentException("No such command");
        }
        commandToExecute.execute();
    }
}

interface Command {
    void execute();
}

class LogReader {
    public int biggestResponse() {
        System.out.println("Returning biggest response size " + 10);
        return 10;
    }

    public int countHighestFails() {
        System.out.println("Returning highest fails " + 5);
        return 5;
    }
}

class CommandToGetBiggestResponse implements Command {

    private LogReader logReader;

    public CommandToGetBiggestResponse(LogReader logReader) {
        this.logReader = logReader;
    }

    @Override
    public void execute() {
        logReader.biggestResponse();
    }
}

class CommandToGetMostFails implements Command {

    private LogReader logReader;

    public CommandToGetMostFails(LogReader logReader) {
        this.logReader = logReader;
    }

    @Override
    public void execute() {
        logReader.countHighestFails();
    }
}

class TestCommand {
    public static void main(String... args) {
        LogReader logReader = new LogReader();

        Command mostFails = new CommandToGetMostFails(logReader);
        Command biggestResponse = new CommandToGetBiggestResponse(logReader);

        Invoker invoker = new Invoker();
        invoker.registerCommand("biggestResponse", biggestResponse);
        invoker.registerCommand("getMostFails", mostFails);

        invoker.executeCommand("getMostFails");
    }
}
