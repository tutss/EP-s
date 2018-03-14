import java.util.Arrays;

public class CountOccurrences {
	public void compareNumber(ArrayList<String> dict, String[] in) {
		int frequency = 0;
		for (int i = 0; i < in.length; i++) {
			if (in[i] == null) {
			  for (Object e : dict)
			    if (e == null) frequency++;
			}
			else {
				for (Object e : dict)
					if (in[i].equals(e)) frequency++;
			}
		System.out.println(frequency);
		}
	}
	
	
	public static void main(String[] args) {
		String[] dict = args[0];
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(dict));
		while (!StdIn.isEmpty()) {
			String[] in = readAllStrings();
			compareNumber(dict, in);
		}
	}
	
}