/* Made by: tuts at 31/05/18
 *  Project: ep-6
 *  Dependencies: BinaryStdIn, BinaryStdOut
 */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.RedBlackBST;

import java.util.ArrayList;

public class MoveToFront {

    private static class Duo {
        int index;
        int new_index;

        public Duo() {
            this.index = 0;
            this.new_index = 0;
        }

        public int new_local(int old_index) {
            return new_index;
        }

        public int old_local(int new_place){
            return index;
        }
    }

    // Public methods
    public static void encode() {
        // Cria o alfabeto e lê entrada
        String s = BinaryStdIn.readString();
        char[] c = s.toCharArray();
        Alphabet alphabet = Alphabet.EXTENDED_ASCII;


        ArrayList<Integer> list = new ArrayList<>();
        RedBlackBST<Integer, Integer> rbt = new RedBlackBST<>();

        // Colocando ou não na lista
        for (int i = 0; i < c.length; i++) {
            int alpha_index = alphabet.toIndex(c[i]);
            if (!list.contains(alpha_index)) {
                // Colocando com valores da inserção
                int real_local = list.size();
                list.add(alpha_index);
                rbt.put(alpha_index, real_local);
            }
            else {
                // Colocando quando o valor já era existente
                int size = list.size() - 1;
                int new_alpha_index = rbt.get(alpha_index) + size;
                int real_local = size + 1;
                list.add(new_alpha_index);
                rbt.put(alpha_index, real_local);
            }
        }

    }

    public static void decode() {

    }

    // Private methods
    private void insert()

    // Unit testing
    public static void main(String[] args) {
        if      (args[0].compareTo("-") == 0) encode();
        else if (args[0].compareTo("+") == 0) decode();
        else {
            throw new IllegalArgumentException("Illegal argument");
        }
    }
}