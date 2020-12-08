package ru.academits.java.kononov.range_main;

import ru.academits.java.kononov.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Range createRange() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Первый диапазон: (0; 100)");

        System.out.println("Введите начальное число второго диапазона:");
        double from = scanner.nextDouble();

        System.out.println("Введите конечное число второго диапазона:");
        double to = scanner.nextDouble();

        return new Range(from, to);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Какой метод класса Range вы хотите проверить?");
        System.out.println("1 - Конструктор, вычисление длины диапазона, принадлежность числа диапазону, геттеры, сеттеры");
        System.out.println("2 - Пересечение диапазонов");
        System.out.println("3 - Объединение диапазонов");
        System.out.println("4 - Разность диапазонов");

        int option = scanner.nextInt();

        Range range1 = new Range(0, 100);

        if (option == 1) {
            System.out.println("Длина диапазона (0; 100) равна: " + range1.getLength());

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
        } else if (option == 2) {
            Range range2 = createRange();

            Range intersection = range1.getIntersection(range2);

            if (intersection == null) {
                System.out.println("У данных диапазонов нет пересечения");
            } else {
                System.out.println("Пересечением данных диапазонов является диапазон " + intersection.toString());
            }
        } else if (option == 3) {
            Range range2 = createRange();

            Range[] union = range1.getUnion(range2);

            if (union.length == 1) {
                System.out.println("Объединением данных диапазонов является диапазон " + union[0].toString());
            } else {
                System.out.println("Объединением данных диапазонов являются диапазоны: " + Arrays.toString(union));
            }
        } else if (option == 4) {
            Range range2 = createRange();

            Range[] complement = range1.getComplement(range2);

            if (complement.length == 0) {
                System.out.println("У данных массивов нет разности");
            } else if (complement.length == 1) {
                System.out.println("Разностью данных диапазонов является диапазон " + complement[0].toString());
            } else {
                System.out.println("Разностью данных диапазонов являются диапазоны: " + Arrays.toString(complement));
            }
        } else {
            System.out.println("Введен неправильный вариант");
        }
    }
}
