package myAdapter;

import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * <p>
 * An implementation of the HListIterator interface that adapts a Vector to the HList interface.
 * </p>
 */
public class ListAdapter implements HList {
    /**
     * The vector that contains the elements of the list.
     */
    protected Vector vec;

    /**
     * The offset of the list, if it is a sublist, otherwise 0.
     */
    protected int offset;

    /**
     * The size of the list.
     */
    protected int size;

    /**
     * The ListAdapter from which this list was created, if it is a sublist, otherwise null.
     */
    protected ListAdapter oldList;

    /**
     * Constructs an empty list.
     */
    public ListAdapter() {
        vec = new Vector();
        offset = 0;
        size = 0;
        oldList = null;
    }

    /**
     * Constructs a list containing the elements of the specified collection, in the order they are returned by the collection's iterator.
     *
     * @param list the collection whose elements are to be placed into this list
     */
    public ListAdapter(ListAdapter list) {
        vec = new Vector();
        offset = 0;
        size = 0;
        oldList = null;
        addAll(list);
    }

    /**
     * Private Constructor for subList, to create a sublist of a list, with shallow copy of the elements.
     *
     * @param list the list to be copied
     * @param start the start index of the sublist
     * @param end the end index of the sublist
     */
    private ListAdapter(ListAdapter list, int start, int end) {
        oldList = list;
        vec = list.vec;
        offset = list.offset + start;
        size = end - start;
    }

    /**
     * Decrements the size of the list and propagates the decrement to the oldList if it exists.
     */
    private void decrementSize() {
        size--;
        if (oldList != null) {
            oldList.decrementSize();
        }
    }

