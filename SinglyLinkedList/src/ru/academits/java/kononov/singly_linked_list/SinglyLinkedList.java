package ru.academits.java.kononov.singly_linked_list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    public SinglyLinkedList() {
    }

    private void checkIndex(int index, int limit) {
        if (index < 0 || index > limit) {
            throw new IndexOutOfBoundsException(index + " - неверное значение индекса. Индекс должен находится в " +
                    "пределах от 0 до " + limit);
        }
    }

    private ListItem<T> iterate(int index) {
        int i = 0;
        ListItem<T> item = head;

        while (i != index) {
            item = item.getNext();
            ++i;
        }

        return item;
    }

    public int getLength() {
        return length;
    }

    public T getFirst() {
        if (length == 0) {
            throw new NoSuchElementException("Невозможно получить первый элемент списка, т.к. список пуст");
        }

        return head.getData();
    }

    public T get(int index) {
        checkIndex(index, length - 1);

        return iterate(index).getData();
    }

    public T set(int index, T data) {
        checkIndex(index, length - 1);

        ListItem<T> item = iterate(index);

        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        ++length;
    }

    public void add(int index, T data) {
        checkIndex(index, length);

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<T> item = iterate(index-1);

        item.setNext(new ListItem<>(data, item.getNext()));
        ++length;
    }

    public T removeFirst() {
        if (length == 0) {
            throw new NoSuchElementException("Невозможно удалить первый элемент списка, т.к. список пуст");
        }

        T removedData = head.getData();
        head = head.getNext();
        --length;

        return removedData;
    }

    public T removeByIndex(int index) {
        checkIndex(index, length - 1);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> item = iterate(index - 1);

        T removedData = item.getNext().getData();
        item.setNext(item.getNext().getNext());
        --length;

        return removedData;
    }

    public boolean removeByData(T data) {
        if (data == null) {
            return false;
        }

        if (head.getData().equals(data)) {
            head = head.getNext();
            --length;

            return true;
        }

        for (ListItem<T> item = head.getNext(), prevItem = head; item != null; prevItem = item, item = item.getNext()) {
            if (item.getData().equals(data)) {
                prevItem.setNext(item.getNext());
                --length;

                return true;
            }
        }

        return false;
    }

    public void reverse() {
        if (length == 0) {
            return;
        }

        ListItem<T> item = head;
        head = new ListItem<>(item.getData());
        item = item.getNext();

        while (item != null) {
            head = new ListItem<>(item.getData(), head);
            item = item.getNext();
        }
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();

        if (length == 0) {
            return copy;
        }

        ListItem<T> item = head;
        copy.addFirst(item.getData());

        if (length == 1) {
            return copy;
        }

        ListItem<T> nextItem = item.getNext();
        copy.head.setNext(nextItem);

        if (length == 2) {
            copy.length = 2;

            return copy;
        }

        int i = 2;
        item = item.getNext();
        nextItem = item.getNext();

        while (i < length) {
            new ListItem<>(item.getData(), nextItem);
            item = item.getNext();
            nextItem = item.getNext();
            ++i;
        }

        copy.length = length;

        return copy;
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            sb.append(item.getData()).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");

        return sb.toString();
    }
}