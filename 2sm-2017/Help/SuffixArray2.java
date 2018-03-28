/******************************************************************************
 *  Compilation:  javac SuffixArray2.java
 *  Execution:    java SuffixArray2 < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  A data type that computes the suffix array of a string.
 *
 *   % java SuffixArray2 < abra.txt
 *    i ind lcp rnk  select
 *   ---------------------------
 *    0  11   -   0  "!"
 *    1  10   0   1  "A!"
 *    2   7   1   2  "ABRA!"
 *    3   0   4   3  "ABRACADABRA!"
 *    4   3   1   4  "ACADABRA!"
 *    5   5   1   5  "ADABRA!"
 *    6   8   0   6  "BRA!"
 *    7   1   3   7  "BRACADABRA!"
 *    8   4   0   8  "CADABRA!"
 *    9   6   0   9  "DABRA!"
 *   10   9   0  10  "RA!"
 *   11   2   2  11  "RACADABRA!"
 *
 * Essa classe foi modificada adicionando os metodos lcpImproved, lcsImproved,
 * e tambem compareImproved, com intuito de serem usados pelas classes LongestPa
 * lindrome e LongestCommonSegment.
 * Esses métodos foram baseados nas classes de LCS de Princeton, fornecidas
 * no material.
 *
 ******************************************************************************/

import java.util.Arrays;

public class SuffixArray2 {
    private Suffix[] suffixes;

    /**
     * Initializes a suffix array for the given {@code text} string.
     * @param text the input string
     */
    public SuffixArray2(String text) {
        int n = text.length();
        this.suffixes = new Suffix[n];
        for (int i = 0; i < n; i++)
            suffixes[i] = new Suffix(text, i);
        Arrays.sort(suffixes);
    }

    private static class Suffix implements Comparable<Suffix> {
        private final String text;
        private final int index;

        private Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }
        private int length() {
            return text.length() - index;
        }
        private char charAt(int i) {
            return text.charAt(index + i);
        }

        public int compareTo(Suffix that) {
            if (this == that) return 0;  // optimization
            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }
            return this.length() - that.length();
        }

        public String toString() {
            return text.substring(index);
        }
    }

    /**
     * Returns the length of the input string.
     * @return the length of the input string
     */
    public int length() {
        return suffixes.length;
    }


    /**
     * Returns the index into the original string of the <em>i</em>th smallest suffix.
     * That is, {@code text.substring(sa.index(i))} is the <em>i</em>th smallest suffix.
     * @param i an integer between 0 and <em>n</em>-1
     * @return the index into the original string of the <em>i</em>th smallest suffix
     * @throws java.lang.IllegalArgumentException unless {@code 0 <= i < n}
     */
    public int index(int i) {
        if (i < 0 || i >= suffixes.length) throw new IllegalArgumentException();
        return suffixes[i].index;
    }

    // lcp method improved
    // semelhante a encontrada no material de Princeton
    private static String lcpImproved(String s, int p, String t, int q) {
      int n = Math.min(s.length() - p, t.length() - q);
      for (int i = 0; i < n; i++) {
        if (s.charAt(p + i) != t.charAt(q + i))
        return s.substring(p, p + i);
      }
      return s.substring(p, p + n);
    }

    // not used
    public String select(int i) {
        if (i < 0 || i >= suffixes.length) throw new IllegalArgumentException();
        return suffixes[i].toString();
    }

    // lcs method improved
    // semelhante ao material de Princeton
    public static String lcsImproved(String s, String t) {
      SuffixArray2 firstSuffix = new SuffixArray2(s);
      SuffixArray2 secSuffix = new SuffixArray2(t);
      int i = 0;
      int j = 0;
      String lcs = "";

      while (i < s.length() && j < t.length()) {
        int p = firstSuffix.index(i);
        int q = secSuffix.index(j);
        String longSub = lcpImproved(s, p, t, q);
        if (longSub.length() > lcs.length()) lcs = longSub;
        if (compareImproved(s, p, t, q) < 0) i++;
        else                                 j++;
      }
      return lcs;
    }

    // compare method between the strings improved
    public static int compareImproved(String s, int p, String t, int q) {
        int n = Math.min(s.length() - p, t.length() - q);
        for (int i = 0; i < n; i++) {
          if (s.charAt(p + i) != t.charAt(q + i))
            return s.charAt(p + i) - t.charAt(q + i);
        }
        if (s.length() - p < t.length() - q) return -1;
        else if (s.length() - p > t.length() - q) return +1;
        else return 0;
    }

    // old compare method
    private static int compare(String query, Suffix suffix) {
        int n = Math.min(query.length(), suffix.length());
        for (int i = 0; i < n; i++) {
            if (query.charAt(i) < suffix.charAt(i)) return -1;
            if (query.charAt(i) > suffix.charAt(i)) return +1;
        }
        return query.length() - suffix.length();
    }
}
