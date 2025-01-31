package edu.cmu.cs.cs214.rec04;

/**
 * SortedIntList -- an implementation of IntegerList that maintains 
 * its elements in ascending numerical order. This means when elements
 * are added, they are inserted into the correct position to maintain
 * the sorting, rather than being added to the end.
 */

public class SortedIntList extends AbstractIntList {
    private int[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public SortedIntList() {
        // Initializes an internal array with a default capacity
        elements = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Adds the specified integer to the list while maintaining sorted order.
     * If the array is full, it will be expanded to accommodate new elements.
     * 
     * @param num the integer to be added
     * @return true always, since duplicates are allowed in this implementation
     */
    @Override
    public boolean add(int num) {
        if (size == elements.length) {
            expandCapacity();
        }

        int insertionPoint = 0;
        while (insertionPoint < size && elements[insertionPoint] <= num) {
            insertionPoint++;
        }

        // Shifts existing elements to make space for the new element.
        for (int i = size; i > insertionPoint; i--) {
            elements[i] = elements[i - 1];
        }
        elements[insertionPoint] = num;

        size++;
        return true;
    }

    /**
     * Doubles the capacity of the internal array.
     * This is a private helper method used when the array needs to grow.
     */
    private void expandCapacity() {
        int[] newElements = new int[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    /**
     * Returns the element at the specified position.
     * 
     * @param index index of element to return
     * @throws IndexOutOfBoundsException if index is out of range
     * @return the element at the specified position
     */
    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[index];
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     * 
     * @param num element to be removed, if present
     * @return true if the list contained the specified element
     */
    @Override
    public boolean remove(int num) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == num) {
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all elements that appear in the specified list from this list.
     * 
     * @param list the list containing elements to be removed
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(IntegerList list) {
        boolean modified = false;
        for (int i = 0; i < list.size(); i++) {
            if (remove(list.get(i))) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Helper method to print the list contents - useful for debugging.
     * 
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
