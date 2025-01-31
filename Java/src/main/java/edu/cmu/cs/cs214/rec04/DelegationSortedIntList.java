package edu.cmu.cs.cs214.rec04;

/**
 * DelegationSortedIntList -- a variant of a SortedIntList that keeps
 * count of the number of attempted element insertions (not to be confused
 * with the current size, which goes down when an element is removed)
 * and exports an accessor (totalAdded) for this count.
 *
 * @author Nora Shoemaker
 *
 */

 public class DelegationSortedIntList implements IntegerList {
    // The delegate object that handles the actual list operations
    private final SortedIntList delegate = new SortedIntList();

    private int totalAdded = 0;
    
    /**
     * Returns the total number of elements that have been added to this list
     * since its creation. This count includes all attempted additions through
     * both add() and addAll() methods, regardless of whether the elements 
     * were successfully added or not.
     * 
     * @return the total number of attempted element additions
     */
    public int getTotalAdded() {
        return totalAdded;
    }
    
    /**
     * Adds the specified integer to the list while maintaining sorted order.
     * Increments the total additions counter regardless of whether the
     * addition was successful.
     *
     * @param num the integer to be added
     * @return true if the list was modified as a result of this call
     */
    @Override
    public boolean add(int num) {
        totalAdded++;
        return delegate.add(num);
    }
    
    /**
     * Adds all elements from the specified list while maintaining sorted order.
     * Increments the total additions counter by the size of the input list.
     *
     * @param list the list containing elements to be added
     * @return true if the list was modified as a result of this call
     */
    @Override
    public boolean addAll(IntegerList list) {
        totalAdded += list.size();
        return delegate.addAll(list);
    }
    
    // The following methods are simple delegations to the internal SortedIntList
    
    /**
     * @see IntegerList#get(int)
     */
    @Override
    public int get(int index) {
        return delegate.get(index);
    }
    
    /**
     * @see IntegerList#remove(int)
     */
    @Override
    public boolean remove(int num) {
        return delegate.remove(num);
    }
    
    /**
     * @see IntegerList#removeAll(IntegerList)
     */
    @Override
    public boolean removeAll(IntegerList list) {
        return delegate.removeAll(list);
    }
    
    /**
     * @see IntegerList#size()
     */
    @Override
    public int size() {
        return delegate.size();
    }
}