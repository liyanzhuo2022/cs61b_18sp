import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testPrevBehind() {
        System.out.println("testing get prev and behind");
        ArrayDeque<Integer> L = new ArrayDeque<>();
        assertEquals(0, L.size());
        System.out.println("tests get prev and behind passed");
    }

    @Test
    public void testAddFirst() {
        System.out.println("testing add first");
        ArrayDeque<Integer> L = new ArrayDeque<>();
        assertTrue(L.isEmpty());
        L.addFirst(3);
        L.addFirst(2);
        L.addFirst(1);
        assertEquals(3, L.size());
        L.printDeque(); // 1 2 3
        System.out.println("tests add first passed");
    }

    @Test
    public void testAddLast() {
        System.out.println("testing add last");
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(3);
        L.addLast(2);
        L.addLast(1);
        assertEquals(3, L.size());
        L.printDeque(); // 3 2 1
        assertEquals(Integer.valueOf(3), L.get(0));
        System.out.println("tests add last passed");
    }

    @Test
    public void testRemove() {
        System.out.println("testing remove");
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(3);
        L.addLast(2);
        L.addLast(1);
        L.printDeque(); // 3 2 1
        assertEquals(Integer.valueOf(1), L.get(2));
        L.removeFirst();
        L.printDeque(); // 2 1
        assertEquals(Integer.valueOf(1), L.get(1));
        L.removeLast();
        L.printDeque(); // 2
        assertEquals(1, L.size());
        System.out.println("tests remove passed");
    }

}
