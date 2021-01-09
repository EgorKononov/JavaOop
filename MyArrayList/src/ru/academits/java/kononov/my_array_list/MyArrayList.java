package ru.academits.java.kononov.my_array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[10];
    }

    public MyArrayList(int capacity) {
        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int FIXED_MOD_COUNT = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (currentIndex >= size - 1) {
                throw new NoSuchElementException("Невозможно перейти к следующему элементу. В списке отсутствует следующий элемент");
            }

            if (modCount != FIXED_MOD_COUNT) {
                throw new ConcurrentModificationException("Невозможно перейти к следующему элементу. В списке изменилось количество элементов");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    private E removeLastElement() {
        --size;
        ++modCount;

        return items[size];
    }

    public void ensureCapacity(int capacity) {
        if (capacity > size) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        //noinspection unchecked
        return (T[]) Arrays.copyOf(items, size, a.getClass());
    }

    @Override
    public boolean add(E element) {
        if (size == items.length) {
            increaseCapacity();
        }

        items[size] = element;
        ++size;
        ++modCount;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            remove(index);

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int i = 0;

        for (E e : c) {
            add(index + i, e);
            ++i;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (int i = 0; i < size; ++i) {
            if (c.contains(get(i))) {
                remove(i);
                --i;
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < size; ++i) {
            if (!c.contains(get(i))) {
                remove(i);
                --i;
            }
        }

        return true;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index >= size || index < 0)
            throw new IllegalArgumentException(index + " - неверное значение индекса для списка длины " + size);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index >= size || index < 0)
            throw new IllegalArgumentException(index + " - неверное значение индекса для списка длины " + size);

        E elementOldValue = items[index];
        items[index] = element;

        return elementOldValue;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0)
            throw new IllegalArgumentException(index + " - неверное значение индекса для списка длины " + size);

        if (index == size) {
            add(element);
        } else {
            if (size == items.length) {
                increaseCapacity();
            }

            System.arraycopy(items, index, items, index + 1, size - index);
            items[index] = element;
            ++size;
            ++modCount;
        }
    }

    @Override
    public E remove(int index) {
        if (index >= size || index < 0)
            throw new IllegalArgumentException(index + " - неверное значение индекса для списка длины " + size);

        if (index == size - 1) {
            return removeLastElement();
        } else {
            E removedElementValue = items[index];
            System.arraycopy(items, index + 1, items, index, size - index - 1);
            --size;
            ++modCount;

            return removedElementValue;
        }
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;

        for (E element : this) {
            if (element.equals(o)) {
                return index;
            }

            ++index;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        int lastIndex = 0;
        boolean isContains = false;

        for (E element : this) {
            if (element.equals(o)) {
                isContains = true;
                lastIndex = index;
            }

            ++index;
        }

        if (isContains) {
            return lastIndex;
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        if (size != 0) {
            for (int i = 0; i < size; ++i) {
                sb.append(items[i]).append(", ");
            }

            sb.delete(sb.length() - 2, sb.length());
        }

        sb.append("]");

        return sb.toString();
    }
}