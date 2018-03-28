/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa: Segmento comum mais longo
  Data: 19/09/2017
  Baseado em SuffixArray.java, LCS fornecido no material de Princeton.

  Dado duas Strings de entrada, o programa encontra a maior sequência em comum
  dentro delas.
	Pode receber argumentos da linha de comando ou não.

  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA.
 ******************************************************************************/

public class LongestCommonSegment {

  public static void main(String[] args) {
    String s, t;
    if (args.length > 0) {
      In firstStr = new In(args[0]);
      In secStr = new In(args[1]);
      s = firstStr.readLine();
      t = secStr.readLine();
    }
    else {
      s = StdIn.readLine();
      t = StdIn.readLine();
    }

    s = s.replaceAll("\\s+", " ").trim();
    t = t.replaceAll("\\s+", " ").trim();

    String longest = SuffixArray2.lcsImproved(s, t);
    System.out.println(longest);
  }
}
