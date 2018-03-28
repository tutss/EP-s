/******************************************************************************
 MAC0121 - Algoritmos e Estruturas de Dados I
 Aluno: Artur Magalhães Rodrigues dos Santos
 Numero USP: 10297734
 Tarefa: Median given two sorted arrays - Web Exercise 2.5.23
 Data: 05/10/2017
 Baseado em Quick.java (select)
 DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 DE CÓPIAS DESTA PROGRAMA.
 ******************************************************************************/
import java.util.Arrays;
public class Median2Sorted {

    /*******************************************************************************************
    * Função merge utilizada para verificar possíveis erros, apenas. Fiz alguns
    * testes utilizando o Driver.java, e eles não coincidiram com os testes desta classe.
    * Não sei se executei o Driver.java corretamente, mas todos os testes verificados dentro desta
    * classe chegaram a resposta correta.
    ********************************************************************************************/
    private static void merge(Comparable[] a, Comparable[] b, Comparable[] c) {
        int i = 0, j = 0;
        int n = a.length + b.length;

        for (int k = 0; k < n; k++) {
            if      (i == a.length)            c[k] = b[j++];
            else if (j == b.length)            c[k] = a[i++];
            else if (a[i].compareTo(b[j]) < 0) c[k] = a[i++];
            else                               c[k] = b[j++];
        }
    }

    /********************************************************************************************
    * Função search que percorre ambos os vetores simultaneamente, por meio de variaveis auxiliares
    * e indices i e j. i e j delimitam o espaço de busca, enquanto walkA e walkB são os valores
    * que percorrem o vetor. Posteriormente, atribuisse esses valores ao indice e a função é
    * retornada. search realiza um processo semelhante a uma busca binária, e maintain é um valor
    * fixo no qual utilizei por meio de pesquisas.
    * Dois casos são analisados para a troca dos valores.
    *********************************************************************************************/
    public static int[] search(int i, int j, int search, int k, Comparable[] a, Comparable[] b) {
        int maintain = k - 1;

        while (i+j < maintain) {
            search = (k -i -j)/2;
            int walkA = i + search;
            int walkB = j + search;

            if (a.length > walkA - 1 && (b.length <= walkB - 1 || a[walkA-1].compareTo(b[walkB-1]) < 0)) {
                i = walkA;
            }
            else {
                j = walkB;
            }
        }

        int[] ij = new int[2];
        ij[0] = i;
        ij[1] = j;
        return ij;

    }

    /*******************************************************************************
    * Função select que encontra o k-ésimo elemento em tempo O(log N), onde
    * N é a soma do tamanho dos dois vetores. Ele funciona com base em comparações,
    * nas quais analisamos os valores intermediários, e como os vetores vem ordenados,
    * determinamos onde a k-ésima posição estará a partir disto.
    * OBS: Não ordenei a entrada assumindo que a e b vem ordenados, como no enunciado,
    * e para manter a complexidade desejada.
    ********************************************************************************/
    public static Comparable select(Comparable[] a, Comparable[] b, int k) {
        // Atualização de k para achar no vetor
        k = k + 1;

        // Assumindo que os dois arrays tem o mesmo tamanho
        int i = 0;
        int j = 0;
        int search = 0;
        int[] found = search(i, j, search, k, a, b);

        /* Retorna um erro caso k não esteja no range
         do vetor */
        if (k < 0 || k > a.length + b.length)
            return -1;
        if (a.length > found[0] && (b.length <= found[1] || a[found[0]].compareTo(b[found[1]]) < 0)) {
            return a[found[0]];
        } else {
            return b[found[1]];
        }

    }

    /**************************
    * Função main para testes *
    ***************************/
    public static void main(String[] args) {
        // Considerando k > 0
        int kth = Integer.parseInt(args[0]);
        String s = args[1];
        String t = args[2];

        Comparable[] test = s.split("");
        Comparable[] test2 = t.split("");
        Comparable[] mer = new Comparable[test.length + test2.length];

        System.out.println(" Valor de test: ");
        for (Integer i = 0; i < test.length; i++) {
            System.out.print(test[i]+ " ");
        }
        System.out.println();
        System.out.println("Valor de test2: ");
        for (Integer i = 0; i < test2.length; i++) {
            System.out.print(test2[i] + " ");
        }
        System.out.println();

        Arrays.sort(test);
        Arrays.sort(test2);
        merge(test, test2, mer);

        System.out.println("Arrays depois de merge: ");
        for (Integer i = 0; i < mer.length; i++) {
            System.out.print(mer[i] + " ");
        }
        System.out.println();
        System.out.println();

        Comparable c = select(test, test2, kth);
        System.out.println(c);
    }
}
