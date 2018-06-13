/* Made by: tuts at 12/06/18
 *  Project: ep-6
 */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    // Public methods
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        int count = 0;
        for (int i = 0; i < csa.length() && csa.index(i) != 0;) {
            count = ++i;
        }
        BinaryStdOut.write(count);
        // Index na string
        for (int i = 0; i < s.length(); i++) {
            int place = (csa.index(i) + s.length() - 1);
            // Rodar até encontrar novamente
            char bw = s.charAt(place % s.length());
            BinaryStdOut.write(bw);
        }
        BinaryStdOut.close();
    }

    public static void inverseTransform() {

    }

    // Private methods


    // Unit testing
    public static void main(String[] args) {
        if      (args[0].compareTo("-") == 0) transform();
        else if (args[0].compareTo("+") == 0) inverseTransform();
        else {
            throw new IllegalArgumentException("Illegal argument");
        }
    }
}