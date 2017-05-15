/******************************************************************************
 *
 * MAC0110 - Introdução à Computação
 * Aluno: Artur Magalhães Rodrigues dos Santos
 * Numero USP: 10297734
 * Tarefa: Arrangements 2.3.17
 * Data: 13/05/2017
 *
 * Baseado em Permutations.java, Heap's Algorithm, LetterHisto.java e
 * CharHisto.java
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class Arrangements {

    // Funcao que realiza a troca entre as posicoes dentro do array
    public static String troca(String s, String[] sequence, int a, int b) {
        String prox = "";
        int tamanho = sequence.length;
        // Troca nas posicoes dentro do array
        String c = sequence[a];
        sequence[a] = sequence[b];
        sequence[b] = c;
        // Aqui se retorna o "valor" da string na proxima casa;
        // passando para a proxima casa;
        for (int i = 0; i < tamanho; i++) prox = prox + sequence[i];
        return prox;
    }

    // Funcao que realiza a permutacao dentro do array de string "sequence"
    public static void perm1(String s, String[] sequence, int beg, int size) {
        // Condicao para inicializao do loop,
        // o inicio do vetor tem que ser menor que seu tamanho.
        if (beg < size) {
          // Como visto nos programas de apoio, criei um vetor booleano,
          // com 127 casas, que representam os caracteres. Ele é inicializado
          // com todas as casas em "falso".
          boolean[] charac = new boolean[127];
          // Loop para leitura do array
          for (int i = 0; i <= size - beg; i++) {
            // Condicao para troca: a casa ser falsa, ou seja,
            // identifica se o caractere ja existe.
            if(charac[s.charAt(beg + i)] == false) {
              // Troca o seu valor por verdadeiro.
              charac[s.charAt(beg + i)] = true;
              // Troca entre as casas do array, e mudasse a posicao das letras
              // do string, chamando a funcao de troca e a de permutacao
              // novamente.
              s = troca(s, sequence, beg, beg + i);
              perm1(s, sequence, beg + 1, size);
              s = troca(s, sequence, beg, beg + i);
            }
          }
        }
        else System.out.println(s);
    }

    // Metodo main
    public static void main(String[] args) {
        String s = args[0]; // Introducao do argumento
        String[] sequence = s.split(""); // Divisao em um array de strings
        int size = s.length() - 1; // Ultima casa do vetor "sequence"
        perm1(s,sequence, 0, size); // Chamada da funcao perm1
    }
}
