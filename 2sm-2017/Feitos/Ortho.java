public class Ortho {
  
  // Recursion call
  public static int search(String key, String[] a) {
    return search(key, a, 0, a.length);
  }
  // Binary Search
  public static int search(String key, String[] a, int lo, int hi) {
    if (hi <= lo) return -1;
    int mid = lo + (hi - lo)/2;
    int cmp = a[mid].compareTo(key);
    if      (cmp > 0) return search(key, a, lo, mid);
    else if (cmp < 0) return search(key, a, mid+1, hi);
    else              return mid;   
  }

  public static void main(String[] args) {
  	In src = new In(args[0]);
    String srcContent = src.readAll();
    String[] splitSrc = srcContent.split("\\s+");
    while (!StdIn.isEmpty()) {
      // Regular expression for splitting
      String regex = "[^a-zA-Z]+";
      String key = StdIn.readLine();
      String[] keySplit = key.split(regex);
      for (int i = 0; i < keySplit.length; i++) {         
        if (keySplit[i].length() > 0) {
          String keyWord = keySplit[i];
          int counter = 0;
          // Check if key its different from the words in the dictionary                      
          if (search(keyWord, splitSrc) < 0 
            && search(keyWord.toLowerCase(), splitSrc) < 0 
            && search(keyWord.toUpperCase(), splitSrc) < 0) counter = 1;
          if (counter == 1) {
            if (args.length <= 1) StdOut.println(keyWord);
            if (args.length > 1) {
              String show = "*" + keyWord + "*"; 
              key = key.replace(keyWord, show);
            }
          }
        }      
      }
      if (args.length > 1) StdOut.println(key);
    }
  }
}
