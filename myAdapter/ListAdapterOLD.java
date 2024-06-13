package myAdapter;

import java.lang.reflect.Array;
import java.util.*;

// Suports Null
public class ListAdapterOLD implements HList {
    private Vector vec;

    public ListAdapterOLD() {
        vec = new Vector();
    }

    public ListAdapterOLD(ListAdapterOLD list, int start, int end) {
        vec = list.vec;
        for (int i = 0; i < start; i++) {
            vec.removeElementAt(0);
        }
        for (int i = vec.size() - 1; i > end; i--) {
            vec.removeElementAt(i);
        }
    }

    public int size() {
        return vec.size();
    }

    public boolean isEmpty() {
        return vec.isEmpty();
    }

    public boolean contains(Object o) {
        if (o == null)
            throw new NullPointerException();
        return vec.contains(o);
    }

    public HListIterator iterator() {
        return new HListIteratorAdapter();
    }

    public Object[] toArray() {
        Object[] array = new Object[vec.size()];
        vec.copyInto(array);
        return array;
    }

    public Object[] toArray(Object[] a) {
        if (a == null)
            throw new NullPointerException();
        if (a.length < vec.size()) {
            Object[] newArr = new Object[vec.size()];
            Object array = Array.newInstance(String.class, 10);
            vec.copyInto(newArr);
            return newArr;
        } else {
            vec.copyInto(a);
            return a;
        }
    }

    // Accetta anche i NULL
    public boolean add(Object o) {
        vec.addElement(o);
        return true;
    }

    public boolean remove(Object o) {
        return vec.removeElement(o);
    }

