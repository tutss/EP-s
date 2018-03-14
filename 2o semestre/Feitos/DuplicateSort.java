/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa: DuplicateSort
  Data: 27/7/2017

  java -cp .:stdlib.jar Generator 4000000 4 abc | java -cp .:stdlib.jar Driver
	 No of comparisons: 82371943
	 No of comparisons: 90615253
	 No of comparisons: 26362687

  java -cp .:stdlib.jar Generator 8000000 4 abc | java -cp .:stdlib.jar Driver
	No of comparisons: 172701080
	No of comparisons: 190935416
	No of comparisons: 50759073

  Baseado em 23Quicksort.pdf (Slides de sala), Algs4, 3-way partition

  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA.
 ******************************************************************************/

public class DuplicateSort {

   /******************************************************
   * Utilizei do método Quicksort, na forma de 3-way
   * partition, como no Algs4. De forma semelhante, ao
   * problema da bandeira holandesa, o número de compa-
   * rações é reduzido a partir do momento que analisamos
   * os casos em que os comparables são iguais, e evita-
   * mos comparações desnecessárias.
   ******************************************************/
  private static void exch(Object[] a, int i, int j) {
      Object swap = a[i];
      a[i] = a[j];
      a[j] = swap;
  }

  /********************************************************
  * Método sort, utilizando quicksort 3-way partitioning.
  * Pela natureza do quicksort, com N log N comparações, e
  * a adaptação para parada quando comparar itens iguais,
  * temos o número de comparações muito reduzido. A melhor
  * e mais intuitiva solução que encontrei foi esta.
  *********************************************************/
  public static void sort(Comparable[] a, int lo, int hi) {
      if (hi <= lo) return;
      int i = lo;
      int lt = lo;
      int gt = hi;
      Comparable v = a[lo];

      while (i <= gt) {
      	int cmp = a[i].compareTo(v);
      	if      (cmp < 0) exch(a, lt++, i++);
      	else if (cmp > 0) exch(a, i, gt--);
      	else              i++;
      }
      sort(a, lo, lt - 1);
      sort(a, gt + 1, hi);
  }

  /**********************************************************
  * Sort que é chamado pela função Drive, foi adaptado para
  * funcionar de forma semelhante ao 3-way, visto em Algs4 e
  * nos slides.
  ***********************************************************/
  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
  	sort(a, 0, a.length - 1);
  }

  /************************
  * Método main para testes
  *************************/
  public static void main(String[] args) {
    String[] a = {"a","d","p","k","a","d","p","k","a","p","k"};
    sort(a);
    System.out.println(a);
  }
}
