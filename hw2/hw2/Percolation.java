package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int N; // N-by-N grid
    boolean[][] sites; // sites[row][col] = true means site in (row, col) is opened
    WeightedQuickUnionUF openSites; // uses index to record the sites
    int numOpenSites = 0;

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

        this.openSites = new WeightedQuickUnionUF(N * N);
    }

    private void checkException(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("Row and Column must be in [0, N).");
        }
    }


    /**Every site in sites[][] has a unique numerical index.
     * The method would get the site's index by its row and column.
     * */
    public int getIndex(int row, int col) {
        return row + col * this.N;
    }


    /**The method opens the site (row, col) if it is not open already
     * It also connects the site with its opened neighbors.
     */
    public void open(int row, int col) {
        checkException(row, col);

        if (!isOpen(row, col)) {
            sites[row][col] = true;
            numOpenSites ++;
        }

        // connect the site with its opened neighbors
        int index = getIndex(row, col);
        if (col + 1 < N && isOpen(row, col + 1)) {
            openSites.union(index, getIndex(row, col + 1));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            openSites.union(index, getIndex(row, col - 1));
        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            openSites.union(index, getIndex(row - 1, col));
        }
        if (row + 1 < N && isOpen(row + 1, col)) {
            openSites.union(index, getIndex(row + 1, col));
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

        if (!isOpen(row, col)) {
            return false;
        }

        if (col == N - 1) {
            return true;
        }

        // check all the top sites
        for (int i = 0; i < N; i++) {
            if (openSites.connected(getIndex(row, col), getIndex(i, N - 1)) && isOpen(i, N -1)) {
                return true;
            }
        }
        return false;
    }


    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < N; i++) {
            if (isFull(i, 0)) {
                return true;
            }
        }
        return false;
    }

}
