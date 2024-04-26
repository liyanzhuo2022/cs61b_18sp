
/** Performs some basic linked list tests. */

import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListDequeTest {
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual
                    + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual
                    + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed. 
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct, 
      * finally printing the results. 
      *
      * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines below "
                + " (and delete this print statement).");

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");
        
        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true,
        // and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Make sure to uncomment the lines below "
                + "(and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty 
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty 
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty 
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    /** add my tests below*/
    @Test
    public void testAdd() {
        System.out.println("Junit Tests: testing add first, "
                + "add last, and get, size, is empty");
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        assertEquals(0, L.size());
        assertTrue(L.isEmpty());
        L.addFirst(1);
        assertEquals(1, L.size());
        L.addLast(2);
        assertEquals(2, L.size());
        assertEquals(Integer.valueOf(1), L.get(0));
        assertEquals(Integer.valueOf(1), L.getRecursive(0));
        assertEquals(Integer.valueOf(2), L.get(1));
        assertEquals(Integer.valueOf(2), L.getRecursive(1));
    }

    @Test
    public void testRemove() {
        System.out.println("Junit Tests: testing remove first, "
                + "add last, and get, size, is empty");
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addLast(1);
        L.addLast(2);
        L.addLast(3);
        L.printDeque();
        assertFalse(L.isEmpty());
        assertEquals(3, L.size());
        L.removeFirst();
        L.printDeque();
        assertEquals(Integer.valueOf(2), L.get(0));
        assertEquals(Integer.valueOf(2), L.getRecursive(0));
        L.removeLast();
        L.printDeque();
        assertEquals(1, L.size());

    }



    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
    }


}
