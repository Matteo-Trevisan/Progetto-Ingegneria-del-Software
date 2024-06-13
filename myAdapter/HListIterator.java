package myAdapter;

/**
 * <p>
 * An iterator for lists that allows the programmer
 * to traverse the list in either direction, modify
 * the list during iteration, and obtain the iterator's
 * current position in the list. A <tt>ListIterator</tt>
 * has no current element; its <i>cursor position</i> always
 * lies between the element that would be returned by a call
 * to <tt>previous()</tt> and the element that would be
 * returned by a call to <tt>next()</tt>. In a list of
 * length <tt>n</tt>, there are <tt>n+1</tt> valid
 * index values, from <tt>0</tt> to <tt>n</tt>, inclusive.
 * </p>
 * <pre>
 *             Element(0)   Element(1)   Element(2)   ... Element(n)
 *           ^            ^            ^            ^               ^
 *    Index: 0            1            2            3               n+1
 *
 *    </pre>
 * <p>
 * Note that the <code>remove()</code> and
 * <code>set(Object)</code> methods are
 * <i>not</i> defined in terms of the cursor position; they are defined to
 * operate on the last element returned by a call to <code>next()</code> or <code>previous()</code>.
 * </p>
 */
public interface HListIterator extends HIterator {

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the forward direction. (In other words, returns
     * {@code true} if {@code next} would return an element rather than
     * throwing an exception.)
     * <p>
     *
     * @return {@code true} if the list iterator has more elements when
     * traversing the list in the forward direction.
     */
    public boolean hasNext();

    /**
     * Returns the next element in the list. This method may be called
     * repeatedly to iterate through the list, or intermixed with calls to
     * {@code previous} to go back and forth. (Note that alternating calls
     * to {@code next} and {@code previous} will return the same element
     * repeatedly.)
     * <p>
     *
     * @return the next element in the list.
     * @throws java.util.NoSuchElementException if the iteration has no next element.
     */
    public Object next();

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the reverse direction. (In other words, returns
     * {@code true} if {@code previous} would return an element rather than
     * throwing an exception.)
     * <p>
     *
     * @return {@code true} if the list iterator has more elements when
     * traversing the list in the reverse direction.
     */
    public boolean hasPrevious();

    /**
     * Returns the previous element in the list. This method may be called
     * repeatedly to iterate through the list backwards, or intermixed with
     * calls to {@code next} to go back and forth. (Note that alternating
     * calls to {@code next} and {@code previous} will return the same
     * element repeatedly.)
     * <p>
     *
     * @return the previous element in the list.
     * @throws java.util.NoSuchElementException if the iteration has no previous
     *                                          element.
     */
    public Object previous();

    /**
     * Returns the index of the element that would be returned by a subsequent
     * call to {@code next}. (Returns list size if the list iterator is at the
     * end of the list.)
     * <p>
     *
     * @return the index of the element that would be returned by a subsequent
     * call to {@code next}, or list size if list iterator is at end
     * of list.
     */
    public int nextIndex();

    /**
     * Returns the index of the element that would be returned by a subsequent
     * call to {@code previous}. (Returns -1 if the list iterator is at the
     * beginning of the list.)
     * <p>
     *
     * @return the index of the element that would be returned by a subsequent
     * call to {@code previous}, or -1 if list iterator is at
     * beginning of list.
     */
    public int previousIndex();

    /**
     * Removes from the list the last element that was returned by
     * {@code next} or {@code previous} (optional operation). This call can
     * only be made once per call to {@code next} or {@code previous}. It
     * can be made only if {@code ListIterator.add} has not been called after
     * the last call to {@code next} or {@code previous}.
     * <p>
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *                                       operation is not supported by this list iterator.
     * @throws IllegalStateException         neither {@code next} nor
     *                                       {@code previous} have been called, or {@code remove} or
     *                                       {@code add} have been called after the last call to *
     *                                       {@code next} or {@code previous}.
     */
    public void remove();

    /**
     * Replaces the last element returned by {@code next} or
     * {@code previous} with the specified element (optional operation).
     * This call can be made only if neither {@code ListIterator.remove} nor
     * {@code ListIterator.add} have been called after the last call to
     * {@code next} or {@code previous}.
     * <p>
     *
     * @param o the element with which to replace the last element returned by
     *          {@code next} or {@code previous}.
     * @throws UnsupportedOperationException if the {@code set} operation
     *                                       is not supported by this list iterator.
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list.
     * @throws IllegalArgumentException      if some aspect of the specified
     *                                       element prevents it from being added to this list.
     * @throws IllegalStateException         if neither {@code next} nor
     *                                       {@code previous} have been called, or {@code remove} or
     *                                       {@code add} have been called after the last call to
     *                                       {@code next} or {@code previous}.
     */
    public void set(Object o);

    /**
     * Inserts the specified element into the list (optional operation). The
     * element is inserted immediately before the next element that would be
     * returned by {@code next}, if any, and after the next element that
     * would be returned by {@code previous}, if any. (If the list contains
     * no elements, the new element becomes the sole element on the list.)
     * The new element is inserted before the implicit cursor: a subsequent
     * call to {@code next} would be unaffected, and a subsequent call to
     * {@code previous} would return the new element. (This call increases
     * by one the value that would be returned by a call to {@code nextIndex}
     * or {@code previousIndex}.)
     * <p>
     *
     * @param o the element to insert.
     * @throws UnsupportedOperationException if the {@code add} method is
     *                                       not supported by this list iterator.
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list.
     * @throws IllegalArgumentException      if some aspect of this element
     *                                       prevents it from being added to this list.
     */
    public void add(Object o);





    /*
    // Inserts the specified element into the list (optional operation).
    void add(Object o);

    // Returns true if this list iterator has more elements when traversing the list in the forward direction.
    boolean hasNext();

    // Returns true if this list iterator has more elements when traversing the list in the reverse direction.
    boolean hasPrevious();

    // Returns the next element in the list.
    Object next();

    // Returns the index of the element that would be returned by a subsequent call to next.
    int nextIndex();

    // Returns the previous element in the list.
    Object previous();

    // Returns the index of the element that would be returned by a subsequent call to previous.
    int previousIndex();

    // Removes from the list the last element that was returned by next or previous (optional operation).
    void remove();

    // Replaces the last element returned by next or previous with the specified element (optional operation).
    void set(Object o);
    
     */
}
