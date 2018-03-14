/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Artur Magalhães Rodrigues dos Santos
 * Numero USP: 10297734
 * Tarefa: ColorStudyH - Variante do Creative Ex. 3.1.33 (Color study)
 * Data: 12/08/2017
 *
 * Baseado em ColorStudy.java, HSB.java
 *
 * Exemplo: java -cp .:stdlib.jar ColorStudyH 0.0 0.33
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

 /* A variavel t, na linha 41, teve seu valor escolhido pela minha
 avaliacao no HSB.java, entretanto, o valor 21.0f foi obtido por meio de testes
 e observei que as imagens se comportaram melhor devido a esse valor. */

import java.awt.Color;

public class ColorStudyH {
    public static void main(String[] args) {
        float h1 = Float.parseFloat(args[0]);
        float h2 = Float.parseFloat(args[1]);
        StdDraw.setXscale(-1, 16);
        StdDraw.setYscale(-1, 16);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int gray = i*16 + (15-j);
                float sat = 0.95f;
                float bright = 0.95f;
                // Aplicacao do metodo da distancia
                float square = (float) Math.sqrt(i*i + j*j);
                float t = square/21.0f;
                // Interpolacao linear
                float hue = (1 - t)* h1 + t* h2;
                Color c1 = Color.getHSBColor(hue, sat, bright);
                Color c2 = new Color(gray, gray, gray);
                // Colore o fundo.
                StdDraw.setPenColor(c1);
                StdDraw.filledSquare(i, j, 0.5);
                //Pinta os quadrados menores, mudando a tonalidade.
                StdDraw.setPenColor(c2);
                StdDraw.filledSquare(i, j, 0.25);
            }
        }
        // Salva a imagem conforme as especificacoes.
        StdDraw.save("study-" + h1 + "-" + h2 + ".png");
    }
}
