package com.design.patterns.structural;

/**
 * In computer programming, the proxy pattern is a software design pattern. A proxy, in its most general form,
 * is a class functioning as an interface to something else. The proxy could interface to anything: a network connection,
 * a large object in memory, a file, or some other resource that is expensive or impossible to duplicate. In short,
 * a proxy is a wrapper or agent object that is being called by the client to access the real serving object behind the scenes.
 * Use of the proxy can simply be forwarding to the real object, or can provide additional logic. In the proxy, extra
 * functionality can be provided, for example caching when operations on the real object are resource intensive, or
 * checking preconditions before operations on the real object are invoked. For the client, usage of a proxy object
 * is similar to using the real object, because both implement the same interface.
 * ---------------------------------------------------------------------------------------------------------------------
 * The Proxy design pattern is one of the twenty-three well-known GoF design patterns that describe how to
 * solve recurring design problems to design flexible and reusable object-oriented software, that is,
 * objects that are easier to implement, change, test, and reuse.
 * <p>
 * What problems can the Proxy design pattern solve?
 * 1) The access to an object should be controlled.
 * 2) Additional functionality should be provided when accessing an object.
 * When accessing sensitive objects, for example, it should be possible to check that clients have the needed access rights.
 * <p>
 * What solution does the Proxy design pattern describe?
 * Define a separate Proxy object that:
 * <p>
 * 1) Can be used as substitute for another object (Subject) and
 * 2) Implements additional functionality to control the access to this subject.
 * This enables to work through a Proxy object to perform additional functionality when accessing a subject.
 * <p>
 * For example, to check the access rights of clients accessing a sensitive object.
 * To act as substitute for a subject, a proxy must implement the Subject interface. Clients can't tell whether
 * they work with a subject or its proxy.
 *
 * @author angel.beshirov
 */
interface PngImage {
    void display();
}

class RealImage implements PngImage {

    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadImage();
    }

    @Override
    public void display() {
        System.out.println("Displaying image through real image: " + fileName);
    }

    private void loadImage() {
        System.out.printf("Loading %s...\n", fileName);
    }
}

class ProxyImage implements PngImage {

    private final String fileName;
    private PngImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        System.out.println("Delegating call through proxy to real image.");
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

class ProxyDriver {

    public static void main(String... args) {
        PngImage image1 = new ProxyImage("PNG_IMG123.png");
        PngImage image2 = new ProxyImage("PNG_IMG1234.png");

        image1.display();
        image1.display();

        image2.display();
        image2.display();
    }
}
