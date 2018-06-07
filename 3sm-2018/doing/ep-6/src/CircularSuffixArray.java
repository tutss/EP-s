/* Made by: tuts at 03/06/18
 *  Project: ep-6
 *  Dependencies:
 */

public class CircularSuffixArray {
    private static String in;
    private static int[] index;
    // Public methods
    public CircularSuffixArray(String s) {
        if (s == null) throw new IllegalArgumentException();
        in = s;
        for (int i = 0; i < in.length(); i++) {
            index[i] = i;
        }
        sort(in);
    }
    public int length(){
        return 0;
    }
    public int index(int i) {
        return 0;
    }

    // Private methods
    private static void sort(String s) {
        sort(0, s.length() -1, s);
    }

    private static void sort(int lo, int hi, String s) {

    }

    // exchange positions between i and j
    private void exch(int i, int j) {
        int temp = index[i];
        index[i] = index[j];
        index[j] = temp;
    }

    // return the dth character of s, -1 if d = length of s
    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }

    // Unit testing
    public static void main(String[] args) {

    }
}