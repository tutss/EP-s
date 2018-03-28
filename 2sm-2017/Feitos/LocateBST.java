/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa: Non-overlapping interval search
  Data: 03/10/2017
  Baseado em ... (breve e se aplicável)
  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA.
 ******************************************************************************/

public class LocateBST {

    public static boolean interval(Integer min, Integer max, Integer value) {
      return max >= value && min <= value;
    }

    public static void main(String[] args) {
      BST<String, Integer> tree = new BST<String, Integer>();
      In receive = new In(args[0]);
      String[] in = receive.readAllLines();
      while (!StdIn.isEmpty()) {
        Integer value = StdIn.readInt();
        for (int i = 0; i < in.length; i++) {
          String[] numbs = in[i].split(" ");
          String s = "[" + numbs[0] + ", " + numbs[1] + "]";
          Integer low = Integer.parseInt(numbs[0]);
          Integer up = Integer.parseInt(numbs[1]);
          if (interval(low, up, value)) {
            tree.put(s, value);
            System.out.println(value + ": " + s);
            break;
          }
          else {
            if ( i == (in.length - 1) && up < value) {
              tree.put("right of " + s, value);
              System.out.println(value + ": right of " + s);
              break;
            }
            else if (up < value) continue;
            else if (low > value && i != 0) {
              String[] t = in[i-1].split(" ");
              String k = "[" + t[0] + ", " + t[1] + "]";
              tree.put("between " + k + " & " + s, value);
              System.out.println(value + ": between " + k + " & " + s);
              break;
            }
            else if (low > value) {
              tree.put("left of " + s, value);
              System.out.println(value + ": left of " + s);
              break;
            }

          }
        }
      }

    }

}
