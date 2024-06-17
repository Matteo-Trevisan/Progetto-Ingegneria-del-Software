package myTest;

import myAdapter.*;
import org.junit.*;

import java.util.NoSuchElementException;

/**
 * <p>
 * Contains a series of unit tests designed to verify the functionality of the ListAdapter class.
 * The tests are organized into logical groups based on the methods they are testing.
 * Each test case is designed to test a specific functionality or behavior of the method under test.
 * </p>
 * <p>
 * This test suite is written using Junit 4.13.2, which files can be found in JUnit folder (junit-4.13.2.jar and hamcrest-core-1.3.jar).
 * </p>
 * <p>
 * Test Case Design: <br>
 * The test cases in this class follow a similar design pattern.
 * Each test case starts by setting up the necessary preconditions, which often involves creating a new instance of the ListAdapter class and populating it with some initial data.
 * The method under test is then invoked, and its output or effect is captured.
 * Finally, assertions are made to verify that the observed output or effect matches the expected output or effect.
 * <br>
 * For example, In the test case 'ListIterator_ShouldThrow_NoSuchElementException', a new ListAdapter is created and a listIterator is created from it.
 * The test then verifies that calling the next method on the listIterator throws a NoSuchElementException, as expected.
 * </p>
 * <p>
 * Each test case is designed to be independent of the others, meaning that the outcome of one test does not affect the outcome of another test.
 * This design allows for each test case to be run in isolation and in any order.
 * </p>
 *
 * @author Matteo Trevisan
 */
public class ListAdapterTest {

    /*
     * ListAdapter Constructor Family Tests
     */

