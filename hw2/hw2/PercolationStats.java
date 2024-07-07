package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    //create an array to store the fraction in each experiment, the size is T
    private double[] fractions;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0.");
        }


        this.fractions = new double[T];
        this.T = T;

        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);

            while (!p.percolates()) {
                Position site = getRandomSite(p, N);
                p.open(site.row, site.col);
            }

            fractions[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    // choose a random unopened site in Percolation p
    private Position getRandomSite(Percolation p, int N) {
        int row, col;

        // damn I forgot about the do-while loop!
        do {
            row = StdRandom.uniform(N);
            col = StdRandom.uniform(N);
        } while (p.isOpen(row, col));

        return new Position(row, col);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(T);
    }


}
