package myTest;

import myAdapter.*;
import org.junit.*;
import java.util.NoSuchElementException;

public class ListAdapterTest {

    /**
     * ListAdapter Constructor Family Tests
     */

    @Test
    public void listAdapter_ShouldBeCreated_FromAnotherList() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter(list);
        Assert.assertEquals(0, other.size());
        Assert.assertNotNull(other);
    }

    /**
     * ListAdapter Add Family Tests
     */

    @Test
    public void initialSize_ShouldBe_Zero() {
        ListAdapter list = new ListAdapter();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void list_ShouldBe_Empty() {
        ListAdapter list = new ListAdapter();
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void size_ShouldBe_Three() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals(3, list.size());
    }


    @Test
    public void list_ShouldContain_OneElement() {
        ListAdapter list = new ListAdapter();
        Assert.assertTrue(list.add("element"));
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void list_ShouldPermit_NullAddition() {
        ListAdapter list = new ListAdapter();
        Assert.assertTrue(list.add(null));
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void list_ShouldAdd_ElementsFromOtherList() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter();
        other.add("element1");
        other.add("element2");
        Assert.assertTrue(list.addAll(other));
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void list_ShouldNotAdd_ElementsFromOtherList() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter();
        Assert.assertFalse(list.addAll(other));
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void addAll_ShouldThrow_NullPointerException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(NullPointerException.class, () -> list.addAll(null));
    }

    @Test
    public void list_ShouldAdd_ElementsFromOtherList_AtIndex() {
        ListAdapter list = new ListAdapter();
        list.add("element1");
        list.add("element2");
        ListAdapter other = new ListAdapter();
        other.add("otherElement1");
        other.add("otherElement2");
        Assert.assertTrue(list.addAll(1, other));
        Assert.assertEquals(4, list.size());
    }

    @Test
    public void addAll_ShouldThrow_IndexException() {
        ListAdapter list = new ListAdapter();
        list.add("element1");
        list.add("element2");
        ListAdapter other = new ListAdapter();
        other.add("otherElement1");
        other.add("otherElement2");
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(5, other));
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void addAll_ShouldThrow_IndexException_Negative() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(-1, null));
    }


    /**
     * ListAdapter Contains Family Tests
     */

    @Test
    public void list_ShouldContain_Elements() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertTrue(list.contains("a"));
        Assert.assertTrue(list.contains("b"));
        Assert.assertTrue(list.contains("c"));
        Assert.assertFalse(list.contains("d"));
    }

    @Test
    public void list_ShouldContain_AllElements() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("a");
        other.add("b");
        Assert.assertTrue(list.containsAll(other));
    }

    @Test
    public void list_ShouldNotContain_AllElements() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("a");
        other.add("b");
        other.add("d");
        Assert.assertFalse(list.containsAll(other));
    }

    @Test
    public void containsAll_ShouldThrow_NullPointerException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(NullPointerException.class, () -> list.containsAll(null));
    }

    @Test
    public void list_ShouldContain_Null() {
        ListAdapter list = new ListAdapter();
        list.add(null);
        Assert.assertTrue(list.contains(null));
    }

    @Test
    public void list_ShouldNotContain_Null() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        Assert.assertFalse(list.contains(null));
    }

    @Test
    public void contains_ShouldReturn_False() {
        ListAdapter list = new ListAdapter();
        list.add(null);
        Assert.assertFalse(list.contains("element"));
    }

    /**
     * ListAdapter toArray Family Tests
     */

    @Test
    public void toArray_ShouldReturn_Array() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Object[] array = list.toArray();
        Assert.assertEquals(3, array.length);
        Assert.assertEquals("a", array[0]);
        Assert.assertEquals("b", array[1]);
        Assert.assertEquals("c", array[2]);
    }

    @Test
    public void toArray_ShouldReturn_EmptyArray() {
        ListAdapter list = new ListAdapter();
        Object[] array = list.toArray();
        Assert.assertEquals(0, array.length);
    }

    @Test
    public void toArray_ShouldReturn_ArrayWithSizeAndNull() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Object[] array = new Object[5];
        list.toArray(array);
        Assert.assertEquals(5, array.length);
        Assert.assertEquals("a", array[0]);
        Assert.assertEquals("b", array[1]);
        Assert.assertEquals("c", array[2]);
        Assert.assertNull(array[3]);
    }

    @Test
    public void toArray_ShouldReturn_ArrayWithExactSize() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Object[] array = new Object[3];
        list.toArray(array);
        Assert.assertEquals(3, array.length);
        Assert.assertEquals("a", array[0]);
        Assert.assertEquals("b", array[1]);
        Assert.assertEquals("c", array[2]);
    }



    @Test
    public void toArray_ShouldReturn_NewProperlySizedArray() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Object[] array = new Object[2];
        Object[] array2 = list.toArray(array);
        Assert.assertEquals(3, array2.length);
        Assert.assertEquals("a", array2[0]);
        Assert.assertEquals("b", array2[1]);
        Assert.assertEquals("c", array2[2]);
    }

    @Test
    public void toArray_ShouldThrow_NullPointerException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(NullPointerException.class, () -> list.toArray(null));
    }

    /**
     * ListAdapter Remove Family Tests
     */

    @Test
    public void remove_ShouldRemove_Element() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertTrue(list.remove("b"));
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void remove_ShouldNotRemove_Element() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertFalse(list.remove("d"));
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void removeAll_ShouldRemove_Elements() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("a");
        other.add("b");
        Assert.assertTrue(list.removeAll(other));
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void removeAll_ShouldNotRemove_Elements() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("e");
        other.add("d");
        Assert.assertFalse(list.removeAll(other));
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void removeAll_ShouldThrow_NullPointerException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(NullPointerException.class, () -> list.removeAll(null));
    }

    @Test
    public void remove_ShouldThrow_IndexException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    }

    /**
     * ListAdapter Retain Family Tests
     */

    @Test
    public void retainAll_ShouldRetain_Elements() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("a");
        other.add("b");
        Assert.assertTrue(list.retainAll(other));
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void retainAll_ShouldRetain_AllElements() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("a");
        other.add("b");
        other.add("c");
        Assert.assertFalse(list.retainAll(other));
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void retainAll_ShouldNotRetain_Elements() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("e");
        other.add("d");
        Assert.assertTrue(list.retainAll(other));
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void retainAll_ShouldThrow_NullPointerException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(NullPointerException.class, () -> list.retainAll(null));
    }

    /**
     * ListAdapter clear Family Tests
     */

    @Test
    public void clear_ShouldClear_List() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        list.clear();
        Assert.assertEquals(0, list.size());
    }

    /**
     * ListAdapter equals Family Tests
     */

    @Test
    public void equals_ShouldReturn_True() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("a");
        other.add("b");
        other.add("c");
        Assert.assertEquals(list, other);
    }

    @Test
    public void equals_ShouldReturn_False() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("a");
        other.add("b");
        other.add("d");
        Assert.assertNotEquals(list, other);
    }

    @Test
    public void equals_ShouldThrow_NullPointer() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(NullPointerException.class, () -> list.equals(null));
    }

    /**
     * ListAdapter hashCode Family Tests
     */

    @Test
    public void hashCode_ShouldReturn_Same() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("a");
        other.add("b");
        other.add("c");
        Assert.assertEquals(list.hashCode(), other.hashCode());
    }

    @Test
    public void hashCode_ShouldReturn_Different() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("a");
        other.add("b");
        other.add("d");
        Assert.assertNotEquals(list.hashCode(), other.hashCode());
    }

    @Test
    public void hashCode_ShouldReturn_Same_Empty() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter();
        Assert.assertEquals(list.hashCode(), other.hashCode());
    }

    @Test
    public void hashCode_ShouldReturn_Different_Empty() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter();
        other.add("a");
        Assert.assertNotEquals(list.hashCode(), other.hashCode());
    }

    @Test
    public void hashCode_WithNull_ShouldReturn_Same() {
        ListAdapter list = new ListAdapter();
        list.add(null);
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add(null);
        other.add("b");
        other.add("c");
        Assert.assertEquals(list.hashCode(), other.hashCode());
    }

    /**
     * ListAdapter toString Family Tests
     */

    @Test
    public void toString_ShouldReturn_String() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals("[a, b, c]", list.toString());
    }

    @Test
    public void toString_ShouldReturn_EmptyString() {
        ListAdapter list = new ListAdapter();
        Assert.assertEquals("[]", list.toString());
    }


    /**
     * ListAdapter get Family Tests
     */

    @Test
    public void get_ShouldReturn_Element() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals("b", list.get(1));
    }

    @Test
    public void get_ShouldThrow_IndexError() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    @Test
    public void get_ShouldThrow_IndexError_Negative() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    /**
     * ListAdapter set Family Tests
     */

    @Test
    public void set_ShouldSet_Element() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals("b", list.set(1, "d"));
        Assert.assertEquals("d", list.get(1));
    }

    @Test
    public void set_ShouldThrow_IndexError() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, "element"));
    }

    @Test
    public void set_ShouldThrow_IndexError_Negative() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "element"));
    }

    @Test
    public void set_ShouldThrow_IndexError_Empty() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "element"));
    }

    /**
     * ListAdapter add Family Tests
     */

    @Test
    public void add_ShouldAdd_Element() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add(1, "d");
        Assert.assertEquals("d", list.get(1));
    }

    @Test
    public void add_ShouldThrow_IndexError() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, "element"));
    }

    /**
     * ListAdapter indexOf Family Tests
     */

    @Test
    public void indexOf_ShouldReturn_Index() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals(1, list.indexOf("b"));
    }

    @Test
    public void indexOf_ShouldReturn_MinusOne() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals(-1, list.indexOf("d"));
    }

    /**
     * ListAdapter lastIndexOf Family Tests
     */

    @Test
    public void lastIndexOf_ShouldReturn_Index() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        Assert.assertEquals(2, list.lastIndexOf("b"));
    }

    @Test
    public void lastIndexOf_ShouldReturn_MinusOne() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals(-1, list.lastIndexOf("d"));
    }

    /**
     * ListAdapter subList Family Tests
     */

    @Test
    public void subList_ShouldReturn_List() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter subList = (ListAdapter) list.subList(1, 3);
        Assert.assertEquals(2, subList.size());
        Assert.assertEquals("b", subList.get(0));
        Assert.assertEquals("c", subList.get(1));
    }

    @Test
    public void subList_ShouldReturn_EmptyList() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter subList = (ListAdapter) list.subList(1, 1);
        Assert.assertEquals(0, subList.size());
    }

    @Test
    public void subList_ShouldReturn_FullList() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter subList = (ListAdapter) list.subList(0, 3);
        Assert.assertEquals(3, subList.size());
        Assert.assertEquals("a", subList.get(0));
        Assert.assertEquals("b", subList.get(1));
        Assert.assertEquals("c", subList.get(2));
    }

    @Test
    public void subListClear_ShouldClear_OriginalList() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter subList = (ListAdapter) list.subList(1, 3);
        subList.clear();
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void subListAdd_ShouldAdd_OriginalList() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter subList = (ListAdapter) list.subList(1, 3);
        subList.add(1, "d");
        Assert.assertEquals(4, list.size());
        Assert.assertEquals("d", list.get(2));
    }

    @Test
    public void subList_ShouldThrow_IndexError() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.subList(1, 5));
    }

    @Test
    public void subList_ShouldThrow_IndexError_FromIndex() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.subList(5, 1));
    }


    /**
     * ListAdapter iterator Family Tests
     */

    @Test
    public void Iterator_ShouldThrow_IndexError() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.listIterator(5));
    }

    @Test
    public void Iterator_ShouldHave_NextItem() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("element", iterator.next());
    }

    @Test
    public void iterator_ShouldThrow_NoSuchElementException() {
        ListAdapter list = new ListAdapter();
        HListIterator iterator = list.listIterator();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void iterator_ShouldHave_PreviousItem() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        iterator.next();
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals("element", iterator.previous());
    }

    @Test
    public void iterator_ShouldThrow_NoSuchElementException_Previous() {
        ListAdapter list = new ListAdapter();
        HListIterator iterator = list.listIterator();
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertThrows(NoSuchElementException.class, iterator::previous);
    }

    @Test
    public void iterator_ShouldHave_NextIndex() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        Assert.assertEquals(0, iterator.nextIndex());
        iterator.next();
        Assert.assertEquals(1, iterator.nextIndex());
    }

    @Test
    public void iterator_ShouldHave_PreviousIndex() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        Assert.assertEquals(-1, iterator.previousIndex());
        iterator.next();
        Assert.assertEquals(0, iterator.previousIndex());
    }

    @Test
    public void iterator_ShouldRemove_Item() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        iterator.next();
        iterator.remove();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void iterator_ShouldSet_Item() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        iterator.next();
        iterator.set("newElement");
        Assert.assertEquals("newElement", list.get(0));
    }

    @Test
    public void iterator_ShouldAdd_Item() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        iterator.next();
        iterator.add("newElement");
        Assert.assertEquals("element", list.get(0));
    }

    @Test
    public void iterator_ShouldThrow_IllegalStateException_Remove() {
        ListAdapter list = new ListAdapter();
        HListIterator iterator = list.listIterator();
        Assert.assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void iterator_ShouldThrow_IllegalStateException_Set() {
        ListAdapter list = new ListAdapter();
        HListIterator iterator = list.listIterator();
        Assert.assertThrows(IllegalStateException.class, () -> iterator.set("element"));
    }

    @Test
    public void iterator_ShouldThrow_IllegalStateException_Add() {
        ListAdapter list = new ListAdapter();
        HListIterator iterator = list.listIterator();
        Assert.assertThrows(IllegalStateException.class, () -> iterator.add("element"));
    }

    @Test
    public void iterator_ShouldReturn_IteratorAtIndex() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        list.add("element2");
        HListIterator iterator = list.listIterator(1);
        Assert.assertEquals("element2", iterator.next());
    }
}