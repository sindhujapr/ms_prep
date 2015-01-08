package lc;

import java.util.Arrays;

public class NextPermutation {
    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 6, 5, 1};
        NextPermutation instance = new NextPermutation();
        instance.nextPermutation(array);
        System.out.println(Arrays.toString(array));
    }

    public void nextPermutation(int[] num) {
        int pos = num.length-1;
        while(pos > 0 && num[pos] <= num[pos-1])
            pos--;

        int j = pos-1;
        qsort(num, pos, num.length-1);

        if(j >= 0) {
            while(pos < num.length && num[pos] <= num[j])
                pos++;
            swap(num, j, pos);
        }
    }
    
    private void qsort(int[] num, int start, int end) {
        if(start >= end)
            return;
        
        int j = start;
        for(int i = start+1; i <= end; i++) {
            if(num[i] < num[start])
                swap(num, ++j, i);
        }
        swap(num, start, j);
        qsort(num, start, j-1);
        qsort(num, j+1, end);
    }
    
    public void nextPermutation1(int[] num) {
        if (num == null || num.length == 0 || num.length == 1)
            return;

        int i = num.length - 1;
        int j = num.length - 2;
        for (; j >= 0; j--) {
            boolean flag = false;
            for (i = num.length-1; i > j; i--) {
                if (num[i] > num[j]) {
                    swap(num, i, j);
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
        }

        for (int k = j + 1; k < (num.length + j + 1) >>> 1; k++) {
            int m = (num.length + j - k);
            swap(num, m, k);
        }
        return;

    }

    public void swap(int[] num, int i, int j) {
        if(i == j)
            return;
        num[i] ^= num[j];
        num[j] ^= num[i];
        num[i] ^= num[j];
    }
}
