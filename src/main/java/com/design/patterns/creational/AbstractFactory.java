package design.creational;

/**
 * @author angel.beshirov
 */

interface Button {
    void click();
}

class WindowsCloseButton implements Button {

    @Override
    public void click() {
        System.out.println("Windows close button clicked");
    }
}

class WindowsSendButton implements Button {

    @Override
    public void click() {
        System.out.println("Windows send button clicked");
    }
}

class WindowsRejectButton implements Button {

    @Override
    public void click() {
        System.out.println("Windows reject button clicked");
    }
}

class LinuxCloseButton implements Button {

    @Override
    public void click() {
        System.out.println("Linux close button clicked");
    }
}

class LinuxSendButton implements Button {

    @Override
    public void click() {
        System.out.println("Linux send button clicked");
    }
}

class LinuxRejectButton implements Button {

    @Override
    public void click() {
        System.out.println("Linux reject button clicked");
    }
}

enum ButtonType {
    CLOSE, SEND, REJECT;
}
abstract class AbstractFactory {
    abstract Button createButton(ButtonType type);
}

class WindowsFactory extends AbstractFactory {

    @Override
    Button createButton(ButtonType type) {
        switch (type) {
            case SEND: return new WindowsSendButton();
            case CLOSE: return new WindowsCloseButton();
            case REJECT: return new WindowsRejectButton();
            default: throw new UnsupportedOperationException();
        }
    }
}

class LinuxFactory extends AbstractFactory {

    @Override
    Button createButton(ButtonType type) {
        switch (type) {
            case SEND: return new LinuxSendButton();
            case CLOSE: return new LinuxCloseButton();
            case REJECT: return new LinuxRejectButton();
            default: throw new UnsupportedOperationException();
        }
    }
}

class TestAbstractFactory {
    public static void main(String[] args) {
        AbstractFactory windowsFactory = new WindowsFactory();

        Button button = windowsFactory.createButton(ButtonType.CLOSE);
        button.click();
    }
}