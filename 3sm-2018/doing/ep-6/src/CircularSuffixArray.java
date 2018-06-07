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
        int n = s.length();
    }

    // Unit testing
    public static void main(String[] args) {

    }
}