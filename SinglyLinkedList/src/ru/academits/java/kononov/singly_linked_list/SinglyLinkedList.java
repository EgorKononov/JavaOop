package ru.academits.java.kononov.singly_linked_list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getLength() {
        return count;
    }

    public T getHeadData() {
        return head.getData();
    }

    public T getListItemData(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException(index + " - неверное значение индекса для списка длины " + count);
        }

        int i = 0;
        ListItem<T> listItem = head;

        while (i != index) {
            listItem = listItem.getNext();
            ++i;
        }

        return listItem.getData();
    }

    public T setListItemData(int index, T data) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException(index + " - неверное значение индекса для списка длины " + count);
        }

        int i = 0;
        ListItem<T> listItem = head;

        while (i != index) {
            listItem = listItem.getNext();
            ++i;
        }

        ListItem<T> listItemOldValue = new ListItem<>(listItem.getData());
        listItem.setData(data);

        return listItemOldValue.getData();
    }

    public T removeListItem(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException(index + " - неверное значение индекса для списка длины " + count);
        }

        if (index == 0) {
            return removeHead();
        } else {
            int i = 0;
            ListItem<T> listItem = head;

            while (i != index - 1) {
                listItem = listItem.getNext();
                ++i;
            }

            ListItem<T> listItemOldValue = new ListItem<>(listItem.getNext().getData());
            listItem.setNext(listItem.getNext().getNext());
            --count;

            return listItemOldValue.getData();
        }
    }

    public void addHead(T data) {
        head = new ListItem<>(data, head);
        ++count;
    }

    public void addListItem(T data, int index) {
        if (index > count || index < 0) {
            throw new IllegalArgumentException(index + " - неверное значение индекса для списка длины " + count);
        }

        if (index == 0) {
            addHead(data);
        } else {
            int i = 0;
            ListItem<T> listItem = head;

            while (i != index - 1) {
                listItem = listItem.getNext();
                ++i;
            }

            ListItem<T> addedListItem = new ListItem<>(data, listItem.getNext());
            listItem.setNext(addedListItem);
            ++count;
        }
    }

    public boolean removeListItemByValue(T value) {
        int index = 0;

        for (ListItem<T> listItem = head; listItem != null; listItem = listItem.getNext()) {
            if (listItem.getData().equals(value)) {
                removeListItem(index);
                --count;

                return true;
            }
            ++index;
        }

        return false;
    }

    public T removeHead() {
        if (count == 0) {
            throw new IllegalArgumentException("Невозможно удалить головной элемент списка, т.к. список пуст");
        }

        ListItem<T> headOldValue = head;
        head = head.getNext();
        --count;

        return headOldValue.getData();
    }

    public void reverse() {
        SinglyLinkedList<T> reversedList = new SinglyLinkedList<>();

        for (ListItem<T> listItem = head; listItem != null; listItem = listItem.getNext()) {
            reversedList.addHead(listItem.getData());
        }

        assert head != null;
        head.setData(reversedList.getHeadData());
        head.setNext(reversedList.head.getNext());
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();
        int index = 0;

        for (ListItem<T> listItem = head; listItem != null; listItem = listItem.getNext()) {
            copy.addListItem(listItem.getData(), index);
            ++index;
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (head != null) {
            sb.append("{");

            for (ListItem<T> listItem = head; listItem != null; listItem = listItem.getNext()) {
                sb.append(listItem.getData()).append(", ");
            }

            sb.delete(sb.length() - 2, sb.length());
            sb.append("}");
        } else {
            sb.append("{}");
        }

        return sb.toString();
    }
}