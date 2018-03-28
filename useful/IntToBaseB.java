/******************************************************************************
 *
 * MAC0110 - Introdução à Computação
 * Aluno: Artur Magalhães Rodrigues dos Santos
 * Numero USP: 10297734
 * Tarefa: IntToBaseB - Ex.6 BaseB
 * Data: 20/05/2017
 *
 * Baseado em
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/


public class IntToBaseB {
  // Metodo para conversao de bases
  public static String baseB(int read, int base) {
    // Se a base ultrapassar os valores limites para conversao
    if (base < Character.MIN_RADIX || base > Character.MAX_RADIX) return null;
        // Todos os 36 caracteres possiveis a serem utilizados (36)
        char caract[] = {'0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' ,
        '9' , 'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,'i' , 'j' , 'k' ,
        'l' , 'm' , 'n' , 'o' , 'p' , 'q' , 'r' , 's' , 't' ,'u' , 'v' , 'w' ,
        'x' , 'y' , 'z'};
        char a[] = new char[36];
        // O booleano checar verifica a condicao de os inteiros serem maiores
        // do que 0
        boolean checar = (read < 0);
        // Posicao a percorrer o vetor
        int atual = 35;
        // Caso o inteiro nao seja negativo, inverta o sinal para
        // percorrer, mais a frente, o vetor
        if (!checar) {
          read = -read;
         }
        // Condicao para percorrer o vetor
        while (read <= -base) {
          // Decresce as posicoes, verificando a divisibilidade pela base,
          // e atribui tal valor a posicao no vetor. Tambem, divide sucessiva-
          // mente o inteiro pelo valor da base.
          a[atual--] = caract[-(read % base)];
          read = read / base;
         }
         // Atribui, depois das trocas ocorridas acima, a posicao do vetor
         // de caracteres, ao vetor char
        a[atual] = caract[-read];
        return new String(a, atual, (36 - atual));
     }
  // Metodo main
  public static void main(String[] args) {
    // Le qual a base para qual se deseja converter o inteiro
    int base = Integer.parseInt(args[0]);
    while (!StdIn.isEmpty()) {
      // Caso seja maior que 36, saia do loop
      if (base > 36 || base < 2) {
         StdOut.println("Não será possível realizar a mudança.");
         break;
      }
      // Le-se os inteiros vindos da entrada padrao
      int[] read = StdIn.readAllInts();
      // Para cada um desses inteiros, chame baseB
      for (int i = 0; i < read.length; i++) {
        if (read[i] < 0) StdOut.println("Numero negativo abaixo - " + (i+1) + "o");
         String s = baseB(read[i],base);
         StdOut.println(s);
      }
    }
  }
}
