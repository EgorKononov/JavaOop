package ru.academits.java.kononov.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> getStringsFromFile(String fileName) {
        ArrayList<String> stringsFromFile = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                stringsFromFile.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Неправильно указан путь к файлу");
        }

        return stringsFromFile;
    }

    public static void removeEvenNumbersFromAList(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                --i;
            }
        }
    }

    public static ArrayList<Integer> getListWithoutRepeatedNumbers(ArrayList<Integer> list) {
        ArrayList<Integer> listWithoutRepeatedNumbers = new ArrayList<>(list.size());

        for (Integer e : list) {
            if (!listWithoutRepeatedNumbers.contains(e)) {
                listWithoutRepeatedNumbers.add(e);
            }
        }

        return listWithoutRepeatedNumbers;
    }

    public static void main(String[] args) {
        ArrayList<String> stringsFromFile = getStringsFromFile("ArrayListHome\\Input.txt");
        System.out.println("Список на массиве со строками из файла:");
        System.out.println(stringsFromFile);
        System.out.println();

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Исходный список на массиве:");
        System.out.println(numbers);
        removeEvenNumbersFromAList(numbers);
        System.out.println("Список на массиве без четных чисел:");
        System.out.println(numbers);
        System.out.println();

        ArrayList<Integer> listWithRepeatedNumbers = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 5, 2, 6, 7, 1, 8, 9, 5, 10));
        System.out.println("Исходный список на массиве:");
        System.out.println(listWithRepeatedNumbers);
        ArrayList<Integer> listWithoutRepeatedNumbers = getListWithoutRepeatedNumbers(listWithRepeatedNumbers);
        System.out.println("Список на массиве без повторяющихся чисел:");
        System.out.println(listWithoutRepeatedNumbers);
    }
}