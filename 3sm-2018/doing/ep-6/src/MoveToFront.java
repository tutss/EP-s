/* Made by: tuts at 31/05/18
 *  Project: ep-6
 *  Dependencies: BinaryStdIn, BinaryStdOut
 */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256;

    // Public methods
    public static void encode() {
        // Cria o alfabeto e lê entrada
        String s = BinaryStdIn.readString();
        char[] c = s.toCharArray();
        char[] alphabet = alphabet();

        // Colocando ou não na lista
        // Um loop para a entrada, o outro para o alfabeto e suas mudanças
        for (int i = 0; i < c.length; i++) {
            char n;
            char initial = c[i];
            char tmp_to_keep, tmp_to_change;
            /*
            Percorre no alfabeto, verificando se encontrou o char, que também "funciona"
            como um inteiro. Quando acha, troca as posições no alfabeto, fazendo isso para
            todas as letras da entrada.
            */
            for (tmp_to_keep = alphabet[0], n = 0; initial != alphabet[n]; n++) {
                tmp_to_change = alphabet[n];
                alphabet[n] = tmp_to_keep;
                tmp_to_keep = tmp_to_change;
            }
            BinaryStdOut.write(n);
            alphabet[n] = tmp_to_keep;
            alphabet[0] = initial;
        }

        BinaryStdOut.close();
    }

    public static void decode() {
        String s = BinaryStdIn.readString();
        char[] c = s.toCharArray();
        char[] alphabet = alphabet();

        for (int i = 0; i < c.length; i++) {
            char place = alphabet[c[i]];
            BinaryStdOut.write(place, 8);
            while (c[i] > 0)
                alphabet[c[i]] = alphabet[--c[i]];
            alphabet[0] = place;
        }
        BinaryStdOut.close();
    }

    // Private methods
    private static char[] alphabet() {
        char[] alphabet = new char[R];
        for (char i = 0; i < alphabet.length; i++) {
            alphabet[i] = i;
        }
        return alphabet;
    }

    // Unit testing
    public static void main(String[] args) {
        if      (args[0].compareTo("-") == 0) encode();
        else if (args[0].compareTo("+") == 0) decode();
        else {
            throw new IllegalArgumentException("Illegal argument");
        }
    }
}