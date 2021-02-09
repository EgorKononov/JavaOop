package ru.academits.java.kononov.singly_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    public SinglyLinkedList() {
    }

    private void checkIndex(int index, int maxIndex) {
        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException(index + " - неверное значение индекса. Индекс должен находится в " +
                    "пределах от 0 до " + maxIndex);
        }
    }

    private ListItem<T> getItem(int index) {
        ListItem<T> item = head;

        for (int i = 0; i != index; ++i) {
            item = item.getNext();
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

        return getItem(index).getData();
    }

    public T set(int index, T data) {
        checkIndex(index, length - 1);

        ListItem<T> item = getItem(index);

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

        ListItem<T> previousItem = getItem(index - 1);

        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
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

        ListItem<T> previousItem = getItem(index - 1);

        T removedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());
        --length;

        return removedData;
    }

    public boolean removeByData(T data) {
        if (length == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            head = head.getNext();
            --length;

            return true;
        }

        ListItem<T> currentItem = head.getNext();
        ListItem<T> previousItem = head;

        for (int i = 1; i < length; ++i) {
            if (Objects.equals(currentItem.getData(), data)) {
                previousItem.setNext(currentItem.getNext());
                --length;

                return true;
            }

            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }

        return false;
    }

    public void reverse() {
        if (length == 0) {
            return;
        }

        ListItem<T> previousItem = null;
        ListItem<T> item = head;
        ListItem<T> nextItem = head.getNext();

        while (nextItem != null) {
            item.setNext(previousItem);
            previousItem = item;
            item = nextItem;
            nextItem = nextItem.getNext();
        }

        item.setNext(previousItem);
        head = item;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();

        if (length == 0) {
            return copy;
        }

        ListItem<T> currentItem = head;
        copy.addFirst(currentItem.getData());

        if (length == 1) {
            return copy;
        }

        ListItem<T> copyPreviousItem = copy.head;

        for (int i = 2; i < length; ++i) {
            currentItem = currentItem.getNext();
            ListItem<T> nextItem = currentItem.getNext();

            ListItem<T> copyNextItem = new ListItem<>(nextItem.getData());
            ListItem<T> copyCurrentItem = new ListItem<>(currentItem.getData(), copyNextItem);
            copyPreviousItem.setNext(copyCurrentItem);

            copyPreviousItem = copyCurrentItem;
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