    /**
     * Increments the size of the list and propagates the increment to the oldList if it exists.
     */
    private void incrementSize() {
        size++;
        if (oldList != null) {
            oldList.incrementSize();
        }
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     * @return {@code true} if this list contains no elements.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code (o==null ? e==null : o.equals(e))}.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element.
     */
    public boolean contains(Object o) {
        HIterator it = this.iterator();
        while (it.hasNext()) {
            Object current = it.next();
            if (current == null && o == null) {
                return true;
            }
            if (current != null && current.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence.
     */
    public HIterator iterator() {
        return new HListIteratorAdapter();
    }

    /**
     * Returns an array containing all the elements in this list in proper sequence.
     *
     * @return an array containing all the elements in this list in proper sequence.
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = offset; i < offset + size; i++) {
            array[i - offset] = vec.elementAt(i);
        }
        return array;
    }

    /**
     * Returns an array containing all the elements in this list in proper
     * sequence; the runtime type of the returned array is that of the
     * specified array.
     *
     * @param a the array into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list.
     * @throws NullPointerException if the specified array is {@code null}.
     */
    public Object[] toArray(Object[] a) {
        if (a == null)
            throw new NullPointerException();
        if (a.length < vec.size()) {
            Object[] newArr = new Object[size];
            for (int i = offset; i < offset + size; i++) {
                newArr[i - offset] = vec.elementAt(i);
            }
            return newArr;
        } else {
            for (int i = offset; i < offset + size; i++) {
                a[i - offset] = vec.elementAt(i);
            }
            if (a.length > size) {
                a[size] = null;
            }
            return a;
        }
    }

    /**
     * <p>
     * Appends the specified element to the end of this list</p>
     * <p>
     * Support null additions. </p>
     *
     * @param o element to be appended to this list.
     * @return {@code true} (as per the general contract of the
     * {@code Collection.add} method).
     */
    public boolean add(Object o) {
        vec.insertElementAt(o, offset + size);
        incrementSize();
        return true;
    }

    /**
     * Removes the first occurrence in this list of the specified element.
     * If this list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index i
     * such that {@code (o==null ? get(i)==null : o.equals(get(i)))} (if
     * such an element exists).
     *
     * @param o element to be removed from this list, if present.
     * @return {@code true} if this list contained the specified element.
     */
    public boolean remove(Object o) {
        for (int i = offset; i < offset + size; i++) {
            if (vec.elementAt(i).equals(o)) {
                vec.removeElementAt(i);
                decrementSize();
                return true;
            }
        }
        return false;
    }

    /**
     * Returns {@code true} if this list contains all the elements of the
     * specified collection.
     *
     * @param c collection to be checked for containment in this list.
     * @return {@code true} if this list contains all the elements of the
     * specified collection.
     * @throws NullPointerException if the specified collection is {@code null}.
     */
    public boolean containsAll(HCollection c) {
        if (c == null)
            throw new NullPointerException();
        HIterator it = c.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Appends all the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator.  The behavior of this
     * operation is unspecified if the specified collection is modified while
     * the operation is in progress.  (Note that this will occur if the
     * specified collection is this list, and it's nonempty.)
     *
     * @param c collection whose elements are to be added to this list.
     * @return {@code true} if this list changed as a result of the call.
     * @throws NullPointerException          if the specified collection is {@code null}.
     */
    public boolean addAll(HCollection c) {
        if (c == null)
            throw new NullPointerException();
        HIterator it = c.iterator();
        if (c.isEmpty())
            return false;
        while (it.hasNext()) {
            add(it.next());
        }
        return true;
    }

    /**
     * Inserts all the elements in the specified collection into this
     * list at the specified position.  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * unspecified if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this list, and it's nonempty.)
     *
     * @param index index at which to insert first element from the specified
     *              collection.
     * @param c     elements to be inserted into this list.
     * @return {@code true} if this list changed as a result of the call.
     * @throws NullPointerException          if the specified collection is {@code null}.
     * @throws IndexOutOfBoundsException     if the index is out of range {@code (index < 0 || index > size())}.
     */
    public boolean addAll(int index, HCollection c) {
        if (index < 0 || index >  size)
            throw new IndexOutOfBoundsException();
        if (c == null)
            throw new NullPointerException();
        index += offset;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            add(index++, it.next());
        }
        return true;
    }

    /**
     * Removes from this list all the elements that are contained in the
     * specified collection.
     *
     * @param c collection that defines which elements will be removed from
     *          this list.
     * @return {@code true} if this list changed as a result of the call.
     * @throws NullPointerException          if the specified collection is {@code null}.
     */
    public boolean removeAll(HCollection c) {
        if (c == null)
            throw new NullPointerException();
        HIterator it = iterator();
        boolean modified = false;
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection.  In other words, removes
     * from this list all the elements that are not contained in the specified
     * collection.
     *
     * @param c collection that defines which elements this set will retain.
     * @return {@code true} if this list changed as a result of the call.
     * @throws NullPointerException          if the specified collection is {@code null}.
     */
    public boolean retainAll(HCollection c) {
        if (c == null)
            throw new NullPointerException();
        boolean modified = false;
        HIterator it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all the elements from this list.  This
     * list will be empty after this call returns.
     */
    public void clear() {
        HListIterator it = this.listIterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    /**
     * Compares the specified object with this list for equality.  Returns
     * {@code true} if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in
     * the two lists are <i>equal</i>.  (Two elements {@code e1} and
     * {@code e2} are <i>equal</i> if {@code (e1==null ? e2==null :
     * e1.equals(e2))}.)  In other words, two lists are defined to be
     * equal if they contain the same elements in the same order.  This
     * definition ensures that the equals method works properly across
     * different implementations of the {@code List} interface.
     *
     * @param o the object to be compared for equality with this list.
     * @return {@code true} if the specified object is equal to this list.
     */
    public boolean equals(Object o) {
        if(!(o instanceof HList)) {
            return false;
        }
        HList list = (HList) o;
        if (list.size() != size) {
            return false;
        }
        HIterator it = list.iterator();
        HIterator it2 = iterator();
        while (it.hasNext()) {
            Object obj1 = it.next();
            Object obj2 = it2.next();
            if (!(obj1 == null ? obj2 == null : obj1.equals(obj2))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the hash code value for this list.  The hash code of a list
     * is defined to be the result of the following calculation:
     * <pre>{@code   hashCode = 1;
     * Iterator i = list.iterator();
     * while (i.hasNext()) {
     * Object obj = i.next();
     * hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     * }
     * }</pre>
     * This ensures that {@code list1.equals(list2)} implies that
     * {@code list1.hashCode()==list2.hashCode()} for any two lists,
     * {@code list1} and {@code list2}, as required by the general
     * contract of {@code Object.hashCode}.
     *
     * @return the hash code value for this list.
     */
    public int hashCode() {
        int hashCode = 1;
        HListIterator  i = listIterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range {@code (index < 0 || index >= size())}.
     */
    public Object get(int index) {
        if (index < 0 || index >  size)
            throw new IndexOutOfBoundsException();
        index += offset;
        return vec.elementAt(index);
    }


    public String toString() {
        String str = "[";
        for (int i = offset; i < offset + size; i++) {
            str += vec.elementAt(i).toString();
            if (i != offset + size - 1) {
                str += ", ";
            }
        }
        str += "]";
        return str;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index   index of element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException     if the index is out of range {@code (index < 0 || index >= size())}.
     */
    public Object set(int index, Object element) {
        if (index < 0 || index >  size)
            throw new IndexOutOfBoundsException();
        Object old = get(index);
        try {
            vec.setElementAt(element, index + offset);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
        return old;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index   index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @throws IndexOutOfBoundsException     if the index is out of range {@code (index < 0 || index > size())}.
     */
    public void add(int index, Object element) {
        if (index < 0 || index >  size)
            throw new IndexOutOfBoundsException();
        index += offset;
        try {
            vec.insertElementAt(element, index);
            incrementSize();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to removed.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException     if the index is out of range {@code (index < 0 || index >= size())}.
     */
    public Object remove(int index) {
        if (index < 0 || index >  size)
            throw new IndexOutOfBoundsException();
        index += offset;
        Object old = get(index - offset);
        try {
            vec.removeElementAt(index);
            decrementSize();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
        return old;
    }

    /**
     * Returns the index in this list of the first occurrence of the specified
     * element, or -1 if this list does not contain this element.
     * More formally, returns the lowest index {@code i} such that
     * {@code (o==null ? get(i)==null : o.equals(get(i)))},
     * or -1 if there is no such index.
     *
     * @param o element to search for.
     * @return the index in this list of the first occurrence of the specified
     * element, or -1 if this list does not contain this element.
     */
    public int indexOf(Object o) {
        for (int i = offset; i < offset + size; i++) {
            if (vec.elementAt(i).equals(o)) {
                return i - offset;
            }
        }
        return -1;
    }

    /**
     * Returns the index in this list of the last occurrence of the specified
     * element, or -1 if this list does not contain this element.
     * More formally, returns the highest index {@code i} such that
     * {@code (o==null ? get(i)==null : o.equals(get(i)))},
     * or -1 if there is no such index.
     *
     * @param o element to search for.
     * @return the index in this list of the last occurrence of the specified
     * element, or -1 if this list does not contain this element.
     */
    public int lastIndexOf(Object o) {
        for (int i = offset + size - 1; i >= offset; i--) {
            if (vec.elementAt(i).equals(o)) {
                return i - offset;
            }
        }
        return -1;
    }

    /**
     * Returns a list iterator of the elements in this list (in proper
     * sequence).
     *
     * @return a list iterator of the elements in this list (in proper
     * sequence).
     */
    public HListIterator listIterator() {
        return new HListIteratorAdapter();
    }

    /**
     * Returns a list iterator of the elements in this list (in proper
     * sequence), starting at the specified position in this list.  The
     * specified index indicates the first element that would be returned by
     * an initial call to the {@code next} method.  An initial call to
     * the {@code previous} method would return the element with the
     * specified index minus one.
     *
     * @param index index of first element to be returned from the
     *              list iterator (by a call to the {@code next} method).
     * @return a list iterator of the elements in this list (in proper
     * sequence), starting at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range {@code (index < 0 || index > size())}.
     */
    public HListIterator listIterator(int index) {
        if (index < 0 || index >  size)
            throw new IndexOutOfBoundsException();
        index += offset;
        return new HListIteratorAdapter(index);
    }

    /**
     * <p>
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.  (If
     * {@code fromIndex} and {@code toIndex} are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.</p>
     * <p>
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).   Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:</p>
     * <pre>{@code        list.subList(from, to).clear();
     * }</pre>
     * Similar idioms may be constructed for {@code indexOf} and
     * {@code lastIndexOf}, and all of the algorithms in the
     * {@code Collections} class can be applied to a subList.
     * <p>
     * The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.) </p>
     *
     * @param fromIndex low endpoint (inclusive) of the subList.
     * @param toIndex   high endpoint (exclusive) of the subList.
     * @return a view of the specified range within this list.
     * @throws IndexOutOfBoundsException for an illegal endpoint index value {@code (fromIndex < 0 || toIndex > size || fromIndex > toIndex)}.
     */
    public HList subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >  size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        return new ListAdapter(this, fromIndex, toIndex);
    }

    /**
     * Private class that implements the HListIterator interface.
     */
    private class HListIteratorAdapter implements HListIterator {
        /**
         * The cursor position of the iterator.
         */
        private int cursor;

        /**
         * The index of the last element returned by a call to next() or previous(), or -1 if no such element has been returned.
         */
        private int lastRet = -1;

        /**
         * Constructs a new HListIteratorAdapter with the cursor at the beginning of the list.
         */
        public HListIteratorAdapter() {
            cursor = offset;
        }

        /**
         * Constructs a new HListIteratorAdapter with the cursor at the specified index.
         *
         * @param cursor the index of the cursor.
         * @throws IndexOutOfBoundsException if the cursor is out of range {@code (cursor < 0 || cursor > size())}.
         */
        public HListIteratorAdapter(int cursor) {
            if (cursor < offset || cursor > offset + size)
                throw new IndexOutOfBoundsException();
            this.cursor = cursor;
        }

        /**
         * <p>
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the forward direction. (In other words, returns
         * {@code true} if {@code next} would return an element rather than
         * throwing an exception.)
         * </p>
         *
         * @return {@code true} if the list iterator has more elements when
         * traversing the list in the forward direction.
         */
        public boolean hasNext() {
            return cursor != offset + size;
        }

        /**
         * <p>
         * Returns the next element in the list. This method may be called
         * repeatedly to iterate through the list, or intermixed with calls to
         * {@code previous} to go back and forth. (Note that alternating calls
         * to {@code next} and {@code previous} will return the same element
         * repeatedly.)
         * </p>
         *
         * @return the next element in the list.
         * @throws NoSuchElementException if the iteration has no next element.
         */
        public Object next() {
            int i = cursor;
            if (i >= offset + size)
                throw new NoSuchElementException();
            cursor = i + 1;
            return get(lastRet = i - offset);
        }

        /**
         * <p>
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the reverse direction. (In other words, returns
         * {@code true} if {@code previous} would return an element rather than
         * throwing an exception.)
         * </p>
         *
         * @return {@code true} if the list iterator has more elements when
         * traversing the list in the reverse direction.
         */
        public boolean hasPrevious() {
            return cursor != offset;
        }

        /**
         * <p>
         * Returns the previous element in the list. This method may be called
         * repeatedly to iterate through the list backwards, or intermixed with
         * calls to {@code next} to go back and forth. (Note that alternating
         * calls to {@code next} and {@code previous} will return the same
         * element repeatedly.)
         * </p>
         *
         * @return the previous element in the list.
         * @throws NoSuchElementException if the iteration has no previous element.
         */
        public Object previous() {
            int i = cursor - 1;
            if (i < offset)
                throw new NoSuchElementException();
            cursor = i;
            return get(lastRet = i);
        }

        /**
         * <p>
         * Returns the index of the element that would be returned by a subsequent
         * call to {@code next}. (Returns list size if the list iterator is at the
         * end of the list.)
         * </p>
         *
         * @return the index of the element that would be returned by a subsequent
         * call to {@code next}, or list size if list iterator is at end
         * of list.
         */
        public int nextIndex() {
            return cursor;
        }

        /**
         * <p>
         * Returns the index of the element that would be returned by a subsequent
         * call to {@code previous}. (Returns -1 if the list iterator is at the
         * beginning of the list.)
         * </p>
         *
         * @return the index of the element that would be returned by a subsequent
         * call to {@code previous}, or -1 if list iterator is at
         * beginning of list.
         */
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * <p>
         * Removes from the list the last element that was returned by
         * {@code next} or {@code previous}. This call can
         * only be made once per call to {@code next} or {@code previous}. It
         * can be made only if {@code ListIterator.add} has not been called after
         * the last call to {@code next} or {@code previous}.
         * </p>
         *
         * @throws IllegalStateException         neither {@code next} nor
         *                                       {@code previous} have been called, or {@code remove} or
         *                                       {@code add} have been called after the last call to *
         *                                       {@code next} or {@code previous}.
         */
        public void remove() {
            if (lastRet == -1)
                throw new IllegalStateException();

            ListAdapter.this.remove(lastRet);

            cursor = lastRet + offset;
            lastRet = -1;
        }

        /**
         * <p>
         * Replaces the last element returned by {@code next} or
         * {@code previous} with the specified element.
         * This call can be made only if neither {@code ListIterator.remove} nor
         * {@code ListIterator.add} have been called after the last call to
         * {@code next} or {@code previous}.
         * </p>
         *
         * @param o the element with which to replace the last element returned by
         *          {@code next} or {@code previous}.
         * @throws IllegalStateException         if neither {@code next} nor
         *                                       {@code previous} have been called, or {@code remove} or
         *                                       {@code add} have been called after the last call to
         *                                       {@code next} or {@code previous}.
         */
        public void set(Object o) {
            if (lastRet == -1)
                throw new IllegalStateException();

            ListAdapter.this.set(lastRet, o);
        }

        /**
         * <p>
         * Inserts the specified element into the list. The
         * element is inserted immediately before the next element that would be
         * returned by {@code next}, if any, and after the next element that
         * would be returned by {@code previous}, if any. (If the list contains
         * no elements, the new element becomes the sole element on the list.)
         * The new element is inserted before the implicit cursor: a subsequent
         * call to {@code next} would be unaffected, and a subsequent call to
         * {@code previous} would return the new element. (This call increases
         * by one the value that would be returned by a call to {@code nextIndex}
         * or {@code previousIndex}.)
         * </p>
         *
         * @param o the element to insert.
         */
        public void add(Object o) {
            int i = cursor;
            ListAdapter.this.add(i, o);
            cursor = i + 1;
            lastRet = -1;
        }
    }
}