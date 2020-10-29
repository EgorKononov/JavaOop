package ru.academits.java.kononov.shapes;

public class Square implements Shape {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return width;
    }

    @Override
    public double getArea() {
        return Math.pow(width, 2);
    }

    @Override
    public double getPerimeter() {
        return width * 4;
    }

    @Override
    public String toString() {
        return "(" + width + ", " + this.getArea() + ", " + this.getPerimeter() + ")";
    }

    @Override
    public int hashCode() {
        return Double.hashCode(width);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }

        if (o == null || o.getClass() != this.getClass()){
            return false;
        }

        Square square = (Square) o;

        return width == square.width;
    }
}
