/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa: EstadãoHeadlines - Manchetes
  Data: 21/08/2017

  É necessário importar a biblioteca org.apache.commons.lang3.StringEscapeUtils
  (commons-lang3.jar) para executar este programa. Também, é necessário a
  biblioteca stdlib.jar.

  Exemplo de execução: http://www.estadao.com.br > estadao.html
                       URL > arquivo ou APENAS a URL.

  A URL do Estadão está completamente funcional.

  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA.
 ******************************************************************************/

import org.apache.commons.lang3.StringEscapeUtils;

public class EstadaoHeadlines {

    private static String readHTML(String URL) {
        In page = new In(URL);
        String html = page.readAll();
        if (html.contains("<title></title>")) return null;
        else return html;
    }

    public static void main(String[] args) {
        String URL = args[0];
	    String html = readHTML(URL);
	    /* i possui valor arbitrário. (A página do
	    Estadão possui ao máximo, 39 headers de 3a categoria) */
	    int i = 100;
	    int where = 0;
	      
	    /* O 3o header comeca quando encontrar <h3
	    e independente das especificacoes internas,
        acaba em >. Ele acaba em </h3>.
	    Leia o que está dentro, imprima, e
	    comece o processo novamente a partir do outro
	    fim. */
	      while (i > 0) {
	    	int start = html.indexOf("<h3", where);
	    	if (start == -1) break;
	  	    int from = html.indexOf(">", start);
	  	    if (from == -1) break;
	  	    int to = html.indexOf("</h3>", from);
	  	    where = to;
	  	    String head = html.substring(from + 1, to);
	  	    StdOut.println(StringEscapeUtils.unescapeHtml4(head));
	  	    i--;
	      }
    }
}
