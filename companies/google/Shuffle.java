
/*
 * http://www.glassdoor.com/Interview/Given-an-input-array-and-another-array-that-describes-a-new-index-for-each-element-mutate-the-input-array-so-that-each-ele-QTN_446534.htm
 * https://gist.github.com/mcsheffrey/6977999
 */
public class Shuffle {
   public static void permute(int[] a, int[] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            while (b[i] != i)
                swap(i, b[i], a, b);

            for (int j = 0; j < n; j++)
                System.out.print(a[j]);
            System.out.println();
        }
    }

    public static void swap(int i, int j, int[] a, int[] b) {
        int bt = b[j];
        b[j] = b[i];
        b[i] = bt;

        int at = a[j];
        a[j] = a[i];
        a[i] = at;
    }
}
