package test;

/**
 * Created by qingcunz on 10/16/14.
 */
public class QuickSelection {
    public static void main(String[] args) {
        int[] A = {3, 5, 1, 4, 6, 2, 9, 7, 8};
//        qsort(A, 0, A.length-1);
//        for(int val : A)
//            System.out.print(val + "\t");
        for(int i = 1; i <= A.length; i++)
            System.out.println(quickSelection(A, i));
    }

    public static void qsort(int[] A, int a, int b) {
        if(a >= b)
            return;

        int j = a;
        for(int i = a; i <= b; i++)
            if(A[i] < A[a])
                swap(A, i, ++j);
        swap(A, a, j);

        qsort(A, a, j-1);
        qsort(A, j+1, b);
    }

    public static int quickSelection(int[] A, int index) {
        int start = 0, end = A.length-1;
        while(start <= end) {
            int j = start;
            for(int i = start; i <= end; i++)
                if(A[i] < A[start])
                    swap(A, i, ++j);
            swap(A, start, j);

            if(j == index-1)
                return A[j];
            else if(j > index-1)
                end = j-1;
            else
                start = j+1;
        }
        return -1;
    }

    private static void swap(int[] A, int i, int j) {
        if(i == j)
            return;
        A[i] ^= A[j];
        A[j] ^= A[i];
        A[i] ^= A[j];
    }
}
