package design.structural;

/**
 * The facade pattern is a software-design pattern commonly used in object-oriented programming.
 * Analogous to a facade in architecture, a facade is an object that serves as a front-facing interface masking more
 * complex underlying or structural code.
 * <p>
 * A facade can:
 * 1) Improve the readability and usability of a software library by masking interaction with more complex components
 * behind a single and simplified API.
 * 2) Provide a context-specific interface to more generic functionality.
 * 3) Serve as a launching point for a broader refactor of monolithic or tightly-coupled systems in favor of more loosely-coupled code
 * <p>
 * Developers often use the facade design pattern when a system is very complex or difficult to understand because the
 * system has many interdependent classes or because its source code is unavailable. This pattern hides the complexities
 * of the larger system and provides a simpler interface to the client. It typically involves a single wrapper class that
 * contains a set of members required by the client. These members access the system on behalf of the facade client and
 * hide the implementation details.
 * <p>
 * <p>
 * What problems can the Facade design pattern solve?
 * 1) To make a complex subsystem easier to use, a simple interface should be provided
 * for a set of interfaces in the subsystem.
 * 2) The dependencies on a subsystem should be minimized.
 * <p>
 * Clients that access a complex subsystem directly refer to many different objects having
 * different interfaces (tight coupling), which makes the clients hard to implement, change, test, and reuse.
 * <p>
 * What solution does the Facade design pattern describe?
 * 1) Define a Facade object that implements a simple interface in terms of (by delegating to) the interfaces in the subsystem and
 * may perform additional functionality before/after forwarding a request.
 *
 * @author angel.beshirov
 */

class CPU {
    public void execute(int iid) {
        System.out.println("Executing instruction id " + iid);
    }

    public void jump() {
        System.out.println("Jumping...");
    }

    public void freeze() {
        System.out.println("Freezing...");
    }
}

class HardDrive {
    byte[] read() {
        System.out.println("Reading...");
        return "test something".getBytes();
    }
}

class Memory {
    void load() {
        System.out.println("Loading into memory");
    }
}

class ComputerFacade {
    private CPU cpu;
    private HardDrive hardDrive;
    private Memory memory;

    public ComputerFacade(CPU cpu, HardDrive hardDrive, Memory memory) {
        this.cpu = cpu;
        this.hardDrive = hardDrive;
        this.memory = memory;
    }

    public void start() {
        cpu.freeze();
        hardDrive.read();
        memory.load();
        cpu.jump();
        cpu.execute(1);
    }
}

class FacadeDriver {

    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade(new CPU(), new HardDrive(), new Memory());

        computerFacade.start();
    }
}
