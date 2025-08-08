package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private Object[] elements = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        if (size == elements.length) {
            Object[] newElements = new Object[(int) (elements.length * 1.5)];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index out of size");
        }
        size++;
        if (size == elements.length) {
            Object[] newElements = new Object[(int) (elements.length * 1.5)];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (size == elements.length) {
                Object[] newElements = new Object[(int) (elements.length * 1.5)];
                System.arraycopy(elements, 0, newElements, 0, elements.length);
                elements = newElements;
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
        T removedElement = (T) elements[index];
        int valuesAfter = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, valuesAfter);
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                T removedElement = element;
                int valuesAfter = size - i - 1;
                System.arraycopy(elements, i + 1, elements, i, valuesAfter);
                elements[--size] = null;
                return removedElement;
            }
        }
        throw new NoSuchElementException("element: "
                + element + ", dose not exist in the collection");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        }
        return true;
    }
}
