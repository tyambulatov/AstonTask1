package edu.aston;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UserArrayList<T> implements UserList<T> {

    private T[] array;

    private int size;

    private final int BASE_ARRAY_SIZE = 10;

    public UserArrayList() {
        this.array = (T[]) new Object[BASE_ARRAY_SIZE];
        this.size = 0;
    }

    /**
     * Appends an element to the end of the list.
     * @param element element to be appended to end of a list
     * @return true - appended successfully, false – otherwise
     */
    @Override
    public boolean add(T element) {
        if (size == array.length)
            grow();

        this.array[size] = element;
        size++;
        return true;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[3 * array.length / 2];
        T[] tmpArray = this.array;
        this.array = newArray;
        System.arraycopy(tmpArray, 0, this.array, 0, tmpArray.length);
    }

    /**
     * Inserts a specified element into specified position.
     * Shifts elements in position and any subsequent elements to the right.
     * @param index the index where to insert an element
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException when index is out of range (index < 0
     * || index > size())
     */
    @Override
    public void add(int index, T element) {
        checkIndexInRange(index);

        if (size == array.length)
            grow();

        System.arraycopy(array, index,
                array, index + 1,
                size - index);
        this.array[index] = element;
        size++;
    }

    private void checkIndexInRange(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
    }

    private void checkIndexNotEqualSize(int index) {
        if (index == size)
            throw new IndexOutOfBoundsException();
    }

    /**
     * Returns an element at specified position.
     * @param index the index of an element to return
     * @return an element at specified position
     * @throws IndexOutOfBoundsException when index is out of range (index < 0
     * || index > size())
     */
    @Override
    public T get(int index) {
        checkIndexInRange(index);
        checkIndexNotEqualSize(index);
        return this.array[index];
    }

    /**
     * Removes an element at specified position.
     * Shifts elements in position and any subsequent elements to the left.
     * @param index the index of an element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException when index is out of range (index < 0
     * || index > size())
     */
    @Override
    public T remove(int index) {
        checkIndexInRange(index);
        checkIndexNotEqualSize(index);
        T oldElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index + 1);
        size--;
        return oldElement;
    }

    /**
     * Removes every element from this list. The List will be empty after this call.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Sorts a list according to the order induced by the specified Comparator.
     * Uses 'Quick Sort' algorithm for sorting.
     * @param comp the Comparator used to compare list elements
     */
    @Override
    public void sort(Comparator<? super T> comp) {
        quickSort(array, 0, size - 1, comp);
    }

    private void quickSort(T[] array, int low, int high, Comparator<? super T> comp) {
        if (array.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        T pivot = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (comp.compare(array[i], pivot) < 0)
                i++;

            while (comp.compare(array[j], pivot) > 0)
                j--;

            if (i <= j) {
                T tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j, comp);

        if (high > i)
            quickSort(array, i, high, comp);
    }

    /**
     * Returns a string representation of the object
     * @return string representation of the object
     */
    @Override
    public String toString() {
        Iterator<T> it = iterator();

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private class ArrayListIterator implements Iterator<T> {

        private int currentIndex;

        public ArrayListIterator() {
            currentIndex = 0;
        }


        @Override
        public boolean hasNext() {
            return array[currentIndex] != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T element = array[currentIndex];
                currentIndex++;
                return element;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    /**
     * Creates new ArrayListIterator of a list and returns it.
     * @return ArrayListIterator
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }
}