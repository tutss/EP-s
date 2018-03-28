/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa: Número de ocorrências em lista ordenada - CountOccurrences
  Data: 18/07/2017

  Baseado em busca binária implementada em Ortho.java, BinarySearch.java do
  material de Princeton.

  Exemplos de execução: 
      java -cp .:stdlib.jar CountOccurrences 1000000_10s.in < 1000000_10.in > 
      1000000_10.out

  Tempos de execução:
      p/ entrada 1000000_10s.in < 1000000_10.in -       3,22s user
      p/ entrada 1000000_10000s.in < 1000000_10000.in - 4,64s user

  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA. 
 ******************************************************************************/ 

public class CountOccurrences {
    
    // Binary Search
	public static int find(int in, int[] dict) {
	    return search(in, dict, 0, dict.length);
	}

	public static int search(int in, int[] dict, int floor, int ceil) {
		int sizeDict = dict.length;
	    int index = floor + (ceil - floor) / 2;
	    if (ceil <= floor) return index;
	    // Caso o ultimo número seja menor que o número chamado
	    else if (dict[sizeDict - 1] < in) return sizeDict;

	    if      (dict[index] > in)        return search(in, dict, floor, index);
	    else if (dict[index] < in)        return search(in, dict, index + 1, ceil);
	    else if (floor != index)          return search(in, dict, floor, index);
	    else                              return index;
	}

	public static void main(String[] args) {
		In receive = new In(args[0]);
		int[] dict = receive.readAllInts();
		int[] in = StdIn.readAllInts();
        // Busco um valor, seu próximo, e faço a diferença.
		for (int i = 0; i < in.length; i++) {
			int firstIndex = find(in[i], dict);
			int nextIndex = find(in[i] + 1, dict);
			int diff = nextIndex - firstIndex;
			System.out.println(diff);
		}
	}	
}