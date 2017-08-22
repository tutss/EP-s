public class Random {
	public static void main(String[] args) {
		double ran = Math.random();
		String alpha = "abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVXWYZ";
		int print = (int) (100000000 * ran);
	/*	int[] a = new int[9];
		for(int i = 0; i < 9; i++) {

		}  */
		String k = "";
		for (int i = 0; i < 10; i++) {
			double ran2 = Math.random();
			int size = (int) (52 * ran2);
			char c = alpha.charAt(size);
			k += c;
		}
	/*	for (int i = 0; i < 10; i++) {
			char b = k.charAt(i); 
			char n = print.charAt(i);
			System.out.println(b + "" + n);
		}  */
		System.out.print(print + " " + k);
		System.out.println();
	}
}