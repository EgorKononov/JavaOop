package ru.academits.java.kononov.lambda_expressions_main;

import ru.academits.java.kononov.person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Иван", 23),
                new Person("Семен", 23),
                new Person("Ольга", 6),
                new Person("Елена", 16),
                new Person("Иван", 64),
                new Person("Наталья", 35),
                new Person("Елена", 41),
                new Person("Алексей", 24),
                new Person("Антон", 12)
        );

        System.out.println("Список людей");
        System.out.println(persons);

        System.out.println("Список уникальных имен списком");

        List<String> distinctNamesList = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(distinctNamesList);

        System.out.println("Список уникальных имен строкой");

        String distinctNamesString = distinctNamesList.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(distinctNamesString);

        System.out.println("Средний возраст людей младше 18 лет");

        OptionalDouble averageAge = persons.stream()
                .filter(p -> p.getAge() < 18)
                .mapToInt(Person::getAge)
                .average();

        if (averageAge.isPresent()) {
            System.out.println(averageAge.getAsDouble());
        } else {
            System.out.println("В списке нет людей младше 18 лет");
        }

        System.out.println("Map, в котором ключи - это имена, а значения - средний возраст");

        Map<String, Double> personsMap = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println(personsMap);

        System.out.println("Список людей, возраст которых от 20 до 45, в порядке убывания возраста");

        String sortedNamesString = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", "));

        System.out.println(sortedNamesString);
    }
}