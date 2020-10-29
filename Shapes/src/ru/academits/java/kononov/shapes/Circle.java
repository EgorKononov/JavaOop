package ru.academits.java.kononov.shapes;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "(" + radius + ", " + this.getArea() + ", " + this.getPerimeter() + ")";
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }

        if (o == null || o.getClass() != this.getClass()){
            return false;
        }

        Circle circle = (Circle) o;

        return radius == circle.radius;
    }
}
