/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa: WordLoop.java
  Data: 23/11/2017

  Baseado em Graph.java, CC.java, DepthFirstSearch, WordCC.java
  (Todas estas pertencentes dos livros)

  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA.
 ******************************************************************************/



import java.util.Arrays;
public class WordLoop {

    /**************************************************************
    * Variaveis globais para marcar e verificar o percorrimento das
    * palavras.
    **************************************************************/
    private static ST<String, Boolean> marked;   // marked[v] = has vertex v been marked?
    private static boolean flag = false;


    /*******************************************************
    * Função que diferencia as palavras apenas por uma letra
    ********************************************************/
    private static boolean difLetter(String s, String t) {
      int differ = 0;
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) != t.charAt(i))
        differ++;
      }
      if (differ == 1)
        return true;
      else
        return false;
    }

    public static void main(String[] args) {
      In arg = new In(args[0]);
      Graph G = new Graph();
      String[] words = new String[100000];
      int i = 0;
      while (!arg.isEmpty()) {
        words[i] = arg.readString();
        i++;
      }

      /*************************************************
      * Montagem do grafo a partir da diferença de apenas
      * um elemento dentro das palavras.
      **************************************************/
      for (int k = 0; k < i; k++)
        for (int j = k + 1; j < i; j++)
          if(difLetter(words[k], words[j]))
            G.addEdge(words[k], words[j]);

      /*************************************
      * Recebe as strings da entrada padrão.
      **************************************/
      int a = 0;
      Queue<String> q = new Queue<String>();
      while (!StdIn.isEmpty()) {
        String s = StdIn.readString();
        q.enqueue(s);
      }


      int size = q.size();
      for (int k = 0; k < size; k++) {
        String takeOut = q.dequeue();
        boolean bol = G.hasVertex(takeOut);
        marked = new ST<String, Boolean>();
        for (String v : G.vertices()) marked.put(v, false);
          if (!bol)
           System.out.println(takeOut + ": not in graph");
          else {
           dfs(G, takeOut, takeOut, "", 0);
           if (!flag)
           System.out.println(takeOut + ": no word loop");
           flag = false;
          }
      }
    }

    /**********************************************************************
    * Função dfs modificada para manter a string inicial (ini), e percorrer
    * os valores conforme a recursão. Vou concatenando as palavras corretas
    * conforme seguimos na pilha. Height representa o que chamei de "altura",
    * que representa a distancia da string original.
    ***********************************************************************/
    public static void dfs(Graph G, String ini, String next, String concat, int height) {
       marked.put(next, true);
       concat += next;
       for (String w : G.adjacentTo(next)) {
         if (flag)
           break;
         if (!marked.get(w)) {
           dfs(G, ini, w, concat + " ", height+1);
         }

         /**********************************************************
         * Quando encontramos a palavra igual, imprimimos o caminho.
         ***********************************************************/
         if (ini.compareTo(w) == 0 && height >= 2) {
           System.out.println(ini + ": " + concat + " " + ini);
         flag = true;
         }
       }
    }
}
