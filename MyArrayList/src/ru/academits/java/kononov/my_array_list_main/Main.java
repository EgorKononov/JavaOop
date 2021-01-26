package ru.academits.java.kononov.my_array_list_main;

import ru.academits.java.kononov.my_array_list.MyArrayList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Конструктор без аргументов");
        MyArrayList<Integer> list1 = new MyArrayList<>();
        System.out.println(list1);

        System.out.println("Конструктор, принимающий вместимость");
        MyArrayList<Double> list2 = new MyArrayList<>(10);
        System.out.println(list2);

        System.out.println("Добавление элемента в конец списка");
        System.out.println(list1.add(0));
        System.out.println(list1);

        System.out.println("Добавление элемента по индексу");
        list1.add(1, 1);
        list1.add(2, 2);
        list1.add(3, 3);
        list1.add(4, 4);
        System.out.println(list1);

        System.out.println("Получение элемента по индексу");
        System.out.println(list1.get(3));

        System.out.println("Изменение элемента по индексу");
        System.out.println(list1.set(4, 2));
        System.out.println(list1);

        System.out.println("Получение индекса первого совпадающего элемента");
        System.out.println(list1.indexOf(2));

        System.out.println("Получение индекса последнего совпадающего элемента");
        System.out.println(list1.lastIndexOf(2));

        System.out.println("Проверка на содержание списком объекта");
        System.out.println(list1.contains(1));

        System.out.println("Проверка на содержание списком коллекции");
        List<Integer> list3 = Arrays.asList(1, 2, 3);
        System.out.println(list1.containsAll(list3));

        System.out.println("Добавление коллекции в конец списка");
        MyArrayList<Number> list4 = new MyArrayList<>();
        list2.add(0, 1.0);
        list2.add(1, 1.1);
        list2.add(2, 1.2);
        list2.add(3, 1.3);
        System.out.println(list4.addAll(list2));
        System.out.println(list4);

        System.out.println("Добавление коллекции в список по индексу");
        System.out.println(list4.addAll(1, list1));
        System.out.println(list4);

        System.out.println("Удаление элемента из списка");
        System.out.println(list4.remove(1.2));
        System.out.println(list4);

        System.out.println("Удаление элемента из списка по индексу");
        System.out.println(list4.remove(1));
        System.out.println(list4);

        System.out.println("Удаление из списка элементов, содержащихся в коллекции");
        System.out.println(list4);
        System.out.println(list3);
        System.out.println(list4.removeAll(list3));
        System.out.println(list4);

        System.out.println("Удаление из списка элементов, не содержащихся в коллекции");
        System.out.println(list1);
        System.out.println(list3);
        System.out.println(list1.retainAll(list3));
        System.out.println(list1);

        System.out.println("Удаление всех элементов из списка");
        list1.clear();
        System.out.println(list1);

        System.out.println("Обрезание вместимости по размеру списка");
        list2.trimToSize();
        System.out.println(list2);

        System.out.println("Изменение вместимости списка");
        list2.ensureCapacity(100);
        System.out.println(list2);

        System.out.println("Получение размера списка");
        System.out.println(list2);
        System.out.println(list2.size());

        System.out.println("Проверка на то, является ли список пустым");
        System.out.println(list2);
        System.out.println(list2.isEmpty());
        System.out.println(list1);
        System.out.println(list1.isEmpty());

        System.out.println("Проход по списку итератором");

        for (double element : list2) {
            System.out.println(element);
        }

        System.out.println("Получение из списка массива типа Object");
        Object[] array1 = list2.toArray();
        System.out.println(Arrays.toString(array1));

        System.out.println("Получение из списка массива указанного типа");
        Number[] array3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        list2.toArray(array3);
        System.out.println(Arrays.toString(array3));
    }
}