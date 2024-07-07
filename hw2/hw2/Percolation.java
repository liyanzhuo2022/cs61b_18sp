package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Percolation {
    private int N; // N-by-N grid
    private boolean[][] sites; // sites[row][col] = true means site in (row, col) is opened
    private int numOpenSites = 0; // number of opened sites
    private int virtualTop;
    private int virtualBottom;
    private boolean isPercolated = false;

    /**Use index to record the sites. This object contains virtual top and virtual
     * bottom sites, it is used to track percolation.
     * */
    private WeightedQuickUnionUF sitesTracker;
    /**This object is a copy of SitesTracker for solving backwash.
     * However, it doesn't contain the virtual bottom site for anti-backwash.
     * It is called when checking a site is full or not.*/
    private WeightedQuickUnionUF fullTracker;


    /**The constructor creates N-by-N grid, with all sites initially blocked.
     * It initializes all elements in sites[][] into false.
     * */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0.");
        }

        this.N = N;

        this.sites = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.sites[i][j] = false;
            }
        }

        this.sitesTracker = new WeightedQuickUnionUF(N * N + 2);
        this.fullTracker = new WeightedQuickUnionUF(N * N + 1);
        this.virtualTop = N * N;
        this.virtualBottom = N * N + 1;
    }

    private void checkException(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("Row and Column must be in [0, N).");
        }
    }


    /**Every site in sites[][] has a unique numerical index.
     * The method would get the site's index by its row and column.
     * */
    private int getIndex(int row, int col) {
        return row * N + col;
    }


    /**The method opens the site (row, col) if it is not open already
     * It also connects the site with its opened neighbors.
     */
    public void open(int row, int col) {
        checkException(row, col);

        if (!isOpen(row, col)) {
            sites[row][col] = true;
            numOpenSites++;

            // connect the site with its opened neighbors
            int index = getIndex(row, col);
            if (col + 1 < N && isOpen(row, col + 1)) {
                sitesTracker.union(index, getIndex(row, col + 1));
                fullTracker.union(index, getIndex(row, col + 1));
            }
            if (col - 1 >= 0 && isOpen(row, col - 1)) {
                sitesTracker.union(index, getIndex(row, col - 1));
                fullTracker.union(index, getIndex(row, col - 1));
            }
            if (row - 1 >= 0 && isOpen(row - 1, col)) {
                sitesTracker.union(index, getIndex(row - 1, col));
                fullTracker.union(index, getIndex(row - 1, col));
            }
            if (row + 1 < N && isOpen(row + 1, col)) {
                sitesTracker.union(index, getIndex(row + 1, col));
                fullTracker.union(index, getIndex(row + 1, col));
            }

            if (row == 0) {
                sitesTracker.union(index, virtualTop);
                fullTracker.union(index, virtualTop);
            }
            if (row == N - 1) {
                sitesTracker.union(index, virtualBottom);
            }

            if (sitesTracker.connected(virtualTop, virtualBottom)) {
                isPercolated = true;
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkException(row, col);
        return sites[row][col];
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkException(row, col);
        int index = getIndex(row, col);
        return (isOpen(row, col) && fullTracker.connected(index, virtualTop));
    }


    // Does the system percolate?
    // If any bottom site is full, the grid is percolated.
    public boolean percolates() {
        return isPercolated;
    }

    public static void main(String[] args) {
        Percolation perc5 = new Percolation(5);
    }

}
