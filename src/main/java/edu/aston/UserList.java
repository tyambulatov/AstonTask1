package edu.aston;

import java.util.Comparator;
import java.util.Iterator;

public interface UserList<T> {
    /**
     * Appends an element to the end of list
     * @param element element to be appended to end of a list
     * @return true - appended successfully, false – otherwise
     */
    public boolean add(T element);

    /**
     * Appends an element to specified index.
     * Shifts elements to the right.
     * @param index index in a list where to append an element
     * @param element element to be appended
     * @throws IndexOutOfBoundsException thrown when index is out bound
     */
    public void add(int index, T element);

    /**
     * Returns element at specified index
     * @param index position of an element to return
     * @return element that at index
     */
    public T get(int index);

    /**
     * removes an element at specified index
     * @param index position of an element to remove
     * @return element that been removed
     * @throws IndexOutOfBoundsException when index is out of bound
     */
    public T remove(int index);

    /**
     * Removes every element from this list. The List will be empty after this call.
     */
    public void clear();

    /**
     * Sorts a list according to the order induced by the specified Comparator.
     * Uses 'Quick Sort' algorithm for sorting.
     * @param comp functional interface {@code Comparator}  that compares two elements
     */
    public void sort(Comparator<? super T> comp);

    /**
     * Creates new ArrayListIterator of a list and returns it
     * @return ArrayListIterator
     */
    public Iterator<T> iterator();

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size();
}
