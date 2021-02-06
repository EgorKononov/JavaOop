package ru.academits.java.kononov.my_array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(capacity + " - неверное значение вместимости. Вместимость должна быть больше либо равной нулю");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Невозможно перейти к следующему элементу. В списке отсутствует следующий элемент");
            }

            if (modCount != initialModCount) {
                throw new ConcurrentModificationException("Невозможно перейти к следующему элементу. Коллекция изменилась");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    private void checkIndex(int index, int maxIndex) {
        if (items.length == 0) {
            throw new IndexOutOfBoundsException("Невозможно обратиться по индексу. Список пуст");
        }

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException(index + " - неверное значение индекса. Индекс должен находится в " +
                    "пределах от 0 до " + maxIndex);
        }
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            items = Arrays.copyOf(items, DEFAULT_CAPACITY);
        } else {
            items = Arrays.copyOf(items, items.length * 2);
        }
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
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
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E item) {
        add(size, item);

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
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index, size);

        int collectionSize = c.size();

        if (collectionSize == 0) {
            return false;
        }

        if (index == size) {
            ensureCapacity(size + collectionSize);
        } else {
            System.arraycopy(items, index, items, index + collectionSize, size - index);
        }

        int i = index;

        for (E e : c) {
            items[i] = e;
            ++i;
        }

        size += collectionSize;
        ++modCount;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        final int initialModCount = modCount;

        for (int i = 0; i < size; ++i) {
            if (c.contains(items[i])) {
                remove(i);
                --i;
            }
        }

        return modCount != initialModCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        final int initialModCount = modCount;

        for (int i = 0; i < size; ++i) {
            if (!c.contains(items[i])) {
                remove(i);
                --i;
            }
        }

        return modCount != initialModCount;
    }

    @Override
    public void clear() {
        for (int i = 0; i <= size; ++i) {
            items[i] = null;
        }

        size = 0;
        ++modCount;
    }

    @Override
    public E get(int index) {
        checkIndex(index, size - 1);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index, size - 1);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        if (items.length == 0 && index == 0) {
            increaseCapacity();
        } else {
            checkIndex(index, size);

            if (size == items.length) {
                increaseCapacity();
            }
        }

        if (index != size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = item;
        ++size;
        ++modCount;
    }

    @Override
    public E remove(int index) {
        checkIndex(index, size - 1);

        E removedItem = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[size - 1] = null;
        --size;
        ++modCount;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
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
        if (size == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (int i = 0; i < size; ++i) {
            sb.append(items[i]).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");

        return sb.toString();
    }
}