    /**
     * Verifies that a new ListAdapter can be created from another ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new empty ListAdapter and then creates another ListAdapter by passing the first one as a parameter to the constructor.
     * @doc.testDescription The test verifies that the new ListAdapter has been correctly created and that it is empty, like the original ListAdapter.
     * @doc.preCondition The original ListAdapter is empty.
     * @doc.postCondition The new ListAdapter has been created and is empty.
     * @doc.expectedResults The test expects the new ListAdapter to be non-null and its size to be 0.
     */
    @Test
    public void listAdapter_ShouldBeCreated_FromAnotherList() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter(list);
        Assert.assertEquals(0, other.size());
        Assert.assertNotNull(other);
    }

    /**
     * Verifies that a new ListAdapter can be created from another ListAdapter with a range of elements.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter with three elements and then creates another ListAdapter by passing the first one as a parameter to the constructor.
     * @doc.testDescription The test verifies that the new ListAdapter has been correctly created and that it contains the elements from the original ListAdapter.
     * @doc.preCondition The original ListAdapter has three elements.
     * @doc.postCondition The new ListAdapter has been created and contains the elements from the original ListAdapter.
     * @doc.expectedResults The test expects the new ListAdapter to be non-null and its size to be 3.
     */
    @Test
    public void listAdapter_ShouldBeCreated_FromAnotherListWithRange() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter(list);
        Assert.assertNotNull(other);
        Assert.assertEquals(3, other.size());
        Assert.assertEquals("a", other.get(0));
        Assert.assertEquals("b", other.get(1));
        Assert.assertEquals("c", other.get(2));
    }

    /*
     * ListAdapter Add Family Tests
     */

    /**
     * Verifies that the initial size of a new ListAdapter is zero.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and checks its size.
     * @doc.testDescription The test verifies that the size of the new ListAdapter is 0.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition The size of the ListAdapter is verified.
     * @doc.expectedResults The test expects the size of the ListAdapter to be 0.
     */
    @Test
    public void initialSize_ShouldBe_Zero() {
        ListAdapter list = new ListAdapter();
        Assert.assertEquals(0, list.size());
    }

    /**
     * Verifies the behavior of the addAll method when the collection to be added contains null elements.
     *
     * @doc.testCaseDesign The test creates two ListAdapter instances. One of them, other, is populated with a null element.
     * Then, the addAll method of the first ListAdapter instance, list, is called with other as an argument.
     * @doc.testDescription The test checks if the addAll method can handle null elements without throwing exceptions and
     * if the null element is correctly added to list.
     * @doc.preCondition The other ListAdapter instance contains a null element.
     * @doc.postCondition The list ListAdapter instance contains the null element from other.
     * @doc.expectedResults The addAll method should not throw an exception when adding a collection with null elements.
     * The list ListAdapter instance should contain the null element after the addAll operation.
     */
    @Test
    public void addAll_ShouldHandle_NullElements() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter();
        other.add(null);
        list.addAll(other);
        Assert.assertEquals(1, list.size());
        Assert.assertNull(list.get(0));
    }

    /**
     * Verifies that a new ListAdapter is empty.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and checks if it is empty.
     * @doc.testDescription The test verifies that the new ListAdapter is empty.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition The empty condition of the ListAdapter is verified.
     * @doc.expectedResults The test expects the ListAdapter to be empty.
     */
    @Test
    public void isEmpty_ShouldReturn_True() {
        ListAdapter list = new ListAdapter();
        Assert.assertTrue(list.isEmpty());
    }

    /**
     * Verifies that the size method correctly returns the size of the ListAdapter after adding elements.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then calls the size method.
     * @doc.testDescription The test verifies that the size method returns the correct size of the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The size method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the size method to return the correct size of the ListAdapter.
     */
    @Test
    public void size_ShouldReturn_CorrectSize() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals(3, list.size());
    }

    /**
     * Verifies that the size method correctly returns 0 for an empty ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and calls the size method.
     * @doc.testDescription The test verifies that the size method returns 0 for an empty ListAdapter.
     * @doc.preCondition The ListAdapter has just been created and is empty.
     * @doc.postCondition The size method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the size method to return 0 for an empty ListAdapter.
     */
    @Test
    public void size_ShouldReturn_Zero() {
        ListAdapter list = new ListAdapter();
        Assert.assertEquals(0, list.size());
    }

    /**
     * Verifies that the size method correctly returns the size of the ListAdapter after removing elements.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter, adds some elements to it, removes some elements, and then calls the size method.
     * @doc.testDescription The test verifies that the size method returns the correct size of the ListAdapter after elements have been removed.
     * @doc.preCondition The ListAdapter has been initialized, populated with elements, and some elements have been removed.
     * @doc.postCondition The size method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the size method to return the correct size of the ListAdapter after elements have been removed.
     */
    @Test
    public void size_ShouldReturn_CorrectSize_AfterRemoval() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        list.remove(1);
        Assert.assertEquals(2, list.size());
    }


    /**
     * Verifies that add returns true and ListAdapter contains an element.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter, adds an element and checks its size.
     * @doc.testDescription The test verifies that the ListAdapter contains an element after adding an element.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition The size of the ListAdapter after adding an element is verified.
     * @doc.expectedResults The test expects the size of the ListAdapter to be 1.
     */
    @Test
    public void add_ShouldReturn_True_x1() {
        ListAdapter list = new ListAdapter();
        Assert.assertTrue(list.add("element"));
        Assert.assertEquals(1, list.size());
    }


    /**
     * Verifies that add returns true and ListAdapter contains a null element.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter, adds a null element and checks its size.
     * @doc.testDescription The test verifies that the ListAdapter contains a null element after adding a null element.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition The size of the ListAdapter after adding a null element is verified.
     * @doc.expectedResults The test expects the size of the ListAdapter to be 1.
     */
    @Test
    public void add_ShouldPermit_NullAddition() {
        ListAdapter list = new ListAdapter();
        Assert.assertTrue(list.add(null));
        Assert.assertEquals(1, list.size());
    }


    /**
     * Verifies that the addAll method can add elements from another collection to the ListAdapter.
     *
     * @doc.testCaseDesign The test creates two new ListAdapters. The first one is empty and the second one contains two elements. The test then adds the elements from the second ListAdapter to the first one using the addAll method.
     * @doc.testDescription The test verifies that the first ListAdapter contains the elements from the second ListAdapter after the addAll operation.
     * @doc.preCondition The first ListAdapter is empty and the second ListAdapter contains two elements.
     * @doc.postCondition The first ListAdapter contains the elements from the second ListAdapter.
     * @doc.expectedResults The test expects the first ListAdapter to be non-empty and its size to be 2 after the addAll operation.
     */
    @Test
    public void addAll_ShouldAdd_fromAnotherCollection() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter();
        other.add("element1");
        other.add("element2");
        Assert.assertTrue(list.addAll(other));
        Assert.assertEquals(2, list.size());
    }

    /**
     * Verifies that addAll returns false and ListAdapter is empty.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and another empty ListAdapter, then adds the second ListAdapter to the first one.
     * @doc.testDescription The test verifies that the ListAdapter is empty after adding an empty ListAdapter.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition The size of the ListAdapter after adding an empty ListAdapter is verified.
     * @doc.expectedResults The test expects the size of the ListAdapter to be 0.
     */
    @Test
    public void addAll_ShouldReturn_False() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter();
        Assert.assertFalse(list.addAll(other));
        Assert.assertEquals(0, list.size());
    }

    /**
     * Verifies that the addAll method throws a NullPointerException when null is passed as an argument.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to add null to it using the addAll method.
     * @doc.testDescription The test verifies that a NullPointerException is thrown when null is passed to the addAll method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition A NullPointerException is thrown.
     * @doc.expectedResults The test expects a NullPointerException to be thrown.
     */
    @Test
    public void addAll_ShouldThrow_NullPointerException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(NullPointerException.class, () -> list.addAll(null));
    }


    /**
     * Verifies that the addAll method can add elements from another Collection at a specific index in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates two new ListAdapters. The first one contains two elements and the second one contains two different elements. The test then adds the elements from the second ListAdapter to the first one at a specific index using the addAll method.
     * @doc.testDescription The test verifies that the first ListAdapter contains the elements from the second ListAdapter at the correct index after the addAll operation.
     * @doc.preCondition The first ListAdapter contains two elements and the second ListAdapter contains two different elements.
     * @doc.postCondition The first ListAdapter contains the elements from the second ListAdapter at the correct index.
     * @doc.expectedResults The test expects the first ListAdapter to contain the elements from the second ListAdapter at the correct index and its size to be 4 after the addAll operation.
     */
    @Test
    public void addAll_ShouldAdd_ElementsFromOtherList_AtIndex() {
        ListAdapter list = new ListAdapter();
        list.add("element1");
        list.add("element2");
        ListAdapter other = new ListAdapter();
        other.add("otherElement1");
        other.add("otherElement2");
        Assert.assertTrue(list.addAll(1, other));
        Assert.assertEquals(4, list.size());
    }


    /**
     * Verifies that the addAll method throws an IndexOutOfBoundsException when an invalid index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to add elements at an invalid index using the addAll method.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when an invalid index is provided to the addAll method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
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

    /**
     * Verifies that the addAll method throws an IndexOutOfBoundsException when a negative index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to add elements at a negative index using the addAll method.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when a negative index is provided to the addAll method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void addAll_ShouldThrow_IndexException_Negative() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter();
        other.add("otherElement1");
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(-1, other));
    }

    /**
     * Verifies that the add method throws an IndexOutOfBoundsException when an invalid index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to add an element at an invalid index.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when an invalid index is provided to the add method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void add_ShouldThrow_IndexException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, "element"));
    }

    /**
     * Verifies the behavior of the `addAll` method when an empty collection is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter instance and then attempts to add all elements from an empty collection to it.
     * @doc.testDescription The test checks if the `addAll` method correctly returns false when the provided collection is empty.
     * @doc.preCondition The ListAdapter instance is newly created and the provided collection is empty.
     * @doc.postCondition The ListAdapter instance remains unchanged after the call to `addAll`.
     * @doc.expectedResults The `addAll` method should return false when the provided collection is empty.
     */
    @Test
    public void addAll_ShouldReturnFalse_WhenCollectionIsEmpty() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter();
        Assert.assertFalse(list.addAll(other));
    }

    /**
     * Verifies that the add method throws an IndexOutOfBoundsException when a negative index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to add an element at a negative index.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when a negative index is provided to the add method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void Add_ShouldThrow_IndexException_Negative() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "element"));
    }

    /**
     * Verifies that the addAll method throws a NullPointerException when null is passed as the collection to be added at a specific index.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to add null at a specific index using the addAll method.
     * @doc.testDescription The test verifies that a NullPointerException is thrown when null is passed to the addAll method at a specific index.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition A NullPointerException is thrown.
     * @doc.expectedResults The test expects a NullPointerException to be thrown.
     */
    @Test
    public void addAll_ShouldThrow_NullPointerException_WithIndex() {
        ListAdapter list = new ListAdapter();
        list.add("element1");
        list.add("element2");
        Assert.assertThrows(NullPointerException.class, () -> list.addAll(1, null));
    }


    /*
     * ListAdapter Contains Family Tests
     */


    /**
     * Verifies that the contains method correctly identifies the presence of an element in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then checks if the ListAdapter contains each of these elements.
     * @doc.testDescription The test verifies that the contains method returns true for each of the elements added to the ListAdapter.
     * @doc.preCondition The ListAdapter contains three elements.
     * @doc.postCondition The contains method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the contains method to return true for each of the elements present in the ListAdapter.
     */
    @Test
    public void contains_ShouldCorrectly_VerifyElementPresence() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertTrue(list.contains("a"));
        Assert.assertTrue(list.contains("b"));
        Assert.assertTrue(list.contains("c"));
        Assert.assertFalse(list.contains("d"));
    }

    /**
     * Verifies that the containsAll method correctly identifies the presence of all elements from another Collection in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates two new ListAdapters. The first one contains three elements and the second one contains two of these elements. The test then checks if the first ListAdapter contains all elements from the second ListAdapter.
     * @doc.testDescription The test verifies that the containsAll method returns true as all elements from the second ListAdapter are present in the first ListAdapter.
     * @doc.preCondition The first ListAdapter contains three elements and the second ListAdapter contains two of these elements.
     * @doc.postCondition The containsAll method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the containsAll method to return true as all elements from the second ListAdapter are present in the first ListAdapter.
     */
    @Test
    public void containsAll_ShouldReturn_True() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter other = new ListAdapter();
        other.add("a");
        other.add("b");
        Assert.assertTrue(list.containsAll(other));
    }

    /**
     * Verifies the behavior of the contains method when the element being searched for is not present in the list.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter instance and adds a single element to it. It then calls the contains method with an element that was not added to the list.
     * @doc.testDescription The test checks if the contains method correctly returns false when the element being searched for is not present in the list.
     * @doc.preCondition The ListAdapter instance contains a single element which is different from the element being searched for.
     * @doc.postCondition The ListAdapter instance remains unchanged after the call to contains.
     * @doc.expectedResults The contains method should return false when the element being searched for is not present in the list.
     */
    @Test
    public void contains_ShouldReturnFalse_WhenElementNotPresent() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        boolean result = list.contains("b");
        Assert.assertFalse(result);
    }

    /**
     * Verifies that the containsAll method correctly identifies when not all elements from another Collection are present in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates two new ListAdapters. The first one contains three elements and the second one contains an element that is not present in the first ListAdapter. The test then checks if the first ListAdapter contains all elements from the second ListAdapter.
     * @doc.testDescription The test verifies that the containsAll method returns false as not all elements from the second ListAdapter are present in the first ListAdapter.
     * @doc.preCondition The first ListAdapter contains three elements and the second ListAdapter contains an element that is not present in the first ListAdapter.
     * @doc.postCondition The containsAll method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the containsAll method to return false as not all elements from the second ListAdapter are present in the first ListAdapter.
     */
    @Test
    public void containsAll_ShouldReturn_False() {
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

    /**
     * Verifies that the containsAll method throws a NullPointerException when null is passed as an argument.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to call the containsAll method with null as the argument.
     * @doc.testDescription The test verifies that a NullPointerException is thrown when null is passed to the containsAll method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition A NullPointerException is thrown.
     * @doc.expectedResults The test expects a NullPointerException to be thrown.
     */
    @Test
    public void containsAll_ShouldThrow_NullPointerException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(NullPointerException.class, () -> list.containsAll(null));
    }

    /**
     * Verifies that the contains method correctly identifies the presence of a null element in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds a null element to it. It then checks if the ListAdapter contains the null element.
     * @doc.testDescription The test verifies that the contains method returns true for the null element added to the ListAdapter.
     * @doc.preCondition The ListAdapter contains a null element.
     * @doc.postCondition The contains method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the contains method to return true for the null element present in the ListAdapter.
     */
    @Test
    public void list_ShouldContain_Null() {
        ListAdapter list = new ListAdapter();
        list.add(null);
        Assert.assertTrue(list.contains(null));
    }

    /**
     * Verifies that the contains method correctly identifies the absence of a null element in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds an element to it. It then checks if the ListAdapter contains a null element.
     * @doc.testDescription The test verifies that the contains method returns false for a null element that is not present in the ListAdapter.
     * @doc.preCondition The ListAdapter contains an element.
     * @doc.postCondition The contains method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the contains method to return false for the null element that is not present in the ListAdapter.
     */
    @Test
    public void list_ShouldNotContain_Null() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        Assert.assertFalse(list.contains(null));
    }

    /**
     * Verifies that the contains method correctly identifies the absence of an element in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds an element to it. It then checks if the ListAdapter contains an element that is not present in it.
     * @doc.testDescription The test verifies that the contains method returns false for an element that is not present in the ListAdapter.
     * @doc.preCondition The ListAdapter contains an element.
     * @doc.postCondition The contains method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the contains method to return false for the element that is not present in the ListAdapter.
     */
    @Test
    public void contains_ShouldReturn_False() {
        ListAdapter list = new ListAdapter();
        list.add(null);
        Assert.assertFalse(list.contains("element"));
    }

    /*
     * ListAdapter toArray Family Tests
     */

    /**
     * Verifies that the toArray method correctly converts the ListAdapter to an array.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then calls the toArray method and checks the returned array.
     * @doc.testDescription The test verifies that the toArray method returns an array with the same elements as the ListAdapter.
     * @doc.preCondition The ListAdapter contains three elements.
     * @doc.postCondition The toArray method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the toArray method to return an array with the same elements as the ListAdapter.
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

    /**
     * Verifies that the toArray method correctly converts an empty ListAdapter to an empty array.
     *
     * @doc.testCaseDesign The test creates a new empty ListAdapter and then calls the toArray method and checks the returned array.
     * @doc.testDescription The test verifies that the toArray method returns an empty array when called on an empty ListAdapter.
     * @doc.preCondition The ListAdapter is empty.
     * @doc.postCondition The toArray method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the toArray method to return an empty array when the ListAdapter is empty.
     */
    @Test
    public void toArray_ShouldReturn_EmptyArray() {
        ListAdapter list = new ListAdapter();
        Object[] array = list.toArray();
        Assert.assertEquals(0, array.length);
    }

    /**
     * Verifies that the toArray method correctly converts the ListAdapter to an array of a larger size, filling the one extra spaces with null.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then calls the toArray method with an array of larger size as the argument and checks the returned array.
     * @doc.testDescription The test verifies that the toArray method returns an array with the same elements as the ListAdapter and one extra spaces filled with null.
     * @doc.preCondition The ListAdapter contains three elements.
     * @doc.postCondition The toArray method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the toArray method to return an array with the same elements as the ListAdapter and the extra spaces filled with null.
     */
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

    /**
     * Verifies that the toArray method correctly converts the ListAdapter to an array of the exact same size.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then calls the toArray method with an array of the same size as the argument and checks the returned array.
     * @doc.testDescription The test verifies that the toArray method returns an array with the same elements and the same size as the ListAdapter.
     * @doc.preCondition The ListAdapter contains three elements.
     * @doc.postCondition The toArray method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the toArray method to return an array with the same elements and the same size as the ListAdapter.
     */
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

    /**
     * Verifies that the toArray method correctly converts the ListAdapter to an array of the exact same size, even when the provided array is smaller.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then calls the toArray method with an array of smaller size as the argument and checks the returned array.
     * @doc.testDescription The test verifies that the toArray method returns a new array with the same elements as the ListAdapter and the same size as the ListAdapter, not the size of the provided array.
     * @doc.preCondition The ListAdapter contains three elements.
     * @doc.postCondition The toArray method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the toArray method to return a new array with the same elements and the same size as the ListAdapter, not the size of the provided array.
     */
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

    /**
     * Verifies that the toArray method throws a NullPointerException when null is passed as an argument.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to call the toArray method with null as the argument.
     * @doc.testDescription The test verifies that a NullPointerException is thrown when null is passed to the toArray method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition A NullPointerException is thrown.
     * @doc.expectedResults The test expects a NullPointerException to be thrown.
     */
    @Test
    public void toArray_ShouldThrow_NullPointerException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(NullPointerException.class, () -> list.toArray(null));
    }

    /*
     * ListAdapter Remove Family Tests
     */

    /**
     * Verifies that the remove method correctly removes an element from the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then calls the remove method with one of these elements as the argument.
     * @doc.testDescription The test verifies that the remove method returns true and the size of the ListAdapter decreases by one after the removal of the element.
     * @doc.preCondition The ListAdapter contains three elements.
     * @doc.postCondition The remove method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the remove method to return true and the size of the ListAdapter to decrease by one after the removal of the element.
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

    /**
     * Verifies that the remove method does not remove an element that is not present in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then calls the remove method with an element that is not present in the ListAdapter as the argument.
     * @doc.testDescription The test verifies that the remove method returns false and the size of the ListAdapter remains the same after attempting to remove an element that is not present.
     * @doc.preCondition The ListAdapter contains three elements.
     * @doc.postCondition The remove method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the remove method to return false and the size of the ListAdapter to remain the same after attempting to remove an element that is not present.
     */
    @Test
    public void remove_ShouldNotRemove_Element() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertFalse(list.remove("d"));
        Assert.assertEquals(3, list.size());
    }

    /**
     * Verifies the behavior of the `remove` method when the element to be removed is not present in the list.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter instance and adds a few elements to it. It then calls the `remove` method with an element that was not added to the list.
     * @doc.testDescription The test checks if the `remove` method correctly returns false when the element to be removed is not present in the list.
     * @doc.preCondition The ListAdapter instance contains a few elements which are different from the element to be removed.
     * @doc.postCondition The ListAdapter instance remains unchanged after the call to `remove`.
     * @doc.expectedResults The `remove` method should return false when the element to be removed is not present in the list.
     */
    @Test
    public void remove_ShouldReturnFalse_WhenElementNotPresent() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        Assert.assertFalse(list.remove("b"));
    }

    /**
     * Verifies that the removeAll method correctly removes multiple elements from the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then creates another ListAdapter and adds two of these elements to it. The test then calls the removeAll method on the first ListAdapter with the second ListAdapter as the argument.
     * @doc.testDescription The test verifies that the removeAll method returns true and the size of the first ListAdapter decreases by two after the removal of the elements.
     * @doc.preCondition The first ListAdapter contains three elements and the second ListAdapter contains two of these elements.
     * @doc.postCondition The removeAll method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the removeAll method to return true and the size of the first ListAdapter to decrease by two after the removal of the elements.
     */
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

    /**
     * Verifies that the removeAll method does not remove elements that are not present in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then creates another ListAdapter with two different elements. The test then calls the removeAll method on the first ListAdapter with the second ListAdapter as the argument.
     * @doc.testDescription The test verifies that the removeAll method returns false and the size of the first ListAdapter remains the same after attempting to remove elements that are not present.
     * @doc.preCondition The first ListAdapter contains three elements and the second ListAdapter contains two different elements.
     * @doc.postCondition The removeAll method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the removeAll method to return false and the size of the first ListAdapter to remain the same after attempting to remove elements that are not present.
     */
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
        Assert.assertEquals(3, list.size());
    }

    /**
     * Verifies that the removeAll method throws a NullPointerException when null is passed as an argument.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to call the removeAll method with null as the argument.
     * @doc.testDescription The test verifies that a NullPointerException is thrown when null is passed to the removeAll method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition A NullPointerException is thrown.
     * @doc.expectedResults The test expects a NullPointerException to be thrown.
     */
    @Test
    public void removeAll_ShouldThrow_NullPointerException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(NullPointerException.class, () -> list.removeAll(null));
    }

    /**
     * Verifies that the remove method throws an IndexOutOfBoundsException when an invalid index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to remove an element at an invalid index.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when an invalid index is provided to the remove method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void remove_ShouldThrow_IndexException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    }

    /**
     * Verifies that the remove method throws an IndexOutOfBoundsException when a negative index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to remove an element at a negative index.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when a negative index is provided to the remove method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void remove_ShouldThrow_IndexException_Negative() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    /*
     * ListAdapter Retain Family Tests
     */

    /**
     * Verifies that the retainAll method correctly retains multiple elements in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then creates another ListAdapter and adds two of these elements to it. The test then calls the retainAll method on the first ListAdapter with the second ListAdapter as the argument.
     * @doc.testDescription The test verifies that the retainAll method returns true and the size of the first ListAdapter decreases to two after the retainAll operation, retaining only the elements that are also present in the second ListAdapter.
     * @doc.preCondition The first ListAdapter contains three elements and the second ListAdapter contains two of these elements.
     * @doc.postCondition The retainAll method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the retainAll method to return true and the size of the first ListAdapter to decrease to two after the retainAll operation, retaining only the elements that are also present in the second ListAdapter.
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

    /**
     * Verifies that the retainAll method correctly retains all elements in the ListAdapter when all elements are present in the other collection.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then creates another ListAdapter and adds the same three elements to it. The test then calls the retainAll method on the first ListAdapter with the second ListAdapter as the argument.
     * @doc.testDescription The test verifies that the retainAll method returns false and the size of the first ListAdapter remains the same after the retainAll operation, as all elements in the first ListAdapter are present in the second ListAdapter.
     * @doc.preCondition The first ListAdapter contains three elements and the second ListAdapter contains the same three elements.
     * @doc.postCondition The retainAll method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the retainAll method to return false and the size of the first ListAdapter to remain the same after the retainAll operation, as all elements in the first ListAdapter are present in the second ListAdapter.
     */
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

    /**
     * Verifies that the retainAll method does not retain any elements in the ListAdapter when no elements are present in the other collection.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds three elements to it. It then creates another ListAdapter with two different elements. The test then calls the retainAll method on the first ListAdapter with the second ListAdapter as the argument.
     * @doc.testDescription The test verifies that the retainAll method returns true and the size of the first ListAdapter decreases to zero after the retainAll operation, as none of the elements in the first ListAdapter are present in the second ListAdapter.
     * @doc.preCondition The first ListAdapter contains three elements and the second ListAdapter contains two different elements.
     * @doc.postCondition The retainAll method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the retainAll method to return true and the size of the first ListAdapter to decrease to zero after the retainAll operation, as none of the elements in the first ListAdapter are present in the second ListAdapter.
     */
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

    /**
     * Verifies that the retainAll method throws a NullPointerException when null is passed as an argument.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to call the retainAll method with null as the argument.
     * @doc.testDescription The test verifies that a NullPointerException is thrown when null is passed to the retainAll method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition A NullPointerException is thrown.
     * @doc.expectedResults The test expects a NullPointerException to be thrown.
     */
    @Test
    public void retainAll_ShouldThrow_NullPointerException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(NullPointerException.class, () -> list.retainAll(null));
    }

    /*
     * ListAdapter clear Family Tests
     */

    /**
     * Verifies that the clear method correctly clears the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter, adds an element to it, then calls the clear method.
     * @doc.testDescription The test verifies that the clear method empties the ListAdapter, making its size 0.
     * @doc.preCondition The ListAdapter contains one element.
     * @doc.postCondition The ListAdapter is empty.
     * @doc.expectedResults The test expects the size of the ListAdapter to be 0 after the clear operation.
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

    /*
     * ListAdapter equals Family Tests
     */

    /**
     * Verifies that the equals method correctly identifies two identical ListAdapters as equal.
     *
     * @doc.testCaseDesign The test creates two new ListAdapters and adds the same elements to both. It then calls the equals method on the first ListAdapter with the second ListAdapter as the argument.
     * @doc.testDescription The test verifies that the equals method returns true when the two ListAdapters are identical.
     * @doc.preCondition The first and second ListAdapters contain the same elements.
     * @doc.postCondition The equals method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the equals method to return true when the two ListAdapters are identical.
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

    /**
     * Verifies that the equals method correctly identifies two different ListAdapters as not equal.
     *
     * @doc.testCaseDesign The test creates two new ListAdapters and adds different elements to each. It then calls the equals method on the first ListAdapter with the second ListAdapter as the argument.
     * @doc.testDescription The test verifies that the equals method returns false when the two ListAdapters are not identical.
     * @doc.preCondition The first and second ListAdapters contain different elements.
     * @doc.postCondition The equals method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the equals method to return false when the two ListAdapters are not identical.
     */
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

    /**
     * Verifies that the equals method correctly identifies a ListAdapter and null as not equal.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then calls the equals method with null as the argument.
     * @doc.testDescription The test verifies that the equals method returns false when null is passed as an argument.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition The equals method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the equals method to return false when null is passed as an argument.
     */
    @Test
    public void equals_ShouldReturn_False_Null() {
        ListAdapter list = new ListAdapter();
        Assert.assertNotEquals(null, list);
    }

    /**
     * Verifies that the equals method correctly identifies two ListAdapters of different sizes as not equal.
     *
     * @doc.testCaseDesign The test creates two new ListAdapters. The first one is populated with a set of elements, while the second one is left empty. It then calls the equals method on the first ListAdapter with the second ListAdapter as the argument.
     * @doc.testDescription The test verifies that the equals method returns false when the two ListAdapters have different sizes.
     * @doc.preCondition The first ListAdapter is populated with elements and the second ListAdapter is empty.
     * @doc.postCondition The equals method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the equals method to return false when the two ListAdapters have different sizes.
     */
    @Test
    public void equals_ShouldReturn_False_DifferentSize() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        ListAdapter other = new ListAdapter();
        other.add("a");
        Assert.assertNotEquals(list, other);
    }

    /*
     * ListAdapter hashCode Family Tests
     */

    /**
     * Verifies that the hashCode method consistently returns the same integer for the same ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then calls the hashCode method twice and compares the results.
     * @doc.testDescription The test verifies that the hashCode method returns the same integer each time it is called on the same ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The hashCode method of the ListAdapter has been verified for consistent functionality.
     * @doc.expectedResults The test expects the hashCode method to consistently return the same integer for the same ListAdapter.
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

    /**
     * Verifies that the hashCode method returns different integers for different ListAdapters.
     *
     * @doc.testCaseDesign The test creates two new ListAdapters and adds different elements to each. It then calls the hashCode method on both ListAdapters and compares the results.
     * @doc.testDescription The test verifies that the hashCode method returns different integers when called on different ListAdapters.
     * @doc.preCondition The first and second ListAdapters contain different elements.
     * @doc.postCondition The hashCode method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the hashCode method to return different integers when called on different ListAdapters.
     */
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

    /**
     * Verifies that the hashCode method consistently returns the same integer for two empty ListAdapters.
     *
     * @doc.testCaseDesign The test creates two new empty ListAdapters. It then calls the hashCode method on both ListAdapters and compares the results.
     * @doc.testDescription The test verifies that the hashCode method returns the same integer each time it is called on two empty ListAdapters.
     * @doc.preCondition The first and second ListAdapters are empty.
     * @doc.postCondition The hashCode method of the ListAdapter has been verified for consistent functionality.
     * @doc.expectedResults The test expects the hashCode method to consistently return the same integer for two empty ListAdapters.
     */
    @Test
    public void hashCode_ShouldReturn_Same_Empty() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter();
        Assert.assertEquals(list.hashCode(), other.hashCode());
    }

    /**
     * Verifies that the hashCode method returns different integers for two empty ListAdapters that were manipulated differently.
     *
     * @doc.testCaseDesign The test creates two new empty ListAdapters. It then performs different operations on each (like adding and removing elements). Even though both ListAdapters are empty at the time of the test, they have undergone different operations. The test then calls the hashCode method on both ListAdapters and compares the results.
     * @doc.testDescription The test verifies that the hashCode method returns different integers each time it is called on two empty ListAdapters that have undergone different operations.
     * @doc.preCondition The first and second ListAdapters are empty but have undergone different operations.
     * @doc.postCondition The hashCode method of the ListAdapter has been verified for consistent functionality.
     * @doc.expectedResults The test expects the hashCode method to return different integers for two empty ListAdapters that have undergone different operations.
     */
    @Test
    public void hashCode_ShouldReturn_Different_Empty() {
        ListAdapter list = new ListAdapter();
        ListAdapter other = new ListAdapter();
        other.add("a");
        Assert.assertNotEquals(list.hashCode(), other.hashCode());
    }

    /**
     * Verifies that the hashCode method consistently returns the same integer for the same ListAdapter, even when it contains null elements.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds a null element to it. It then calls the hashCode method twice and compares the results.
     * @doc.testDescription The test verifies that the hashCode method returns the same integer each time it is called on the same ListAdapter, even when it contains null elements.
     * @doc.preCondition The ListAdapter has been initialized and a null element has been added to it.
     * @doc.postCondition The hashCode method of the ListAdapter has been verified for consistent functionality.
     * @doc.expectedResults The test expects the hashCode method to consistently return the same integer for the same ListAdapter, even when it contains null elements.
     */
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

    /*
     * ListAdapter toString Family Tests
     */

    /**
     * Verifies that the toString method correctly converts the ListAdapter to a string representation.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then calls the toString method and checks the returned string.
     * @doc.testDescription The test verifies that the toString method returns a string that accurately represents the state of the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The toString method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the toString method to return a string that accurately represents the state of the ListAdapter.
     */
    @Test
    public void toString_ShouldReturn_String() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals("[a, b, c]", list.toString());
    }

    /**
     * Verifies that the toString method correctly converts an empty ListAdapter to a string representation.
     *
     * @doc.testCaseDesign The test creates a new empty ListAdapter. It then calls the toString method and checks the returned string.
     * @doc.testDescription The test verifies that the toString method returns a string that accurately represents the state of an empty ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized but no elements have been added to it.
     * @doc.postCondition The toString method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the toString method to return a string that accurately represents the state of an empty ListAdapter.
     */
    @Test
    public void toString_ShouldReturn_String_Empty() {
        ListAdapter list = new ListAdapter();
        Assert.assertEquals("[]", list.toString());
    }


    /*
     * ListAdapter get Family Tests
     */

    /**
     * Verifies that the get method correctly returns the element at the specified position in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds an element to it. It then calls the get method with the index of the added element as the argument.
     * @doc.testDescription The test verifies that the get method returns the element that was added to the ListAdapter at the specified index.
     * @doc.preCondition The ListAdapter has been initialized and an element has been added to it.
     * @doc.postCondition The get method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the get method to return the element that was added to the ListAdapter at the specified index.
     */
    @Test
    public void get_ShouldReturn_Element() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals("b", list.get(1));
    }

    /**
     * Verifies that the get method throws an IndexOutOfBoundsException when an invalid index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to retrieve an element at an invalid index.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when an invalid index is provided to the get method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void get_ShouldThrow_IndexException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    /**
     * Verifies that the get method correctly returns the element at the specified position in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds a few elements to it. It then calls the get method with the index of an added element as the argument.
     * @doc.testDescription The test verifies that the get method returns the correct element that was added to the ListAdapter at the specified index.
     * @doc.preCondition The ListAdapter has been initialized and a few elements have been added to it.
     * @doc.postCondition The get method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the get method to return the correct element that was added to the ListAdapter at the specified index.
     */
    @Test
    public void get_ShouldReturn_CorrectElement() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add(1, "d");
        Assert.assertEquals("d", list.get(1));
    }

    /**
     * Verifies that the get method throws an IndexOutOfBoundsException when a negative index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to retrieve an element at a negative index.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when a negative index is provided to the get method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void get_ShouldThrow_IndexException_Negative() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    /*
     * ListAdapter set Family Tests
     */

    /**
     * Verifies that the set method correctly sets an element at the specified position in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds an element to it. It then calls the set method with the index of the added element and a new element as the arguments.
     * @doc.testDescription The test verifies that the set method replaces the element at the specified index with the new element.
     * @doc.preCondition The ListAdapter has been initialized and an element has been added to it.
     * @doc.postCondition The set method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the set method to replace the element at the specified index with the new element.
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

    /**
     * Verifies that the set method throws an IndexOutOfBoundsException when an invalid index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to set an element at an invalid index.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when an invalid index is provided to the set method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void set_ShouldThrow_IndexException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, "element"));
    }

    /**
     * Verifies the behavior of the `set` method when a null element is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter instance and adds a few elements to it. It then calls the `set` method with a null element.
     * @doc.testDescription The test checks if the `set` method correctly allows the insertion of a null element into the list.
     * @doc.preCondition The ListAdapter instance contains a few elements.
     * @doc.postCondition The ListAdapter instance has a null element at the specified index after the call to `set`.
     * @doc.expectedResults The `set` method should allow the insertion of a null element into the list.
     */
    @Test
    public void set_ShouldAllow_NullElement() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        Assert.assertEquals("a", list.set(0, null));
        Assert.assertNull(list.get(0));
    }

    /**
     * Verifies that the set method throws an IndexOutOfBoundsException when a negative index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to set an element at a negative index.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when a negative index is provided to the set method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void set_ShouldThrow_IndexException_Negative() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "element"));
    }

    /**
     * Verifies that the set method throws an IndexOutOfBoundsException when trying to set an element in an empty ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new, empty ListAdapter and then attempts to set an element at index 0.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when trying to set an element at any index in an empty ListAdapter.
     * @doc.preCondition The ListAdapter has just been created and is empty.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void set_ShouldThrow_IndexException_Empty() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "element"));
    }


    /*
     * ListAdapter indexOf Family Tests
     */

    /**
     * Verifies that the indexOf method correctly returns the index of an element in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then calls the indexOf method with one of these elements as the argument.
     * @doc.testDescription The test verifies that the indexOf method returns the correct index of the element in the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The indexOf method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the indexOf method to return the correct index of the element in the ListAdapter.
     */
    @Test
    public void indexOf_ShouldReturn_Index() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals(1, list.indexOf("b"));
    }

    /**
     * Verifies that the indexOf method correctly returns -1 when an element is not present in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then calls the indexOf method with an element that is not present in the ListAdapter as the argument.
     * @doc.testDescription The test verifies that the indexOf method returns -1 when the element is not present in the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The indexOf method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the indexOf method to return -1 when the element is not present in the ListAdapter.
     */
    @Test
    public void indexOf_ShouldReturn_MinusOne() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals(-1, list.indexOf("d"));
    }

    /**
     * Verifies the behavior of the `indexOf` method when the element is not present in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter instance and adds a few elements to it. It then calls the `indexOf` method with an element that is not present in the ListAdapter.
     * @doc.testDescription The test checks if the `indexOf` method correctly returns -1 when the element is not present in the ListAdapter.
     * @doc.preCondition The ListAdapter instance is initialized and populated with elements.
     * @doc.postCondition The ListAdapter instance remains unchanged after the call to `indexOf`.
     * @doc.expectedResults The `indexOf` method should return -1 when the element is not present in the ListAdapter.
     */
    @Test
    public void indexOf_ShouldReturnMinusOne_WhenElementNotPresent() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        Assert.assertEquals(-1, list.indexOf("b"));
    }

    /*
     * ListAdapter lastIndexOf Family Tests
     */

    /**
     * Verifies that the lastIndexOf method correctly returns the last occurrence index of an element in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it, including duplicate elements. It then calls the lastIndexOf method with one of these elements as the argument.
     * @doc.testDescription The test verifies that the lastIndexOf method returns the correct index of the last occurrence of the element in the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements, including duplicate elements.
     * @doc.postCondition The lastIndexOf method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the lastIndexOf method to return the correct index of the last occurrence of the element in the ListAdapter.
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

    /**
     * Verifies that the lastIndexOf method correctly returns -1 when an element is not present in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then calls the lastIndexOf method with an element that is not present in the ListAdapter as the argument.
     * @doc.testDescription The test verifies that the lastIndexOf method returns -1 when the element is not present in the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The lastIndexOf method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the lastIndexOf method to return -1 when the element is not present in the ListAdapter.
     */
    @Test
    public void lastIndexOf_ShouldReturn_MinusOne() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals(-1, list.lastIndexOf("d"));
    }

    /**
     * Verifies the behavior of the `lastIndexOf` method when the element is not present in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter instance and adds a few elements to it. It then calls the `lastIndexOf` method with an element that is not present in the ListAdapter.
     * @doc.testDescription The test checks if the `lastIndexOf` method correctly returns -1 when the element is not present in the ListAdapter.
     * @doc.preCondition The ListAdapter instance is initialized and populated with elements.
     * @doc.postCondition The ListAdapter instance remains unchanged after the call to `lastIndexOf`.
     * @doc.expectedResults The `lastIndexOf` method should return -1 when the element is not present in the ListAdapter.
     */
    @Test
    public void lastIndexOf_ShouldReturnMinusOne_WhenElementNotPresent() {
        ListAdapter list = new ListAdapter();
        list.add(1);
        list.add(2);
        Assert.assertEquals(-1, list.lastIndexOf("b"));
    }


    /*
     * ListAdapter ListIterator Family Tests
     */

    /**
     * Verifies that the listIterator method throws an IndexOutOfBoundsException when an invalid index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to call the listIterator method with an invalid index.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when an invalid index is provided to the listIterator method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void ListIterator_ShouldThrow_IndexException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.listIterator(5));
    }

    /**
     * Verifies that the listIterator method throws an IndexOutOfBoundsException when a negative index is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to call the listIterator method with a negative index.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when a negative index is provided to the listIterator method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void ListIterator_ShouldThrow_IndexException_Negative() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.listIterator(-1));
    }

    /**
     * Verifies the behavior of the `listIterator` method when the ListAdapter is empty.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter instance without adding any elements to it. It then retrieves a list iterator from the ListAdapter.
     * @doc.testDescription The test checks if the `hasNext` method of the retrieved list iterator correctly returns false, as there are no elements in the ListAdapter.
     * @doc.preCondition The ListAdapter instance is initialized but not populated with any elements.
     * @doc.postCondition The ListAdapter instance remains unchanged after the call to `listIterator`.
     * @doc.expectedResults The `hasNext` method of the list iterator should return false when the ListAdapter is empty.
     */
    @Test
    public void listIterator_ShouldNotHaveNext_WhenListIsEmpty() {
        ListAdapter list = new ListAdapter();
        HListIterator iterator = list.listIterator();
        Assert.assertFalse(iterator.hasNext());
    }

    /*
     * ListAdapter subList Family Tests
     */

    /**
     * Verifies that the subList method correctly returns a sublist from the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then calls the subList method with valid fromIndex and toIndex arguments.
     * @doc.testDescription The test verifies that the subList method returns a ListAdapter that contains the elements in the specified range from the original ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The subList method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the subList method to return a ListAdapter that contains the elements in the specified range from the original ListAdapter.
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

    /**
     * Verifies that the subList method correctly returns an empty sublist when the fromIndex and toIndex are the same.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then calls the subList method with the same fromIndex and toIndex.
     * @doc.testDescription The test verifies that the subList method returns an empty ListAdapter when the fromIndex and toIndex are the same.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The subList method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the subList method to return an empty ListAdapter when the fromIndex and toIndex are the same.
     */
    @Test
    public void subList_ShouldReturn_EmptyList() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        ListAdapter subList = (ListAdapter) list.subList(1, 1);
        Assert.assertEquals(0, subList.size());
    }

    /**
     * Verifies the behavior of the `subList` method when the fromIndex and toIndex are the same.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter instance and adds a few elements to it. It then calls the `subList` method with the same fromIndex and toIndex.
     * @doc.testDescription The test checks if the `subList` method correctly returns an empty ListAdapter when the fromIndex and toIndex are the same.
     * @doc.preCondition The ListAdapter instance is initialized and populated with elements.
     * @doc.postCondition The ListAdapter instance remains unchanged after the call to `subList`.
     * @doc.expectedResults The `subList` method should return an empty ListAdapter when the fromIndex and toIndex are the same.
     */
    @Test
    public void subList_ShouldReturnEmpty_WhenFromIndexEqualToToIndex() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        HList subList = list.subList(0, 0);
        Assert.assertTrue(subList.isEmpty());
    }

    /**
     * Verifies that the subList method correctly returns the full ListAdapter when the fromIndex is 0 and the toIndex is the size of the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then calls the subList method with fromIndex 0 and toIndex equal to the size of the ListAdapter.
     * @doc.testDescription The test verifies that the subList method returns a ListAdapter that contains all the elements from the original ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The subList method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the subList method to return a ListAdapter that contains all the elements from the original ListAdapter.
     */
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

    /**
     * Verifies that the clear method correctly clears the original ListAdapter when called on a sublist.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then creates a sublist from the ListAdapter and calls the clear method on the sublist.
     * @doc.testDescription The test verifies that the clear method empties the original ListAdapter when called on a sublist.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements, and a sublist has been created from it.
     * @doc.postCondition The clear method of the sublist has been verified for its impact on the original ListAdapter.
     * @doc.expectedResults The test expects the original ListAdapter to be empty after the clear operation on the sublist.
     */
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

    /**
     * Verifies that the add method correctly adds an element to the original ListAdapter when called on a sublist.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then creates a sublist from the ListAdapter and calls the add method on the sublist.
     * @doc.testDescription The test verifies that the add method increases the size of the original ListAdapter and the added element can be retrieved from the original ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements, and a sublist has been created from it.
     * @doc.postCondition The add method of the sublist has been verified for its impact on the original ListAdapter.
     * @doc.expectedResults The test expects the size of the original ListAdapter to increase and the added element to be retrievable from the original ListAdapter.
     */
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

    /**
     * Verifies that the subList method throws an IndexOutOfBoundsException when invalid fromIndex and toIndex are provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to call the subList method with invalid fromIndex and toIndex.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when invalid fromIndex and toIndex are provided to the subList method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void subList_ShouldThrow_IndexException() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.subList(1, 5));
    }

    /**
     * Verifies that the subList method throws an IndexOutOfBoundsException when a negative fromIndex is provided.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to call the subList method with a negative fromIndex.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when a negative fromIndex is provided to the subList method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void subList_ShouldThrow_IndexException_Negative() {
        ListAdapter list = new ListAdapter();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.subList(-1, 5));
    }

    /**
     * Verifies that the subList method throws an IndexOutOfBoundsException when fromIndex is greater than toIndex.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then attempts to call the subList method with fromIndex greater than toIndex.
     * @doc.testDescription The test verifies that an IndexOutOfBoundsException is thrown when fromIndex is greater than toIndex in the subList method.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition An IndexOutOfBoundsException is thrown.
     * @doc.expectedResults The test expects an IndexOutOfBoundsException to be thrown.
     */
    @Test
    public void subList_ShouldThrow_IndexException_FromIndex() {
        ListAdapter list = new ListAdapter();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.subList(2, 1));
    }


    /*
     * ListAdapter iterator Family Tests
     */


    /**
     * Verifies that the listIterator method correctly identifies the presence of a next item in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds an element to it. It then creates a listIterator and calls the hasNext method.
     * @doc.testDescription The test verifies that the hasNext method of the listIterator returns true when there is a next item in the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and an element has been added to it.
     * @doc.postCondition The hasNext method of the listIterator has been verified for correct functionality.
     * @doc.expectedResults The test expects the hasNext method to return true when there is a next item in the ListAdapter.
     */
    @Test
    public void ListIterator_ShouldHave_NextItem() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("element", iterator.next());
    }

    /**
     * Verifies that the listIterator's next method throws a NoSuchElementException when there are no more elements in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then creates a listIterator. It then calls the next method on the listIterator.
     * @doc.testDescription The test verifies that a NoSuchElementException is thrown when the next method is called on a listIterator that has no more elements to return.
     * @doc.preCondition The ListAdapter has just been created and is empty.
     * @doc.postCondition A NoSuchElementException is thrown.
     * @doc.expectedResults The test expects a NoSuchElementException to be thrown.
     */
    @Test
    public void ListIterator_ShouldThrow_NoSuchElementException() {
        ListAdapter list = new ListAdapter();
        HListIterator iterator = list.listIterator();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertThrows(NoSuchElementException.class, iterator::next);
    }

    /**
     * Verifies that the listIterator method correctly identifies the presence of a previous item in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds an element to it. It then creates a listIterator, calls the next method, and then calls the hasPrevious method.
     * @doc.testDescription The test verifies that the hasPrevious method of the listIterator returns true when there is a previous item in the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and an element has been added to it.
     * @doc.postCondition The hasPrevious method of the listIterator has been verified for correct functionality.
     * @doc.expectedResults The test expects the hasPrevious method to return true when there is a previous item in the ListAdapter.
     */
    @Test
    public void ListIterator_ShouldHave_PreviousItem() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        iterator.next();
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals("element", iterator.previous());
    }

    /**
     * Verifies that the listIterator's previous method throws a NoSuchElementException when there are no more elements in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then creates a listIterator. It then calls the previous method on the listIterator.
     * @doc.testDescription The test verifies that a NoSuchElementException is thrown when the previous method is called on a listIterator that has no more elements to return.
     * @doc.preCondition The ListAdapter has just been created and is empty.
     * @doc.postCondition A NoSuchElementException is thrown.
     * @doc.expectedResults The test expects a NoSuchElementException to be thrown.
     */
    @Test
    public void ListIterator_ShouldThrow_NoSuchElementException_Previous() {
        ListAdapter list = new ListAdapter();
        HListIterator iterator = list.listIterator();
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertThrows(NoSuchElementException.class, iterator::previous);
    }

    /**
     * Verifies that the listIterator's nextIndex method correctly returns the index of the next element in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then creates a listIterator and calls the nextIndex method.
     * @doc.testDescription The test verifies that the nextIndex method of the listIterator returns the correct index of the next element in the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The nextIndex method of the listIterator has been verified for correct functionality.
     * @doc.expectedResults The test expects the nextIndex method to return the correct index of the next element in the ListAdapter.
     */
    @Test
    public void ListIterator_ShouldHave_NextIndex() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        Assert.assertEquals(0, iterator.nextIndex());
        iterator.next();
        Assert.assertEquals(1, iterator.nextIndex());
    }

    /**
     * Verifies that the listIterator's previousIndex method correctly returns the index of the previous element in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then creates a listIterator, calls the next method, and then calls the previousIndex method.
     * @doc.testDescription The test verifies that the previousIndex method of the listIterator returns the correct index of the previous element in the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The previousIndex method of the listIterator has been verified for correct functionality.
     * @doc.expectedResults The test expects the previousIndex method to return the correct index of the previous element in the ListAdapter.
     */
    @Test
    public void ListIterator_ShouldHave_PreviousIndex() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        Assert.assertEquals(-1, iterator.previousIndex());
        iterator.next();
        Assert.assertEquals(0, iterator.previousIndex());
    }

    /**
     * Verifies that the listIterator's remove method correctly removes an element from the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds an element to it. It then creates a listIterator, calls the next method to move the cursor, and then calls the remove method.
     * @doc.testDescription The test verifies that the remove method of the listIterator removes the last element returned by the next method from the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The remove method of the listIterator has been verified for correct functionality.
     * @doc.expectedResults The test expects the remove method to correctly remove the last element returned by the next method from the ListAdapter.
     */
    @Test
    public void ListIterator_ShouldRemove_Item() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        iterator.next();
        iterator.remove();
        Assert.assertEquals(0, list.size());
    }

    /**
     * Verifies that the listIterator's set method correctly replaces an element in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds an element to it. It then creates a listIterator, calls the next method to move the cursor, and then calls the set method.
     * @doc.testDescription The test verifies that the set method of the listIterator replaces the last element returned by the next method in the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The set method of the listIterator has been verified for correct functionality.
     * @doc.expectedResults The test expects the set method to correctly replace the last element returned by the next method in the ListAdapter.
     */
    @Test
    public void ListIterator_ShouldSet_Item() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        iterator.next();
        iterator.set("newElement");
        Assert.assertEquals("newElement", list.get(0));
    }

    /**
     * Verifies that the listIterator's add method correctly adds an element to the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds an element to it. It then creates a listIterator, calls the next method to move the cursor, and then calls the add method.
     * @doc.testDescription The test verifies that the add method of the listIterator adds a new element to the ListAdapter at the current cursor position.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The add method of the listIterator has been verified for correct functionality.
     * @doc.expectedResults The test expects the add method to correctly add a new element to the ListAdapter at the current cursor position.
     */
    @Test
    public void ListIterator_ShouldAdd_Item() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        HListIterator iterator = list.listIterator();
        iterator.next();
        iterator.add("newElement");
        Assert.assertEquals("element", list.get(0));
    }

    /**
     * Verifies that the listIterator's remove method throws an IllegalStateException when it is called before the next or previous method.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds an element to it. It then creates a listIterator and directly calls the remove method without calling next or previous.
     * @doc.testDescription The test verifies that an IllegalStateException is thrown when the remove method is called on a listIterator without a prior call to next or previous.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition An IllegalStateException is thrown.
     * @doc.expectedResults The test expects an IllegalStateException to be thrown.
     */
    @Test
    public void ListIterator_ShouldThrow_IllegalStateException_Remove() {
        ListAdapter list = new ListAdapter();
        HListIterator iterator = list.listIterator();
        Assert.assertThrows(myAdapter.IllegalStateException.class, iterator::remove);
    }

    /**
     * Verifies that the listIterator's set method throws an IllegalStateException when it is called before the next or previous method.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then creates a listIterator. It directly calls the set method without calling next or previous.
     * @doc.testDescription The test verifies that an IllegalStateException is thrown when the set method is called on a listIterator without a prior call to next or previous.
     * @doc.preCondition The ListAdapter has been initialized.
     * @doc.postCondition An IllegalStateException is thrown.
     * @doc.expectedResults The test expects an IllegalStateException to be thrown.
     */
    @Test
    public void ListIterator_ShouldThrow_IllegalStateException_Set() {
        ListAdapter list = new ListAdapter();
        HListIterator iterator = list.listIterator();
        Assert.assertThrows(myAdapter.IllegalStateException.class, () -> iterator.set("element"));
    }

    /**
     * Verifies that the listIterator's add method correctly adds an element to the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and then creates a listIterator. It directly calls the add method without calling next or previous.
     * @doc.testDescription The test verifies that an element is correctly added to the ListAdapter when the add method is called on a listIterator without a prior call to next or previous.
     * @doc.preCondition The ListAdapter has just been created.
     * @doc.postCondition The add method of the listIterator has been verified for correct functionality.
     * @doc.expectedResults The test expects the add method to correctly add an element to the ListAdapter.
     */
    @Test
    public void ListIteratorAdd_ShouldAdd_Element() {
        ListAdapter list = new ListAdapter();
        HListIterator iterator = list.listIterator();
        iterator.add("element");
        Assert.assertEquals(1, list.size());
    }

    /**
     * Verifies that the listIterator method correctly returns a listIterator positioned at the specified index in the ListAdapter.
     *
     * @doc.testCaseDesign The test creates a new ListAdapter and adds some elements to it. It then calls the listIterator method with a valid index as the argument.
     * @doc.testDescription The test verifies that the listIterator method returns a listIterator that is positioned at the specified index in the ListAdapter.
     * @doc.preCondition The ListAdapter has been initialized and populated with elements.
     * @doc.postCondition The listIterator method of the ListAdapter has been verified for correct functionality.
     * @doc.expectedResults The test expects the listIterator method to return a listIterator that is positioned at the specified index in the ListAdapter.
     */
    @Test
    public void ListIterator_ShouldReturn_IteratorAtIndex() {
        ListAdapter list = new ListAdapter();
        list.add("element");
        list.add("element2");
        HListIterator iterator = list.listIterator(1);
        Assert.assertEquals("element2", iterator.next());
    }
}