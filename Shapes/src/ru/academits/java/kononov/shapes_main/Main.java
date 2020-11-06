package ru.academits.java.kononov.shapes_main;

import ru.academits.java.kononov.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(10),
                new Square(20),
                new Triangle(10, 5, 6, 7, 9, -2),
                new Triangle(5, 1, 8, 7, 2, 6),
                new Rectangle(10, 20),
                new Rectangle(15, 10),
                new Circle(2),
                new Circle(3)
        };

        System.out.println("Массив фигур до сортировок:");
        System.out.println(Arrays.toString(shapes));

        Arrays.sort(shapes, new AreaComparator());
        System.out.println("Сортировка фигур по площади:");
        System.out.println(Arrays.toString(shapes));

        System.out.println("Фигура с максимальной площадью: " + shapes[shapes.length - 1]);

        Arrays.sort(shapes, new PerimeterComparator());
        System.out.println("Сортировка фигур по периметру:");
        System.out.println(Arrays.toString(shapes));

        System.out.println("Фигура со вторым по величине периметром: " + shapes[shapes.length - 2]);
    }
}
