public class Median2Sorted {
    public static Comparable select(Comparable[] a, Comparable[] b, int k) {
        // Assumindo que os dois arrays tem o mesmo tamanho
        int i = (int) Math.floor(k/2);
        int j = k - i;
        int step = (int) Math.floor(k/4);
        System.out.println("Analisando os valores: ");
        System.out.println("i = " + i + " | j = " + j + " | step = " + step);

        if (k < 0)
            return -1;

        while (step > 0) {
            if (a[i-1].compareTo(b[j-1]) > 0) {
                i = i - step;
                j = j + step;
            } else {
                i = i + step;
                j = j - step;
            }
            step = step/2;
        }

        System.out.println("Analisando os valores: ");
        System.out.println("i = " + i + " | j = " + j + " | step = " + step);
        if (i > a.length)
            i = i - j;
        if (a[i-1].compareTo(b[j-1]) > 0)
            return a[i-1];
        else
            return b[j-1];
    }

    public static void main(String[] args) {
        Comparable[] a = new Comparable[10];
        Comparable[] b = new Comparable[10];
        int kth = Integer.parseInt(args[0]);
        for (Integer i = 0; i < 10; i++) {
            a[i] = i;
            b[i] = (i+i) * 2;
        }

        System.out.println("Elementos de a: ");
        System.out.println("Tamanho de a: " + a.length);

        for (int i = 0; i < 10; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();

        System.out.println("Elementos de b: ");
        System.out.println("Tamanho de b: " + b.length);

        for (int i = 0; i < 10; i++) {
            System.out.print(" " + b[i]);
        }
        System.out.println();
        Comparable c = select(a, b, kth);
        System.out.println();
        System.out.println("O valor do k = " + kth);
        System.out.println("O kth é o número: " + c);
    }
}
