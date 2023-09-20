package edu.aston;

import java.util.Comparator;
import java.util.Iterator;

public interface UserList<T> {

    /**
     * Appends the specified element to the end of this list.
     * @param element element to be appended to end of a list
     * @return true - appended successfully, false – otherwise
     */
    public boolean add(T element);

    /**
     * Inserts a specified element into specified position.
     * Shifts elements in position and any subsequent elements to the right.
     * @param index the index where to insert an element
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException when index is out of range (index < 0
     * || index > size())
     */
    public void add(int index, T element);

    /**
     * Returns an element at specified position.
     * @param index the index of an element to return
     * @return an element at specified position
     * @throws IndexOutOfBoundsException when index is out of range (index < 0
     * || index > size())
     */
    public T get(int index);

    /**
     * Removes an element at specified position.
     * Shifts elements in position and any subsequent elements to the left.
     * @param index the index of an element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException when index is out of range (index < 0
     * || index > size())
     */
    public T remove(int index);

    /**
     * Removes every element from this list. The List will be empty after this call.
     */
    public void clear();

    /**
     * Sorts a list according to the order induced by the specified Comparator.
     * @param comp the Comparator used to compare list elements
     */
    public void sort(Comparator<? super T> comp);

    /**
     * Creates new ArrayListIterator of a list and returns it.
     * @return ArrayListIterator
     */
    public Iterator<T> iterator();

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size();
}
