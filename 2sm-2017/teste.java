import java.util.*;
public class teste {
  
  // Chamada da recursao
  public static int search(String key, String[] a) {
    return search(key, a, 0, a.length);
  }
  // Implementacao da busca binaria
  public static int search(String key, String[] a, int lo, int hi) {
    if (hi <= lo) return -1;
    int mid = lo + (hi - lo)/2;
    int cmp = a[mid].compareTo(key);
    //if (a[mid].equalsIgnoreCase(key)) { cmp = 0; return 1; }
    if      (cmp > 0) return search(key, a, lo, mid);
    else if (cmp < 0) return search(key, a, mid+1, hi);
    else              return mid;   
  }

  public static void main(String[] args) {
  	In src = new In(args[0]);
    String srcContent = src.readAll();
    String[] splitSrc = srcContent.split("\\s+");
    List<String> markedWords = new ArrayList<String>();
    while (!StdIn.isEmpty()) {
      String regex = "[^a-zA-Z]+";
      String key = StdIn.readLine();
      String[] keySplit = key.split(regex);
      for (int i = 0; i < keySplit.length; i++) {         
        if (keySplit[i].length() > 0) {
           String keyWord = keySplit[i];
           int counter = 0;            
           if (search(keyWord, splitSrc) < 0 
            && search(keyWord.toLowerCase(), splitSrc) < 0 
            && search(keyWord.toUpperCase(), splitSrc) < 0) counter = 1;
           if (counter == 1) {
            markedWords.add(keyWord);
            if (args.length <= 1) StdOut.println(keyWord);
           }
        }      
      }
     /* for (int i = 0; i < markedWords.size(); i++) {
        String sub = "*" + markedWords.get(i) + "*";
        String marked = key.replaceAll(markedWords.get(i), sub);
        StdOut.println(marked);
      }*/
    }
  }
}
