package com.design.patterns.structural;

/**
 * Decorator design pattern is used to add or remove functionality from existing classes or interfaces.
 *
 * @author angel.beshirov
 */

interface Window {
    String getDescription();

    void draw();
}

class BasicWindow implements Window {

    @Override
    public String getDescription() {
        return "Basic window without any extra features";
    }

    @Override
    public void draw() {
        System.out.println("Drawing basic window");
    }
}

class WindowDecorator implements Window {

    private Window windowToBeDecorated;

    public WindowDecorator(Window windowToBeDecorated) {
        this.windowToBeDecorated = windowToBeDecorated;
    }

    @Override
    public String getDescription() {
        return windowToBeDecorated.getDescription();
    }

    @Override
    public void draw() {
        windowToBeDecorated.draw();
    }
}

class HorizontalScrollWindow extends WindowDecorator {

    public HorizontalScrollWindow(Window windowToBeDecorated) {
        super(windowToBeDecorated);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + horizontal scroll";
    }

    @Override
    public void draw() {
        // draw the horizontal scroll
        super.draw();
    }
}

class VerticalScrollWindow extends WindowDecorator {

    public VerticalScrollWindow(Window windowToBeDecorated) {
        super(windowToBeDecorated);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + vertical scroll";
    }

    @Override
    public void draw() {
        // draw the vertical scroll
        super.draw();
    }
}

class TestDecorator {

    public static void main(String... args) {
        Window simpleWindow = new BasicWindow();

        System.out.println(new VerticalScrollWindow(new HorizontalScrollWindow(simpleWindow)).getDescription());
    }
}
