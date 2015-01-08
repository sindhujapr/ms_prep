package lc;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeSum {
    public static void main(String[] args) {
        int[] num = { 0, 0, 0 };
        ArrayList<ArrayList<Integer>> result = new ThreeSum().threeSum(num);
        for(ArrayList<Integer> list : result)
            System.out.println(list);
    }

    /*
     * http://gongxuns.blogspot.com/2012/12/leetcode3sum.html
     * http://n00tc0d3r.blogspot.com/2013/01/2sum-3sum-4sum-and-variances.html
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num.length < 3)
            return res;

        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || num[i] > num[i - 1]) { // avoid duplicate solutions
                int j = i + 1, k = num.length - 1;

                while (j < k) {
                    if (num[j] + num[k] == -num[i]) {
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        temp.add(num[i]);
                        temp.add(num[j]);
                        temp.add(num[k]);
                        res.add(temp);
                        k--;
                        j++;
                        while (k > j && num[k] == num[k + 1])
                            k--;// avoid duplicate solutions

                        while (j < k && num[j] == num[j - 1])
                            j++;// avoid duplicate solutions

                    } else if (num[j] + num[k] > -num[i]) {
                        // if this is not the result, we don't care of the duplicate
                        k--;
                    } else {
                        j++;
                    }
                }
            }
        }
        return res;
    }
}
