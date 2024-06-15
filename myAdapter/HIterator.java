package myAdapter;


/**
 * <p>
 * An iterator over a collection. Iterator takes the place of Enumeration in
 * the Java collections framework. Iterators differ from enumerations in two
 * ways: </p>
 * <ul>
 *     <li> Iterators allow the caller to remove elements from the
 *         underlying collection during the iteration with well-defined
 *         semantics.
 *     </li>
 *     <li> Method names have been improved.
 *     </li>
 * </ul>
 */
public interface HIterator {

    /**
     * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true} if next would return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iterator has more elements.
     * @throws java.util.NoSuchElementException iteration has no more elements.
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     */
    Object next();

    /**
     * Removes from the underlying collection the last element returned by the iterator (optional operation). This method can be called only once per call to {@code next}. The behavior of an iterator is unspecified if the underlying collection is modified while the iteration is in progress in any way other than by calling this method.
     *
     * @throws UnsupportedOperationException if the remove operation is not supported by this Iterator.
     *                                       IllegalStateException - if the {@code next} method has not yet been called, or the {@code remove} method has already been called after the last call to the {@code next} method.
     */
    void remove();
}
