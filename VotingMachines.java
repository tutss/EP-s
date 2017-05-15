/******************************************************************************
 *
 * MAC0110 - Introdução à Computação
 * Aluno: Artur Magalhães Rodrigues dos Santos
 * Numero USP: 10297734
 * Tarefa: Voting Machines 2.1.5
 * Data: 13/05/2017
 *
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class VotingMachines {

  // Metodo para calcular as eleicoes, quem ganha e numero
  // de falhas ocorridas.
  public static boolean election(int N, double a, double f) {
    int voteA = (int)(N * a); // Votantes em A
    int voteB = N - voteA; // Votantes em B
    boolean who = true;
    for (int i = 0; i < N; i++) {
        double ran = Math.random();
        // Se um n aleatorio for menor que o erro:
        // Dois casos possiveis
        if (ran <= f/2) {
            if (voteA > 0) voteA--;
            if (voteB <= N) voteB++;
          }
        if (ran >= f/2 && ran <= f) {
            if (voteA <= N) voteA++;
            if (voteB > 0) voteB--;
          }
      }
      // Quem ganhou ao final da eleicao
      if (voteA > voteB) who = true;
      else who = false;
      return who;
    }

    // Funcao que calcula o numero de falhas
    public static int falhas(double a, int T, int N, double f) {
      int fail = 0;
      // Conforme dado o numero de tentativas:
      for (int i = 0; i < T; i++) {
        if (fail < T) {
          // Simulacao de quem ganha a eleicao
          boolean who = election(N, a, f);
	  // Caso o erro seja muito grande, como o de mais de 70%, certa-
	  // mente a eleicao tera alteracao
          if (f > .7) fail++;
          // Caso o erro passe dos 50%, ou seja menor que esse valor
          // , a eleicao sera alterada.
          if (f > .5 && who || f < .5 && !who) fail++;
          // Caso o "a" esteja indicado com mais de 50% dos votos, mas o
          // candidato B ganhou, ocorreu um erro
          if (a > .5 && !who) fail++;
          // Se o candidato A estava com menos de 50% dos votos,
          // mas ganhou, ocorreu um erro
          else if (a < .5 && who) fail++;
          // Mas tambem, se o candidato A tiver mais de 50% + a % de erro
          // dos votos (50%+f%), ele nao podera perder (no maximo empata)
          else if (a > (0.05 + f) && fail > 0) fail--;
        }
       }
      return fail;
      }

    // Metodo main, para input de variaveis e saida de resultados.
    public static void main(String[] args) {
      int N = Integer.parseInt(args[0]);
      double a = Double.parseDouble(args[1]);
      double f = Double.parseDouble(args[2]);
      int T = Integer.parseInt(args[3]);
      // Chamada da funcao para computar o numero de falhas
      int falhou = falhas(a, T, N, f);
      double valido = (double)(falhou)/(double) T;

      StdOut.println("Wrong results/trials: " + falhou + "/" + T);
      if (valido <= 0.03) StdOut.println("These values are acceptable");
      else StdOut.println("These values are not acceptable");
    }
}
