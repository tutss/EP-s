 public class Wc
 {
     public static void main(String[] args)
     {
	 int line = 0;
	 int word = 0;
	 String arq = StdIn.readAll();
	 int ch = arq.length();

	 for (int loop = 0; loop < arq.length() - 1; loop++)
	     {	 
		 char seq = arq.charAt(loop);
	       	 char next = arq.charAt(loop + 1);
       		 if (seq == '\n') line++;
		 if (!Character.isWhitespace(seq) && Character.isWhitespace(next)) word++;

	     }

	 if (ch != 0) line++;
	 System.out.print(line + " " + word + " " + ch);
	 System.out.println();
     }
 } 
