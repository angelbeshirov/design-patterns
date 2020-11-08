package design.structural;

/**
 * The purpose of this design pattern is to adapt one interface to be used as another one.
 *
 * For comparison the decorator design pattern is used to add new functionality to an existing interface.
 * This design pattern is used only for adaptation without any additional functionality added.
 *
 * @author angel.beshirov
 */
interface TwoDimensionalShape {
    void draw();
    void resize(int factor);
    int getArea();
}

interface ThreeDimensionalShape {
    void draw();
    void resize();
    int getVolume();
}

class Triangle implements TwoDimensionalShape {

    @Override
    public void draw() {
        System.out.println("Drawing triangle");
    }

    @Override
    public void resize(int factor) {
        System.out.println("Resizing triangle by factor " + factor);
    }

    @Override
    public int getArea() {
        return 11;
    }
}

class Rectangle implements TwoDimensionalShape {

    @Override
    public void draw() {
        System.out.println("Drawing rectangle");
    }

    @Override
    public void resize(int factor) {
        System.out.println("Resizing rectangle by factor " + factor);
    }

    @Override
    public int getArea() {
        return 120;
    }
}

class Sphere implements ThreeDimensionalShape {

    @Override
    public void draw() {
        System.out.println("Drawing 3D sphere ");
    }

    @Override
    public void resize() {
        System.out.println("Resizing sphere");
    }

    @Override
    public int getVolume() {
        return 120;
    }
}

class Pyramid implements ThreeDimensionalShape {

    @Override
    public void draw() {
        System.out.println("Drawing 3D pyramid ");
    }

    @Override
    public void resize() {
        System.out.println("Resizing pyramid");
    }

    @Override
    public int getVolume() {
        return 120;
    }
}

class TwoDimensionalAdapter implements TwoDimensionalShape {
    private ThreeDimensionalShape threeDimensionalShape;

    public TwoDimensionalAdapter(ThreeDimensionalShape threeDimensionalShape) {
        this.threeDimensionalShape = threeDimensionalShape;
    }

    @Override
    public void draw() {
        System.out.println("Converting and drawing 3D shape into 2D");
    }

    @Override
    public void resize(int factor) {
        System.out.println("Converting and resizing 3D shape");
    }

    @Override
    public int getArea() {
        return 11;
    }
}

class TestAdapter {

    public static void resize2DByFactor2(TwoDimensionalShape twoDimensionalShape) {
        twoDimensionalShape.resize(2);
    }
    public static void main(String... args) {
        TwoDimensionalShape triangle = new Triangle();
        TwoDimensionalShape rectangle = new Rectangle();
        ThreeDimensionalShape sphere = new Sphere();
        ThreeDimensionalShape pyramid = new Pyramid();

        resize2DByFactor2(triangle);
        resize2DByFactor2(rectangle);

        // these 2 are being adapted
        resize2DByFactor2(new TwoDimensionalAdapter(sphere));
        resize2DByFactor2(new TwoDimensionalAdapter(pyramid));
    }
}
