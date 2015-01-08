package test;

import java.util.Arrays;

/**
 * Created by qingcunz on 11/9/14.
 */
public class Equation {
    public static void main(String[] args) {
        int[] arr = {3,4,7,1,2,9,8};
        equal(arr);
    }

    // suppose no duplicate in the array
    public static void equal(int[] arr) {
        if(arr.length < 4)
            return;

        Arrays.sort(arr);

        for(int start = 0, end = arr.length-1; start < end-2; start++) {
            int i = start+1, j = end-1;

            int sum = arr[start]+arr[end];
            while(i < j) {
                if(arr[i]+arr[j] == sum) {
                    System.out.println(arr[start] + "\t" + arr[i] + "\t" + arr[j] + "\t" + arr[end]);
                    i++;
                    j--;
                } else if(arr[i] + arr[j] < sum) {
                    i++;
                } else {
                    j--;
                }
            }
        }
    }
}