    public boolean containsAll(HCollection c) {
        if (c == null)
            throw new NullPointerException();
        HIterator it = c.iterator();
        while (it.hasNext()) {
            if (!vec.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(HCollection c) {
        HIterator it = c.iterator();
        while (it.hasNext()) {
            vec.addElement(it.next());
        }
        return true;
    }

    public boolean addAll(int index, HCollection c) {
        if (index < 0 || index > vec.size())
            throw new IndexOutOfBoundsException();
        HIterator it = c.iterator();
        while (it.hasNext()) {
            vec.insertElementAt(it.next(), index++);
        }
        return true;
    }

    public boolean removeAll(HCollection c) {
        if (c == null)
            throw new NullPointerException();
        HIterator it = c.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            while (vec.contains(o)) {
                vec.removeElement(o);
            }
        }
        return true;
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
        vec.removeAllElements();
    }

    public boolean equals(Object o) {
        return vec.equals(o);
    }

    public int hashCode() {
        return vec.hashCode();
    }

    public Object get(int index) {
        return vec.elementAt(index);
    }

    public String toString() {
        return vec.toString();
    }

    public Object set(int index, Object element) {
        Object old = get(index);
        try {
            vec.setElementAt(element, index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
        return old;
    }

    public void add(int index, Object element) {
        try {
            vec.insertElementAt(element, index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public Object remove(int index) {
        Object old = get(index);
        try {
            vec.removeElementAt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
        return old;
    }

    public int indexOf(Object o) {
        return vec.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return vec.lastIndexOf(o);
    }

    public HListIterator listIterator() {
        return new HListIteratorAdapter();
    }

    public HListIterator listIterator(int index) {
        if (index < 0 || index > vec.size())
            throw new IndexOutOfBoundsException();
        return new HListIteratorAdapter(index);
    }

    public HList subList(int fromIndex, int toIndex) {
        return new SubList(fromIndex, toIndex);
    }

    // classe ausiliaria per sublist (da capire se farla così)

    private class SubList implements HList {
        private final int offset;
        private int size;

        SubList(int fromIndex, int toIndex) {
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public boolean contains(Object o) {
            for (int i = offset; i < offset + size; i++) {
                if (get(i).equals(o)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Object[] toArray() {
            Object[] array = new Object[size];
            for (int i = offset; i < offset + size; i++) {
                array[i - offset] = get(i);
            }
            return array;
        }

        @Override
        public Object[] toArray(Object[] a) {
            if (a == null)
                throw new NullPointerException();
            if (a.length < size) {
                Object[] newArr = new Object[size];
                for (int i = offset; i < offset + size; i++) {
                    newArr[i - offset] = get(i);
                }
                return newArr;
            } else {
                for (int i = offset; i < offset + size; i++) {
                    a[i - offset] = get(i);
                }
                return a;
            }
        }

        @Override
        public boolean add(Object o) {
            vec.insertElementAt(o, offset + size);
            return true;
        }

        @Override
        public boolean remove(Object o) {
            for (int i = offset; i < offset + size; i++) {
                if (get(i).equals(o)) {
                    vec.remove(i);
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean containsAll(HCollection c) {
            return false;
        }

        @Override
        public boolean addAll(HCollection c) {
            return false;
        }

        @Override
        public boolean addAll(int index, HCollection c) {
            return false;
        }

        @Override
        public boolean removeAll(HCollection c) {
            return false;
        }

        @Override
        public boolean retainAll(HCollection c) {
            return false;
        }

        @Override
        public void clear() {
            for (int i = offset; i < offset + size; i++) {
                vec.remove(i);
            }
        }

        @Override
        public Object get(int index) {
            return null;
        }

        @Override
        public Object set(int index, Object element) {
            return null;
        }

        @Override
        public void add(int index, Object element) {

        }

        @Override
        public Object remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public HList subList(int offset, int toIndex) {
            return null;
        }


        @Override
        public HIterator iterator() {
            return new SubListIterator(0);
        }

        @Override
        public HListIterator listIterator() {
            return new SubListIterator(0);
        }

        @Override
        public HListIterator listIterator(int index) {
            if (index < 0 || index > size()) {
                throw new IndexOutOfBoundsException();
            }
            return new SubListIterator(index);
        }

        private class SubListIterator implements HListIterator {
            int lastRet = -1;
            int cursor = 0;

            SubListIterator(int index) {
                cursor = index;
            }

            @Override
            public boolean hasNext() {
                return cursor < size();
            }

            @Override
            public Object next() {
                if (cursor >= size()) {
                    throw new NoSuchElementException();
                }
                return vec.get(offset + cursor++);
            }

            @Override
            public boolean hasPrevious() {
                return cursor > 0;
            }

            @Override
            public Object previous() {
                if (cursor <= 0) {
                    throw new NoSuchElementException();
                }
                return vec.get(offset + --cursor);
            }

            @Override
            public int nextIndex() {
                return cursor;
            }

            @Override
            public int previousIndex() {
                return cursor - 1;
            }

            @Override
            public void set(Object e) {
                if (lastRet < 0) {
                    throw new IllegalStateException();
                }
                vec.set(offset + lastRet, e);
            }

            @Override
            public void add(Object e) {
                int i = cursor;
                vec.add(offset + i, e);
                cursor++;
                size++;
                lastRet = -1;
            }

            @Override
            public void remove() {
                if (lastRet < 0) {
                    throw new IllegalStateException();
                }
                vec.remove(offset + lastRet);
                cursor = lastRet;
                size--;
                lastRet = -1;
            }

        }

    }


    /**
     * FATTO
     */
    private class HListIteratorAdapter implements HListIterator {
        private int cursor;
        private int lastRet = -1;

        public HListIteratorAdapter() {
            cursor = 0;
        }

        public HListIteratorAdapter(int cursor) {
            if (cursor < 0 || cursor > vec.size())
                throw new IndexOutOfBoundsException();
            this.cursor = cursor;
        }

        /**
         * FATTO
         */
        @Override
        public boolean hasNext() {
            return cursor != vec.size();
        }

        /**
         * FATTO
         */
        @Override
        public Object next() {
            int i = cursor;
            if (i >= vec.size())
                throw new NoSuchElementException();
            cursor = i + 1;
            return vec.elementAt(lastRet = i);
        }

        /**
         * FATTO
         */
        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        /**
         * FATTO
         */
        @Override
        public Object previous() {
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            cursor = i;
            return vec.elementAt(lastRet = i);
        }

        /**
         * FATTO
         */
        @Override
        public int nextIndex() {
            return cursor;
        }

        /**
         * FATTO
         */
        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * FATTO
         */
        @Override
        public void remove() {
            if (lastRet == -1)
                throw new IllegalStateException();

            vec.remove(lastRet);

            cursor = lastRet;
            lastRet = -1;
        }

        /**
         * FATTO
         */
        @Override
        public void set(Object o) {
            if (lastRet == -1)
                throw new IllegalStateException();

            vec.set(lastRet, o);
        }

        /**
         * FATTO
         */
        @Override
        public void add(Object o) {
            int i = cursor;
            vec.add(i, o);
            cursor = i + 1;
            lastRet = -1;
        }
    }
}