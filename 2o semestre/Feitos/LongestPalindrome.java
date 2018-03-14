/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa: Subpalíndromo mais longo
  Data: 19/09/2017
  Baseado em LongestCommonSegment.java, LCS fornecido no material de Princeton

  Dada uma String de entrada, o programa encontra o maior palíndromo dentro dela.
	Pode receber argumentos da linha de comando ou não.

  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA.
 ******************************************************************************/

public class LongestPalindrome {

	// old version of the code (not used)
	// @Deprecated
	public static void LongPaliSequence(String t) {
		int size = t.length();
		int startPosition = 0;
		int largeP = 1;
		boolean[][] palTable = new boolean[size][size];

		for (int i = 0; i < size; i++)
			palTable[i][i] = true;

	    for (int i = 0; i < size - 1; i++) {
	    	if(t.charAt(i) == t.charAt(i + 1)) {
	    		palTable[i][i + 1] = true;
	    		startPosition = i;
	    		largeP = 2;
	    	}
	    }

	    for (int curr = 3; curr <= size; curr++) {
	    	for (int i = 0; i < size - curr + 1; i++) {
	    		int j = i + curr - 1;
	    		if (t.charAt(i) == t.charAt(j)
	    			&& palTable[i+1][j-1]) {
	    			palTable[i][j] = true;
	    			startPosition = i;
	    			largeP = curr;
	    		}
	    	}
	    }
	    System.out.println("LargeP: " + largeP);
	    System.out.println("size: " + size);
	    System.out.println("start: " + startPosition);

	    System.out.println(t.substring(startPosition, startPosition + largeP));
	}

	public static void main(String[] args) {
		String s;
	    if (args.length > 0) {
				In in = new In(args[0]);
				s = in.readAll();
			}
	    else s = StdIn.readLine();
	    String t = s.replaceAll("\\s+", "").trim();
			String reverse = new StringBuilder(t).reverse().toString();
			String pali = SuffixArray2.lcsImproved(t, reverse);
			System.out.println(pali);
	}
}
