package test;

import java.util.Arrays;

/**
 * Created by qingcunz on 11/15/14.
 */
public class ThreeSum {
    // allow duplicate
    public static void threeSum(int[] num) {
        if(num == null || num.length == 0)
            return;

        Arrays.sort(num);
        for(int i = 0; i < num.length; i++) {
            int j = i, k = num.length-1;
            while(j <= k) {
                int sum = num[i]+ num[j] + num[k];
                if(sum == 0) {
                    System.out.println(num[i]+"\t" + num[j] + "\t"+num[k]);
                    j++;
                    k--;
                } else if(sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
    }

    public static void main(String[] args) {
        threeSum(new int[]{-1, 0, 1, 2});
    }
}
