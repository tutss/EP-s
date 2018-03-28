/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa: UnionOfIntervals - Web Exercise 4.2.1 (Union of intervals)
  Data: 08/09/2017

  Nota: Como descrito na API, o método Arrays.sort() requere menos de n lg(n)
  comparações quando o vetor está parcialmente ordenado, e tem a performance de
  mergesort quando este está randomicamente ordenado.

  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA.
 ******************************************************************************/

import java.util.Arrays;
import java.util.ArrayList;

public class UnionOfIntervals {

    public static void draw(Interval1D[] interv, ArrayList<Interval1D> sInt, double much) {
      double min = 0, max = 1.3;
      double c = 1.05;
      double d = 0.003;
      StdDraw.setYscale(0, max);
      for (int i = 0; i < interv.length; i++) {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.line(interv[i].min(), min, interv[i].min(), max);
        StdDraw.line(interv[i].max(), min, interv[i].max(), max);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(interv[i].min(), interv[0].length()*c, interv[i].max(), interv[0].length()*c);
        c += 0.05;
      }
      StdDraw.setPenColor(StdDraw.RED);
      for (int j = 0; j < sInt.size(); j++) {
        Interval1D show = sInt.get(j);
        Interval1D maintain = sInt.get(0);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(show.min(), maintain.length()+d, show.max(), maintain.length()+d);
        d += 0.008;
      }
    }

    public static void main(String[] args) {
      while (!StdIn.isEmpty()) {
        double[] line = StdIn.readAllDoubles();
        Interval1D[] interv = new Interval1D[line.length/2];
        for (int i = 0; i < interv.length; i++) {
            interv[i] = new Interval1D(line[i*2], line[2*i+1]);
        }
        Arrays.sort(interv, Interval1D.MIN_ENDPOINT_ORDER);
        ArrayList<Interval1D> sInt = new ArrayList<Interval1D>();
        double max = interv[0].max();
        double min = interv[0].min();
        Interval1D check = new Interval1D(min, max);
        for (int i = 1; i < interv.length; i++) {
          if (check.intersects(interv[i])) {
            if (max < interv[i].max()) max = interv[i].max();
            check = new Interval1D(min, max);
          }
          else {
            sInt.add(new Interval1D(min, max));
            min = interv[i].min();
            max = interv[i].max();
            check = new Interval1D(min, max);
          }
          if (check.intersects(interv[i]) && i == interv.length-1) sInt.add(new Interval1D(min, max));
        }
        double much = 0;
        for (int i = 0; i <sInt.size(); i++) {
          much += sInt.get(i).length();
        }
        if (args.length > 0) {
          StdOut.println("Total length: " + much + " [" + sInt.size() + " components]");
          draw(interv, sInt, much);
          for (int i = 0; i < sInt.size(); i++) {
            StdOut.println(sInt.get(i));
          }
        }
        else { StdOut.println("Total length: " + much + " [" + sInt.size() + " components]");}
      }
    }
}
