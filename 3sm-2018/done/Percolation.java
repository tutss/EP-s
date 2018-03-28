import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;

public class Percolation {
    private int N;
    private int size;
    private int count;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF suf;
    private boolean grid[];
    private int first;
    private int last;

    /*
    * Percolation constructor
    * @param: int n - size of the matrix
     */
    public Percolation(int n) {
        size = n*n;
        N = n;
        uf = new WeightedQuickUnionUF(size+2);
        suf = new WeightedQuickUnionUF(size + 1);
        grid = new boolean[size];

        first = 0;
        last = size + 1;
        /*  Connect the virtual uf object to the first ones
        in the grid */
        for (int i = 1; i < n; i++) {
            uf.union(first, i);
            suf.union(first, i);
        }
        /*  And the last ones */
        for (int i = size; i > size - n; i--) {
            uf.union(last, i);
        }

    }

    /* opens the site (row, col) if it is not open already
    * @param: int row and int col
    */
    public void open(int row, int col) {
        // validate indexes
        int current = cartesianToId(row, col);
        if (current > size) throw new IllegalArgumentException();
        // mark as true and increment
        grid[current] = true;
        count++;
        // ver vizinhos e linkar
        linkNeighbor(current);
    }

    /* Links i component with its neighbours if
    * they were opened
    * @param: int i
    */
    private void linkNeighbor(int i) {
        int upper = i - N; int down = i + N;
        int left = i - 1; int right = i + 1;
        if (upper < size && upper > 0) {
            if (checkId(upper)) {
                uf.union(upper, i);
                suf.union(upper, i);
            }
        }
        if (right < size && right > 0) {
            if (checkId(right)) {
                uf.union(right, i);
                suf.union(right, i);
            }
        }
        if (left < size && left > 0) {
            if (checkId(left)) {
                uf.union(left, i);
                suf.union(left, i);
            }
        }
        if (down < size && down > 0) {
            if (checkId(down)) {
                uf.union(down, i);
                suf.union(down, i);
            }
        }
    }

    /* Checks, using the id, if the component is open or not
    * @param: int i
    */
    private boolean checkId(int i) {
        return grid[i];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        // incrementa a cada aberto (chamada de open())
        return count;
    }

    /* is the site (row, col) open?
    * @param: int row and int col
    */
    public boolean isOpen(int row, int col) {
        int current = cartesianToId(row, col);
        if (current < size && current >= 0)
            return grid[current];
        else
            throw new IllegalArgumentException();
    }

    /* is the site (row, col) full?
    * @param: int row and int col
    */
    public boolean isFull(int row, int col) {
    //  Verify if it is connected to the first virtual
        int current = cartesianToId(row, col);
        if (current > size || current < 0) throw new IllegalArgumentException();
        if (grid[current]) {
            return suf.connected(current, first);
        }
        return false;
    }

    /* does the system percolate?
    * @param: no parameters
    */
    public boolean percolates() {
    //  Verify if the last virtual is connected to the last one
        return uf.connected(first, last);
    }

    /*Converts the (x,y) to a linear array number
    * @param: int x and int y - positions
    */
    private int cartesianToId(int x, int y) {
        return ((N*x) + y);
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int grid = n*n;
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int x = StdRandom.uniform(0, n);
            int y = StdRandom.uniform(0, n);
            if (!p.isOpen(x, y)) {
                p.open(x, y);
            }
        }
        double threshold = (double) p.numberOfOpenSites()/grid;
        System.out.println("It percolated!");
        System.out.format("Threshold: %6f", threshold);
        System.out.println();
    }
}
