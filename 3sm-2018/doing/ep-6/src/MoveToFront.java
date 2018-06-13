/* Made by: tuts at 31/05/18
 *  Project: ep-6
 *  Dependencies: BinaryStdIn, BinaryStdOut
 */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256;
    private static char[] alpha;

    // Public methods
    public static void encode() {
        // Cria o alfabeto e lê entrada
        String s = BinaryStdIn.readString();
        char[] c = s.toCharArray();
        alpha = alphabet();

        // Colocando ou não na lista
        // Um loop para a entrada, o outro para o alfabeto e suas mudanças
        for (int i = 0; i < c.length; i++) {
            char initial = c[i];
            /*
            Percorre no alfabeto, verificando se encontrou o char, que também "funciona"
            como um inteiro. Quando acha, troca as posições no alfabeto, fazendo isso para
            todas as letras da entrada.
            */
            char[] in = exch(initial);

            BinaryStdOut.write(in[2]);
            alpha[in[2]] = in[0];
            alpha[0] = in[1];
        }

        BinaryStdOut.close();
    }


    /*
    Decode method
    */
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


    private static char[] exch(char init) {
        char a;
        char b;
        char n;
        for (a = alpha[0], n = 0; init != alpha[n]; n++) {
            b = alpha[n];
            alpha[n] = a;
            a = b;
        }
        char[] ret = new char[3];
        ret[0] = a; ret[1] = init; ret[2] = n;
        return ret;
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