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

    public static void removeEvenNumbersFromArrayList(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) % 2 == 0) {
                arrayList.remove(i);
                --i;
            }
        }
    }

    public static ArrayList<Integer> getArrayListWithoutRepeatedNumbers(ArrayList<Integer> arrayList) {
        ArrayList<Integer> arrayListWithoutRepeatedNumbers = new ArrayList<>();

        for (Integer e1 : arrayList) {
            boolean isNumberRepeat = false;

            for (Integer e2 : arrayListWithoutRepeatedNumbers) {
                if (e1.equals(e2)) {
                    isNumberRepeat = true;
                    break;
                }
            }

            if(!isNumberRepeat){
                arrayListWithoutRepeatedNumbers.add(e1);
            }
        }

        return arrayListWithoutRepeatedNumbers;
    }


    public static void main(String[] args) {
        ArrayList<String> stringsFromFile = getStringsFromFile("ArrayListHome\\Input.txt");
        System.out.println("Список на массиве со строками из файла:");
        System.out.println(stringsFromFile);
        System.out.println();

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Исходный список на массиве:");
        System.out.println(numbers);
        removeEvenNumbersFromArrayList(numbers);
        System.out.println("Список на массиве без четных чисел:");
        System.out.println(numbers);
        System.out.println();

        ArrayList<Integer> arrayListWithRepeatedNumbers = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 5, 2, 6, 7, 1, 8, 9, 5, 10));
        System.out.println("Исходный список на массиве:");
        System.out.println(arrayListWithRepeatedNumbers);
        ArrayList<Integer> arrayListWithoutRepeatedNumbers = getArrayListWithoutRepeatedNumbers(arrayListWithRepeatedNumbers);
        System.out.println("Список на массиве без повторяющихся чисел:");
        System.out.println(arrayListWithoutRepeatedNumbers);
    }
}