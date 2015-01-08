package test;

import java.util.Arrays;

/**
 * Created by qingcunz on 11/22/14.
 */
public class IsPalindrome {
    public static void main(String[] args) {
        IsPalindrome ins = new IsPalindrome();
//        System.out.println(ins.isPalindrome(10));

        int[] A = new int[1];
        int[] B = new int[1];
        B[0] = 1;
        ins.merge(A, 0, B, 1);
        System.out.println(Arrays.toString(A));
    }

    public void merge(int A[], int m, int B[], int n) {
        for(int i = m+n-1, j = m-1; j >= 0; i--, j--)
            A[i] = A[j];

        for(int i = 0, j = n, k = 0; j < m+n || k < n; i++) {
            if(j == m+n)
                A[i] = A[k++];
            else if(k == n)
                A[i] = A[j++];
            else {
                if(A[j] < A[k])
                    A[i] = A[j++];
                else
                    A[i] = A[k++];
            }
        }
    }
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;

        int len = 0;
        int y = x;
        while(y > 0) {
            y /= 10;
            len++;
        }

        for(int i = 0; i < len/2; i++) {
            if(bit(x, i) != bit(x, len-i-1))
                return false;
        }
        return true;
    }

    // return the bit at bit position i [0, len-1]
    private int bit(int val, int i) {
        while(i-- > 0)
            val /= 10;
        return val % 10;
    }
}
