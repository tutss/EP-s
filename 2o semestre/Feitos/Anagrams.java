/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa: Anagrams - Web Exercise 4.2.12 (Anagrams)
  Data: 13/09/2017

  Tempos de execução:
  exemplo.in =       real    0m0.122s     6 palavras
  words5.txt =       real    0m0.200s     2415 palavras
  Pwords.txt =       real    0m1.562s     275502 palavras
  500k_words.txt =   real    0m2.892s     500159 palavras

  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA.
 ******************************************************************************/

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Anagrams {

    // Retira duplicatas de uma ArrayList<>
    public static void noDups(ArrayList<String> seqAn) {
      LinkedHashSet<String> set = new LinkedHashSet<String>();
      set.addAll(seqAn);
      seqAn.clear();
      seqAn.addAll(set);
    }

    // Verifica se existe anagrama entre duas palavras
    public static boolean findAnagram(String s, String t) {
      char[] sarray = s.toLowerCase().trim().toCharArray();
      char[] tarray = t.toLowerCase().trim().toCharArray();
      Arrays.sort(sarray);
      Arrays.sort(tarray);
      if (String.valueOf(sarray).equals(String.valueOf(tarray)))
        return true;
      return false;
    }
    // Preenche um vetor com strings originários de outro de forma ordenada
    public static void charOrder(String[] input, String[] order) {
      for (int i = 0; i < input.length; i++) {
        char[] c = input[i].toLowerCase().trim().toCharArray();
        Arrays.sort(c);
        order[i] = new String(c);
      }
      for (int i = 0; i < order.length; i++) {
        order[i] = order[i] + "+" + i;
      }
      Arrays.sort(order);
    }
    // Printa os anagramas
    private static void printAnagram(ArrayList<String> printable) {
      noDups(printable);
      if (printable.size() > 1) {
        System.out.print("* ");
        for (int i = 0; i < printable.size(); i++) {
          System.out.print(printable.get(i) + " ");
        }
        System.out.print("\n");
      }
    }

    // Busca dos anagramas no vetor
    public static void findImproved (String[] input, String[] order) {
      String[] verifyString = new String[2];
      String[] verifyNext = new String[2];
      ArrayList<String> printable = new ArrayList<String>();
      for (int i = 0; i < order.length - 1; i++) {
        verifyString = order[i].split("\\+");
        verifyNext = order[i+1].split("\\+");
        int localS = Integer.parseInt(verifyString[1]);
        int localN = Integer.parseInt(verifyNext[1]);
        if (findAnagram(verifyString[0], verifyNext[0])) {
          printable.add(input[localS]);
          printable.add(input[localN]);
        }
        else {
          printAnagram(printable);
          printable.clear();
        }
        Arrays.fill(verifyString, null);
        Arrays.fill(verifyNext, null);
      }
    }

    public static void main(String[] args) {
      if (!StdIn.isEmpty()) {
        String[] input = StdIn.readAllStrings();
        String[] order = new String[input.length];
        charOrder(input, order);
        findImproved(input, order);
      }
      else
        System.out.println(" No input. ");
    }

}
