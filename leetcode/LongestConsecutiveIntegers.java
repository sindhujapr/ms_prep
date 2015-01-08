package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveIntegers {
    public static void main(String[] args) {
        int[] num = { 0, -1, 1, 2 };
        int max = new LongestConsecutiveIntegers().longestConsecutive(num);
        System.out.println(max);
    }

    public int longestConsecutive(int[] num) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < num.length; i++) {
            set.add(num[i]);
        }

        /*
         * for each loop we will remove all adjacent elements
         */
        int max = 1;
        for (int i = 0; i < num.length; i++) {
            int temp = num[i];
            int left = 1;
            while (set.contains(temp - left)) {
                set.remove(temp - left);
                left++;
            }
            int right = 1;
            while (set.contains(temp + right)) {
                set.remove(temp + right);
                right++;
            }
            max = Math.max(max, left + right - 1);
            set.remove(temp);
        }

        return max;
    }
}