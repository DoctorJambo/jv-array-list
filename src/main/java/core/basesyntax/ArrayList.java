package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private int size;
    private Object[] elements = new Object[DEFAULT_CAPACITY];

    private void growIfNeeded() {
        Object[] newElements = new Object[(int) (elements.length * GROWTH_FACTOR)];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    private T removeValue(int index) {
        T removedElement = (T) elements[index];
        int valuesAfter = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, valuesAfter);
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            growIfNeeded();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index out of size");
        }
        if (size == elements.length) {
            growIfNeeded();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (size == elements.length) {
                growIfNeeded();
            }
            elements[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index: "
                    + index + "out of size: " + size);
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index: "
                    + index + " out of collection size");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("no elements at index: " + index);
        }
        return removeValue(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null ? elements[i] == null : element.equals(elements[i]))) {
                return removeValue(i);
            }
        }
        throw new NoSuchElementException("element: "
                + element + ", does not exist in the collection");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
