package test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You have an array of 0s and 1s and you want to output all the intervals (i, j) where the number of 0s and numbers of 1s are equal.
 * Example
 * pos = 0 1 2 3 4 5 6 7 8
 * 0 1 0 0 1 1 1 1 0
 * One interval is (0, 1) because there the number of 0 and 1 are equal. There are many other intervals, find all of them in linear time.
 */
public class CountZeroOne {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 0, 1, 1, 1, 1, 0};
        count(arr);
    }

    // this is wrong implementation
    public static void count(int[] arr) {
        Map<Integer, Integer> map0 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();

        int count0 = 0;
        int count1 = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) {
                map0.put(++count0, i);
            } else {
                map1.put(++count1, i);
            }
        }

        for(int count : map0.keySet()) {
            if(map1.containsKey(count)) {
                int pos0 = map0.get(count);
                int pos1 = map1.get(count);
                System.out.println(count + ":\t" + Math.min(pos0, pos1) + "\t" + Math.max(pos0, pos1));
            }
        }
    }
}
