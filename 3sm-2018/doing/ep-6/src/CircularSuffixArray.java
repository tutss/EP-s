/* Made by: tuts at 03/06/18
 *  Project: ep-6
 */

public class CircularSuffixArray {
    private static int size;
    private static int[] index;

    private static final int CUTOFF =  15;   // cutoff to insertion sort

    // Public methods

    /*
    * Constructor method
     */
    public CircularSuffixArray(String s) {
        if (s == null) throw new IllegalArgumentException();
        size = s.length();
        index = new int[size];
        for (int i = 0; i < s.length(); i++) {
            index[i] = i;
        }
        sort(s);
    }

    /*
    * Returns the length of the string
     */
    public int length(){
        return size;
    }

    /*
    * Returns the index of the Circular Suffix (ordered)
     */
    public int index(int i) {
        return index[i];
    }

    // Private methods
    private static void sort(String s) {
        sort(0, size -1, s, 0);
    }

    /*
    * Code similar to the 3-way quicksort implementation
    * seen in Algorithms website.
     */
    /**********************************************************/
    private static void sort(int lo, int hi, String s, int d) {
        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(s, lo, hi, d);
            return;
        }

        /*
        * Sorting using recursion (similar to 3-way quicksort)
         */
        int lt = lo, gt = hi;
        int v = charAt(s, index[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(s, index[i], d);
            if      (t < v) exch(lt++, i++);
            else if (t > v) exch(i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(lo, lt-1, s, d);
        if (v >= 0) sort(lt, gt, s, d+1);
        sort(gt+1, hi, s, d);
    }

    /*
    * Use insertion sort if the string is short
     */
    // sort from a[lo] to a[hi], starting at the dth character
    private static void insertion(String a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a, j, j-1, d); j--)
                exch(j, j-1);
    }

    private static boolean less(String a, int i, int j, int d) {
        int c_1 = index[i];
        int c_2 = index[j];
        while (d < size) {
            int cmp_1 = charAt(a, c_1, d);
            int cmp_2 = charAt(a, c_2, d);
            if (cmp_1 > cmp_2) {
                return true;
            }
            else if (cmp_1 < cmp_2) {
                return false;
            }
            d++;
        }
        return true;
    }

    // exchange positions between i and j
    private static void exch(int i, int j) {
        int temp = index[i];
        index[i] = index[j];
        index[j] = temp;
    }

    // return the dth character of s, -1 if d = length of s
    private static int charAt(String s, int k, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt((k + d) % size);
    }
    /******************************************************/

    // Unit testing
    public static void main(String[] args) {
        CircularSuffixArray c = new CircularSuffixArray("banana");
        for (int i = 0; i < c.length(); i++) {
            int k = c.index(i);
            System.out.print(k + " - - - ");
            System.out.print("banana".charAt(k));
            System.out.println();
        }
    }
}