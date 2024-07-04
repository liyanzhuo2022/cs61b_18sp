package hw2;

import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationTest {
    @Test
    public void getIndexTest() {
        Percolation perc5 = new Percolation(5);
        assertEquals(perc5.getIndex(1, 1), 6);
        assertEquals(perc5.getIndex(0, 0), 0);
        assertEquals(perc5.getIndex(4, 4), 24);
        assertEquals(perc5.getIndex(3, 2), 13);
        assertEquals(perc5.getIndex(2, 3), 17);
    }

    @Test
    public void isFullTest() {
        Percolation perc2 = new Percolation(2);
        perc2.open(1, 1);
        assertTrue(perc2.isFull(1, 1));
        assertFalse(perc2.isFull(0, 1));
        assertFalse(perc2.isFull(0, 0));
        assertFalse(perc2.percolates());
        perc2.open(0, 0);
        assertFalse(perc2.isFull(0, 0));

        Percolation perc5 = new Percolation(5);
        perc5.open(3, 2);
        perc5.open(3, 3);
        perc5.open(2, 3);
        perc5.open(2, 4);
        assertFalse(perc5.isFull(1, 1));
        assertTrue(perc5.isFull(3, 2));
        perc5.open(2, 1);
        assertFalse(perc5.isFull(2, 1));
    }

    @Test
    public void percolatesTest() {
        Percolation perc2 = new Percolation(2);
        perc2.open(1, 1);
        perc2.open(0, 0);
        assertFalse(perc2.percolates());
        perc2.open(0, 1);
        assertTrue(perc2.percolates());

        Percolation perc5 = new Percolation(5);
        perc5.open(2, 4);
        perc5.open(3, 3);
        perc5.open(2, 2);
        perc5.open(2, 0);
        perc5.open(2, 3);
        assertFalse(perc5.percolates());
        perc5.open(2, 1);
        assertTrue(perc5.percolates());
    }

}
