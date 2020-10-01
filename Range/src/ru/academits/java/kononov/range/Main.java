package ru.academits.java.kononov.range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Range range1 = new Range(0, 100);
        System.out.println("Длина диапазона чисел от 0 до 100 равна: " + range1.getLength());

        System.out.println("Введите начальное число диапазона:");
        double range2From = scanner.nextDouble();

        System.out.println("Введите конечное число диапазона:");
        double range2To = scanner.nextDouble();

        Range range2 = new Range(range2From, range2To);

        System.out.printf("Длина диапазона чисел от %f до %f равна: %f%n", range2From, range2To, range2.getLength());

        System.out.println("Введите число для определения принадлежности данного числа диапазону:");
        double userNumber = scanner.nextDouble();

        if (range2.isInside(userNumber)) {
            System.out.printf("Число %f принадлежит диапазону от %f до %f%n", userNumber, range2From, range2To);
        } else {
            System.out.printf("Число %f не принадлежит диапазону от %f до %f%n", userNumber, range2From, range2To);
        }

        System.out.println("Введите новое начальное число диапазона:");
        range2.setFrom(scanner.nextDouble());

        System.out.println("Введите новое конечное число диапазона:");
        range2.setTo(scanner.nextDouble());

        System.out.printf("Длина диапазона чисел от %f до %f равна: %f%n", range2.getFrom(), range2.getTo(), range2.getLength());

        System.out.println("Введите число для определения принадлежности данного числа диапазону:");
        userNumber = scanner.nextDouble();

        if (range2.isInside(userNumber)) {
            System.out.printf("Число %f принадлежит диапазону от %f до %f", userNumber, range2.getFrom(), range2.getTo());
        } else {
            System.out.printf("Число %f не принадлежит диапазону от %f до %f", userNumber, range2.getFrom(), range2.getTo());
        }
    }
}
