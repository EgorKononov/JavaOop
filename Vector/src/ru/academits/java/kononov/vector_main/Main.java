package ru.academits.java.kononov.vector_main;

import ru.academits.java.kononov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Конструктор 1");
        Vector vector1 = new Vector(3);
        System.out.println(vector1);

        System.out.println("Конструктор 2");
        double[] components = {1, 2, 3};
        Vector vector2 = new Vector(components);
        System.out.println(vector2);

        System.out.println("Конструктор 3");
        Vector vector3 = new Vector(vector2);
        System.out.println(vector3);

        System.out.println("Конструктор 4");
        Vector vector4 = new Vector(4, components);
        System.out.println(vector4);

        double[] components2 = {1, 2, 3, 4};
        Vector vector5 = new Vector(components2);

        System.out.println("Прибавление к вектору другого вектора");
        System.out.println(vector2.add(vector5));

        System.out.println("Вычитание из вектора другого вектора");
        System.out.println(vector2.subtract(vector5));

        System.out.println("Умножение вектора на скаляр");
        System.out.println(vector2.scalarMultiply(2));

        System.out.println("Получение длины вектора");
        System.out.println(vector2.getLength());

        System.out.println("Получение компоненты вектора по индексу");
        System.out.println(vector2.getComponent(2));

        System.out.println("Установка компоненты вектора по индексу");
        vector2.setComponent(2, 5);
        System.out.println(vector2);

        System.out.println("Сложение двух векторов ");
        System.out.println(Vector.getAddition(vector2, vector5));

        System.out.println("Вычитание векторов");
        System.out.println(Vector.getSubtraction(vector2, vector5));

        System.out.println("Скалярное произведение векторов");
        System.out.println(Vector.getScalarMultiplication(vector2, vector5));
    }
}
