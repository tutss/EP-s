/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa:
  Data: //2017
  Baseado em ... (breve e se aplicável)
  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA.
 ******************************************************************************/


public class LParens {

  public static void main(String[] args) {

    // Stacks inicializadas e para manuseio.
    Stack<String> chars = new Stack<String>();
    Stack<String> numbs = new Stack<String>();
    Stack<String> values = new Stack<String>();

    // Garantia de que a stack não fique vazia.
    for (int i = 0; i < 200; i++) {
      chars.push("");
    }

    // Loop para leitura e análise dos simbolos.
    // A entrada deve conter espaçamento.
    while (!StdIn.isEmpty()) {
      String str = StdIn.readString();
      if      (str.equals("+"))      chars.push(str);
      else if (str.equals("-"))      chars.push(str);
      else if (str.equals("*"))      chars.push(str);
      else if (str.equals("/"))      chars.push(str);
      else if (str.equals(" "))                     ;
      else if (str.equals(")")) {
        String fop = chars.pop();
        String fN = numbs.pop();
        String sN = numbs.pop();
        String temp = "( " + sN + " " + fop + " " + fN + " )";
        numbs.push(temp);
       }
       else numbs.push(str);
    }

    // Preferi criar uma stack auxiliar para evitar confusões
    // na stack principal.
    for (String i : numbs) {
      values.push(numbs.pop());
    }

    /* Printa os valores na tela.                                      *
    * OBS: O chars.pop() é para concatenar os dois últimos valores da  *
    * forma correta.                                                   */
    StdOut.print(values.pop() + "" + chars.pop());
    while (!values.isEmpty()) {
      StdOut.print(values.pop());
    }

  }
}
