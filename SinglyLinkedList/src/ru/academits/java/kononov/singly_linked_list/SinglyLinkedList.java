package ru.academits.java.kononov.singly_linked_list;

import java.util.NoSuchElementException;

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

        if (data == null) {
            if (head.getData() == null) {
                head = head.getNext();
                --length;

                return true;
            }

            ListItem<T> item = head.getNext();
            ListItem<T> previousItem = head;

            for (int i = 1; i < length; ++i) {
                if (item.getData() == null) {
                    previousItem.setNext(item.getNext());
                    --length;

                    return true;
                }

                previousItem = item;
                item = item.getNext();
            }
        }

        if (head.getData().equals(data)) {
            head = head.getNext();
            --length;

            return true;
        }

        ListItem<T> item = head.getNext();
        ListItem<T> previousItem = head;

        for (int i = 1; i < length; ++i) {
            if (item.getData() != null) {
                if (item.getData().equals(data)) {
                    previousItem.setNext(item.getNext());
                    --length;

                    return true;
                }
            }

            previousItem = item;
            item = item.getNext();
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

        ListItem<T> item = head;
        copy.addFirst(item.getData());

        if (length == 1) {
            return copy;
        }

        ListItem<T> nextItem = item.getNext();
        copy.head.setNext(nextItem);

        item = item.getNext();
        nextItem = item.getNext();

        for (int i = 2; i < length; ++i) {
            new ListItem<>(item.getData(), nextItem);
            item = item.getNext();
            nextItem = item.getNext();
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