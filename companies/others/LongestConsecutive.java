package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qingcunz on 10/5/14.
 */
public class LongestConsecutive {
    public static void main(String[] args) {
        System.out.println(new LongestConsecutive().longestConsecutive(new int[] {-2,-3,-3,7,-3,0,5,0,-8,-4,-1,2}));
    }

    public int longestConsecutive(int[] num) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        int maxLen = 0;
        for(int val : num) {
            if(map.containsKey(val))
                continue;

            if(map.containsKey(val-1) && map.containsKey(val+1)) {
                int left = map.get(val-1).get(0);
                int right = map.get(val+1).get(1);
                map.get(left).set(1, right);
                map.get(right).set(0, left);

                maxLen = Math.max(maxLen, right-left+1);
            } else if(map.containsKey(val-1)) {
                int left = map.get(val-1).get(0);
                map.get(left).set(1, val);
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(left);
                temp.add(val);
                map.put(val, temp);

                maxLen = Math.max(maxLen, val-left+1);
            } else if(map.containsKey(val+1)) {
                int right = map.get(val+1).get(1);
                map.get(right).set(0, val);

                List<Integer> temp = new ArrayList<Integer>();
                temp.add(val);
                temp.add(right);
                map.put(val, temp);

                maxLen = Math.max(maxLen, right-val+1);
            } else {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(val);
                temp.add(val);
                map.put(val, temp);
                maxLen = Math.max(maxLen, 1);
            }
        }

        return maxLen;
    }
}
