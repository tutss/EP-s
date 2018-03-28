/******************************************************************************
 *
 * MAC0110 - Introdução à Computação
 * Aluno: Artur Magalhães Rodrigues dos Santos
 * Numero USP: 10297734
 * Tarefa: IntFromBaseB - Ex.6 BaseB
 * Data: 20/05/2017
 *
 * Baseado em IntToBaseB.java
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class IntFromBaseB {
  // Metodo para conversao para de base
  public static String integerFromB(int read, int base) {
    // Caso a base seja um valor fora do possivel, retorne nulo
    if (base < Character.MIN_RADIX || base > Character.MAX_RADIX) return null;
        // Vetor com todos os caracteres possiveis
        char caract[] = {'0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' ,
        '9' , 'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,'i' , 'j' , 'k' ,
        'l' , 'm' , 'n' , 'o' , 'p' , 'q' , 'r' , 's' , 't' ,'u' , 'v' , 'w' ,
        'x' , 'y' , 'z'};
        // Vetor a ser utilizado para leitura
        char a[] = new char[36];
        // Checa se a entrada tem sinal positivo ou nao
        boolean checar = (read < 0);
        // Posicao a ser percorrida
        int atual = 35;
        if (!checar) {
          read = -read;
         }
         // Condicao para passagem do vetor
        while (read <= -base) {
          a[atual--] = caract[-(read % base)];
          read = read / base;
         }
         // Correspondencia no outro vetor
        a[atual] = caract[-read];
        return new String(a, atual, (36 - atual));
     }

  // Metodo main
  public static void main(String[] args) {
    // Indicacao de qual base esta a entrada padrao
    int base = Integer.parseInt(args[0]);
    // Enquanto a entrada padrao nao for vazia
    while (!StdIn.isEmpty()) {
      // Para valores invalidos de mudanca de base, sai do loop.
      if (base > 36 || base < 2) {
        StdOut.println("Nao sera possivel realizar a mudanca de base");
        break;
      }
      // Leitura da entrada
      String read = StdIn.readLine();
      // Conversao do valor da entrada para sua base equivalente
      int convert = Integer.parseInt(read, base);
      // Utiliza o convert para, de fato, passar para base 10.
      // Vale ressaltar que integerfromb devolve uma String
      String s = integerFromB(convert,10);
      StdOut.println(s);

    }
  }
}
