package lc;

import java.util.Arrays;

public class SortColors {
	/*
	 * another implementation but slightly different:
	 * http://gongxuns.blogspot.com/2012/12/sort-colors-given-array-with-n-colored.html
	 */
    public void sortColors(int[] A) {
        if(A == null || A.length == 0)
            return;

        int i = 0, j = A.length-1;
        int cur = 0;
        while(cur <= j) {
            if(A[cur] == 0) {
                if(cur != i) {
                    swap(A, cur, i);
                    i++;
                } else {
                    i++;
                    cur++;
                }
            } else if(A[cur] == 1) {
                cur++;
            } else if(A[cur] == 2) {
                if(cur != j) {
                    swap(A, cur, j);
                    j--;
                } else {
                    break;
                }
            }
        }
    }
    
    private void swap(int[] A, int i, int j) {
        if(i == j)
            return;

        A[i] ^= A[j];
        A[j] ^= A[i];
        A[i] ^= A[j];
    }
}
