package ru.academits.java.kononov.singly_linked_list_main;

import ru.academits.java.kononov.singly_linked_list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        System.out.println("Вставка элементов в начало");
        list1.addFirst(0);
        System.out.println(list1);
        System.out.println();

        System.out.println("Вставка элементов по индексу");
        list1.add(1, 1);
        list1.add(2, 2);
        list1.add(3, 3);
        System.out.println(list1);
        System.out.println();

        System.out.println("Получение размера списка");
        System.out.println(list1.getLength());
        System.out.println();

        System.out.println("Получение значения первого элемента");
        System.out.println(list1.getFirst());
        System.out.println();

        System.out.println("Получение значения элемента по индексу");
        System.out.println(list1.get(2));
        System.out.println();

        System.out.println("Изменение элемента по индексу");
        System.out.println(list1.set(1, 5));
        System.out.println(list1);
        System.out.println();

        System.out.println("Удаление элемента по индексу");
        System.out.println(list1.removeByIndex(2));
        System.out.println(list1);
        System.out.println();

        System.out.println("Удаление элемента по значению");
        System.out.println(list1.removeByData(3));
        System.out.println(list1);
        System.out.println();

        System.out.println("Удаление первого элемента");
        System.out.println(list1.removeFirst());
        System.out.println(list1);
        System.out.println();

        System.out.println("Разворот списка");
        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        list2.add(0, 0);
        list2.add(1, 1);
        list2.add(2, 2);
        list2.add(3, 3);
        System.out.println(list2);
        list2.reverse();
        System.out.println(list2);
        System.out.println();

        System.out.println("Копирование списка");
        System.out.println(list2);
        SinglyLinkedList<Integer> list3 = list2.copy();
        System.out.println(list3);
    }
}