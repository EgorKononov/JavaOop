package ru.academits.java.kononov.comparators;

import ru.academits.java.kononov.shapes.Shape;

import java.util.Comparator;

public class SortByPerimeter implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return (int) (shape1.getPerimeter() - shape2.getPerimeter());
    }
}
