/******************************************************************************
 *
 * MAC0110 - Introdução à Computação
 * Aluno: Artur Magalhães Rodrigues dos Santos
 * Numero USP: 10297734
 * Tarefa: VotingMachinesDeluxe 2.1.15
 * Data: 13/05/2017 - 15/05/2017
 *
 * Baseado em VotingMachines.java e minhas respectivas funcoes dentro do pro -
 * grama.
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class VotingMachinesDeluxe {
  // Metodo main (nao foram utilizadas funcoes auxiliares).
  public static void main(String[] args)
    {   // Introducao das variaveis:
        // Votantes, porcentagem em a e tentativas, respectivamente.
        double N = Integer.parseInt(args[0]);
        double a = Double.parseDouble(args[1]);
        int T = Integer.parseInt(args[2]);
        // Numero inteiro de votantes em cada um dos candidatos
        double voteA = N * a;
        double voteB = N - voteA;
        // Variaveis booleanas a serem utilizadas nas condicoes
        // dos loops
        boolean who;
        boolean whol;
        // Contadores das interacoes que ocorrem em a, e em b,
        // devido aos possiveis erros e alteracoes de resultados
        int acount = 0;
        int bcount = 0;
        int falhou = 0;
        // Valores a serem utilizados no metodo da bisseccao para
        // achar a variavel f, que indica um maximo de erro aceitaval
        double fl = 0.5;
        double fla = 0.0;
        double flb = 0.5;
        // O resultado acaba por ser um delta entre o valores.
        double result = 0.5;
        // Dado um número arbitrário de interacoes
        for (int i = 0; i < 10000; i++) {
          // Loop das tentativas
          for (int j = 0; j < T; j++) {
                // Caso o A tenha mais votos, ou seja, ele ganhe,
                // é considerado verdadeira a variavel booleana
                // who.
                if (voteA > voteB) who = true;
                else who = false;
                // Aqui se verifica, de forma alternativa ao VotingMachines,
                // os erros dentro da eleicao, com base no fl (f linha)
                // inicial.
                for (int k = 0; k < voteA; k++) {
                    double ran = Math.random();
                    if (ran < fl) bcount++;
                    else acount++;
                }
                for (int k = 0; k < voteB; k++) {
                    double ran = Math.random();
                    if (ran < fl) acount++;
                    else bcount++;
                }
                // Caso meu contar de a seja maior que o de b,
                // a booleana whol (who linha), sera verdadeira.
                if (acount > bcount) whol = true;
                else whol = false;
                // Dado que ouve um erro, some no contador de falhas.
                // Posteriormente, repete-se a simulacao.
                if (who != whol) falhou++;
                acount = 0;
                bcount = 0;
            }
            // Porcetangem de falhas sobre tentativas,
            // que indica quando esta é valida, nas seguintes condicoes
            double valido = ((double)falhou)/T;
            // Se a %, for maior que 3%:
            if (valido > 0.03) {
                // Faz a divisao por dos pontos do intervalo e da
                // o valor absoluto entre eles.
                flb = fl;
                fl = (fl+fla)/2;
                result = Math.abs(fl-fla);
            }
            // Se a % for menor ou igual a 3%:
            if (valido <= 0.03) {
                // Faz divisao dos "intermediarios" do intervalo, e da
                // o valor absoluto entre eles.
                fla = fl;
                fl = (fl+flb)/2;
                result = Math.abs(fl-flb);
            }
            // Se chegarmos no intervalo de precisao desejado (delta = 0.002),
            // sai do loop; Caso contrario, segue a simulacao.
            if (result <= 0.002) break;
            falhou = 0;
        }
        // f sera o valor do intermediario, dentro do intervalo desejado,
        // sendo fo maximo toleravel.
        double f = flb;
        StdOut.println(f);
    }
}
