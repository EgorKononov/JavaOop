package ru.academits.java.kononov.main;

import ru.academits.java.kononov.range.Range;

import java.util.Scanner;

public class Main {
    private static Range createRange2() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Первый диапазон чисел: от 0 до 100");

        System.out.println("Введите начальное число второго диапазона:");
        double range2From = scanner.nextDouble();

        System.out.println("Введите конечное число второго диапазона:");
        double range2To = scanner.nextDouble();

        return new Range(range2From, range2To);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Какой метод класса Range вы хотите проверить?");
        System.out.println("1 - Конструктор, вычисление длины диапазона, принадлежность числа диапазону, геттеры, сеттеры");
        System.out.println("2 - Пересечение диапазонов");
        System.out.println("3 - Объединение диапазонов");
        System.out.println("4 - Разность диапазонов");

        int methodCheckOption = scanner.nextInt();

        Range range1 = new Range(0, 100);

        if (methodCheckOption == 1) {
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

        } else if (methodCheckOption == 2) {
            Range range2 = createRange2();

            Range intersectionRange = range1.intersection(range2);

            if (intersectionRange == null) {
                System.out.println("У данных диапазонов нет пересечения");
            } else {
                System.out.printf("Пересечением данных диапазонов является диапазон от %f до %f",
                        intersectionRange.getFrom(), intersectionRange.getTo());
            }

        } else if (methodCheckOption == 3) {
            Range range2 = createRange2();

            Range[] unionRange = range1.union(range2);

            if (unionRange.length == 1) {
                System.out.printf("Объединением данных диапазонов является диапазон от %f до %f",
                        unionRange[0].getFrom(), unionRange[0].getTo());
            } else {
                System.out.printf("Объединением данных диапазонов являются диапазоны от %f до %f и от %f до %f",
                        unionRange[0].getFrom(), unionRange[0].getTo(), unionRange[1].getFrom(), unionRange[1].getTo());
            }

        } else if (methodCheckOption == 4) {
            Range range2 = createRange2();

            Range[] complementRange = range1.complement(range2);

            if (complementRange == null) {
                System.out.println("У данных массивов нет разности");
            } else if (complementRange.length == 1) {
                System.out.printf("Разностью данных диапазонов является диапазон от %f до %f",
                        complementRange[0].getFrom(), complementRange[0].getTo());
            } else {
                System.out.printf("Разностью данных диапазонов являются диапазоны от %f до %f и от %f до %f",
                        complementRange[0].getFrom(), complementRange[0].getTo(), complementRange[1].getFrom(), complementRange[1].getTo());
            }
            
        } else {
            System.out.println("Введен направильный вариант");
        }
    }
}
