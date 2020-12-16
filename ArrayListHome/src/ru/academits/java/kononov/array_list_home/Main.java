package ru.academits.java.kononov.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> strings = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome\\Input.txt"))) {
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
        }

        System.out.println(strings);

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        for (Iterator<Integer> i = numbers.iterator(); i.hasNext(); ) {
            int number = i.next();

            if (number % 2 == 0) {
                i.remove();
            }
        }

        System.out.println(numbers);

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 5, 2, 6, 7, 1, 8, 9, 5, 10));

        for (int i = 0; i < numbers2.size(); ++i) {
            int checkableNumber = numbers2.get(i);

            for (Iterator<Integer> j = numbers2.listIterator(i + 1); j.hasNext(); ) {
                int number = j.next();

                if (number == checkableNumber) {
                    j.remove();
                }
            }

        }

        System.out.println(numbers2);
    }
}