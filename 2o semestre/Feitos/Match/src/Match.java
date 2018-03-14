/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa: Match.java - Parafusos e porcas 
  Data: 27/7/2017
  Baseado em Interval1D.java, Comparators
  
  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA. 
 ******************************************************************************/ 
import pieces.*;
import java.util.Arrays;
import java.util.Comparator;


/*******************************************************
 Optei pela implementação de um Comparator,
 pois tendo em vista as comparações que podemos realizar
 entre os bolts e os nuts, um método Comparator facilita
 a ordenação, que pode ser feita pelo Arrays.sort.
********************************************************/

public class Match {
  
	/*********************************************************
   * Fixei um nut e um bolt qualquer, criado por tentativa *
   *********************************************************/
   
  private static Nut Nset = new Nut(1);
  private static Bolt Bset = new Bolt(1);
  
  /*******************************************************
   * Os Comparators foram feitos observando o Intervals1D, 
   * um EP passado
   *******************************************************/
  
  public static final Comparator<Nut> NUT_ORDER = new NutComparator();
  
  public static final Comparator<Bolt> BOLT_ORDER = new BoltComparator();
  
  /***************************************************************
   * As comparações dentro do método compare, foram feitas 
   * observando Intervals1D.java. Entretanto, nesse caso,
   * a única comparação possível é entre as classes bolt e
   * nut, que são diferentes. Sabemos que elas geram inteiros,
   * e que o compareTo delas faz a diferença entre o nut e o bolt,
   * ou vice-versa.
   ***************************************************************/
  
  private static class NutComparator implements Comparator<Nut> {
	  public int compare(Nut a, Nut b) {
		  int alpha = a.compareTo(Bset);
		  int beta = b.compareTo(Bset);
		  return alpha - beta;
	  }
  }
  
  private static class BoltComparator implements Comparator<Bolt> {
	  public int compare(Bolt a, Bolt b) {
		  int alpha = a.compareTo(Nset);
		  int beta = b.compareTo(Nset);
		  return alpha - beta;
	  }
  }
	
  /**********************************************************
   * Função match que ordena os dois vetores de nuts e bolts,
   * retornando eles na forma pareada.
   **********************************************************/
  
  public static void match(NutsAndBolts nutsAndBolts) {
    Nut[] nuts = nutsAndBolts.nuts();
    Bolt[] bolts = nutsAndBolts.bolts();

    Arrays.sort(nuts, NUT_ORDER);
    Arrays.sort(bolts, BOLT_ORDER);
  }

}
