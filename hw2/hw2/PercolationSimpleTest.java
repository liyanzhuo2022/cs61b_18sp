package hw2;

import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationSimpleTest {
    @Test
    public void getIndexTest() {
        PercolationSimple perc5 = new PercolationSimple(5);
        assertEquals(perc5.getIndex(1, 1), 6);
        assertEquals(perc5.getIndex(0, 0), 0);
        assertEquals(perc5.getIndex(4, 4), 24);
        assertEquals(perc5.getIndex(3, 2), 17);
        assertEquals(perc5.getIndex(2, 3), 13);
    }

    @Test
    public void isFullTest() {
        PercolationSimple perc2 = new PercolationSimple(2);
        perc2.open(1, 1);
        assertTrue(perc2.isFull(1, 1));
        assertFalse(perc2.isFull(0, 1));
        assertFalse(perc2.isFull(0, 0));
        assertFalse(perc2.percolates());
        perc2.open(0, 0);
        assertFalse(perc2.isFull(0, 0));

        PercolationSimple perc5 = new PercolationSimple(5);
        assertFalse(perc5.isFull(1, 1));
        perc5.open(3, 2);
        perc5.open(4, 2);
        assertTrue(perc5.isFull(3, 2));
        perc5.open(2, 3);
        perc5.open(2, 4);
        assertFalse(perc5.isFull(2, 3));
        perc5.open(2, 2);
        assertTrue(perc5.isFull(2, 4));
        perc5.open(1, 2);
        perc5.open(0, 2);
        assertFalse(perc5.isFull(0, 4));
    }

    @Test
    public void percolatesTest() {
        PercolationSimple perc2 = new PercolationSimple(2);
        perc2.open(1, 1);
        perc2.open(0, 0);
        assertFalse(perc2.percolates());
        perc2.open(0, 1);
        assertTrue(perc2.percolates());

        PercolationSimple perc5 = new PercolationSimple(5);
        perc5.open(4, 2);
        perc5.open(3, 2);
        perc5.open(2, 2);
        perc5.open(1, 2);
        perc5.open(0, 0);
        assertFalse(perc5.percolates());
        perc5.open(0, 2);
        assertTrue(perc5.percolates());
    }

}
