import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class teste {

  /*  public static void findArray (String[] order, String[] input) {
      ArrayList<String> seqAn = new ArrayList<String>();
      boolean[] checkAn = new boolean[input.length];
      int size = order.length - 1;
      //Arrays.sort(input);
      int counteri = 0;
      for (int i = 0; i < order.length; i++) {
        int counter = 0;
        for (int j = i; j < order.length; j++) {
            if (order[i].length() == order[j].length()) {
          //  System.out.println("Tamanho igual: " + order[i]+ " " + order[i].length() + " e " + order[j] + " " + order[j].length());
              checkAn[i] = findAnagram(order[i], order[j]);
              //System.out.println("Estas palavras aparecem de tamanho igual: "+ order[i] +" e " + order[j]);
            // Verificar se isto esta correto.
            System.out.println("Palavra: " + order[i] + " e " + order[j]);
            System.out.println("Elas são anagramas: " + checkAn[i]);
              if (checkAn[i]) {
                //seqAn.add(input[i]);
                 seqAn.add(input[j]);
              }
              counter++;
            }
            if (counter == size && seqAn.size() > 1)
              System.out.println("* " + seqAn + " ");
        }
        //noDups(seqAn);
        //if (seqAn.size() > 1)
        //  System.out.println("* " + seqAn + " ");
        //seqAn.clear();
      }

    }*/

    public static void noDups(ArrayList<String> seqAn) {
      LinkedHashSet<String> set = new LinkedHashSet<String>();
      set.addAll(seqAn);
      seqAn.clear();
      seqAn.addAll(set);
    }

    // Verifica se existe anagrama entre duas palavras
    public static boolean findAnagram(String s, String t) {
      char[] sarray = s.toLowerCase().trim().toCharArray();
      char[] tarray = t.toLowerCase().trim().toCharArray();
      Arrays.sort(sarray);
      Arrays.sort(tarray);
      if (String.valueOf(sarray).equals(String.valueOf(tarray)))
        return true;
      return false;
    }

    public static void charOrder(String[] input, String[] order) {
      for (int i = 0; i < input.length; i++) {
        char[] c = input[i].toLowerCase().trim().toCharArray();
        Arrays.sort(c);
        order[i] = new String(c);
      }
      for (int i = 0; i < order.length; i++) {
        order[i] = order[i] + "+" + i;
      }
      Arrays.sort(order);
    }

    private static void printAnagram(ArrayList<String> printable) {
      noDups(printable);
      if (printable.size() > 1) {
        System.out.print("* ");
        for (int i = 0; i < printable.size(); i++) {
          System.out.print(printable.get(i) + " ");
        }
        System.out.print("\n");
      }
    }

    public static void findImproved (String[] input, String[] order) {
      String[] verifyString = new String[2];
      String[] verifyNext = new String[2];
      ArrayList<String> printable = new ArrayList<String>();
      for (int i = 0; i < order.length - 1; i++) {
        verifyString = order[i].split("\\+");
        verifyNext = order[i+1].split("\\+");
        // System.out.println("order[" + i + "]: " + order[i]);
        // System.out.println("verifyString: " + verifyString[0] + " e " + verifyNext[0]);
        int localS = Integer.parseInt(verifyString[1]);
        int localN = Integer.parseInt(verifyNext[1]);
        if (findAnagram(verifyString[0], verifyNext[0])) {
          printable.add(input[localS]);
          printable.add(input[localN]);
          // printAnagram(input)
        }
        else {
          printAnagram(printable);
          printable.clear();
        }
        Arrays.fill(verifyString, null);
        Arrays.fill(verifyNext, null);
      }
    }

    public static void main(String[] args) {

      if (!StdIn.isEmpty()) {
        String[] input = StdIn.readAllStrings();
        String[] order = new String[input.length];
        charOrder(input, order);
        findImproved(input, order);
      }
      else
        System.out.println(" No input. ");
    }
}
