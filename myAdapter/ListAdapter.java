package myAdapter;

import java.util.NoSuchElementException;
import java.util.Vector;

// Supports Null
public class ListAdapter implements HList {
    protected Vector vec;
    protected int offset;
    protected int size;
    protected ListAdapter oldList;

    public ListAdapter() {
        vec = new Vector();
        offset = 0;
        size = 0;
        oldList = null;
    }

    public ListAdapter(ListAdapter list, int start, int end) {
        oldList = list;
        vec = list.vec;
        offset = list.offset + start;
        size = end - start;
    }

    public ListAdapter(ListAdapter list) {
        vec = list.vec;
        offset = 0;
        size = list.size;
        oldList = list;
    }

    private void decrementSize() {
        size--;
        if (oldList != null) {
            oldList.decrementSize();
        }
    }

    private void incrementSize() {
        size++;
        if (oldList != null) {
            oldList.incrementSize();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

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

    public HIterator iterator() {
        return new HListIteratorAdapter();
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = offset; i < offset + size; i++) {
            array[i - offset] = vec.elementAt(i);
        }
        return array;
    }

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

    // Supports null inserts
    public boolean add(Object o) {
        vec.insertElementAt(o, offset + size);
        incrementSize();
        return true;
    }

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

    public boolean addAll(int index, HCollection c) {
        if (index < 0 || index >  size)
            throw new IndexOutOfBoundsException();
        index += offset;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            add(index++, it.next());
        }
        return true;
    }

    public boolean removeAll(HCollection c) {
        if (c == null)
            throw new NullPointerException();
        HIterator it = c.iterator();
        boolean modified = false;
        while (it.hasNext()) {
            Object o = it.next();
            while (contains(o)) {
                remove(o);
                modified = true;
            }
        }
        return modified;
    }

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

    public void clear() {
        HListIterator it = this.listIterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public boolean equals(Object o) {
        if (o == null)
            throw new NullPointerException();
        return hashCode() == o.hashCode();
    }

    public int hashCode() {
        int hashCode = 1;
        HListIterator  i = listIterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

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

    public Object set(int index, Object element) {
        if (index < 0 || index >  size)
            throw new IndexOutOfBoundsException();
        index += offset;
        Object old = get(index);
        try {
            vec.setElementAt(element, index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
        return old;
    }

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

    public int indexOf(Object o) {
        for (int i = offset; i < offset + size; i++) {
            if (vec.elementAt(i).equals(o)) {
                return i - offset;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = offset + size - 1; i >= offset; i--) {
            if (vec.elementAt(i).equals(o)) {
                return i - offset;
            }
        }
        return -1;
    }

    public HListIterator listIterator() {
        return new HListIteratorAdapter();
    }

    public HListIterator listIterator(int index) {
        if (index < 0 || index >  size)
            throw new IndexOutOfBoundsException();
        index += offset;
        return new HListIteratorAdapter(index);
    }

    public HList subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >  size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        return new ListAdapter(this, fromIndex, toIndex);
    }

    private class HListIteratorAdapter implements HListIterator {
        private int cursor;
        private int lastRet = -1;

        public HListIteratorAdapter() {
            cursor = offset;
        }

        public HListIteratorAdapter(int cursor) {
            if (cursor < offset || cursor > offset + size)
                throw new IndexOutOfBoundsException();
            this.cursor = cursor;
        }

        public boolean hasNext() {
            return cursor != offset + size;
        }

        public Object next() {
            int i = cursor;
            if (i >= offset + size)
                throw new NoSuchElementException();
            cursor = i + 1;
            return get(lastRet = i - offset);
        }

        public boolean hasPrevious() {
            return cursor != offset;
        }

        public Object previous() {
            int i = cursor - 1;
            if (i < offset)
                throw new NoSuchElementException();
            cursor = i;
            return get(lastRet = i);
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public void remove() {
            if (lastRet == -1)
                throw new IllegalStateException();

            ListAdapter.this.remove(lastRet);

            cursor = lastRet + offset;
            lastRet = -1;
        }
        public void set(Object o) {
            if (lastRet == -1)
                throw new IllegalStateException();

            ListAdapter.this.set(lastRet, o);
        }

        public void add(Object o) {
            if (lastRet == -1)
                throw new IllegalStateException();
            int i = cursor;
            ListAdapter.this.add(i, o);
            cursor = i + 1;
            lastRet = -1;
        }
    }
}