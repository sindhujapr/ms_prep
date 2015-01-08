package lc2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestConsecutiveIntegers {
	public static void main(String[] args) {
		int[] num = { 1,2, 5, 4, 3};
		int max = new LongestConsecutiveIntegers().longestConsecutive(num);
		System.out.println(max);
	}
	
    static class Pair{
        int start, end;
        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override public String toString() { return start + "," + end; }
    }
    
    /*
     * my latest code
     */
    public int longestConsecutive(int[] num) {
        Map<Integer, Pair> map = new HashMap<Integer, Pair>();
        
        int max = 0;
        for(int value : num) {
            if(map.containsKey(value))
                continue;
            
            if(map.containsKey(value-1) && map.containsKey(value+1)) {
                Pair pair1 = map.get(value-1);
                Pair pair2 = map.get(value+1);
                pair1.end = pair2.end;
                map.put(value, pair1);
                // we only need to update the pair of the last element!
                map.put(pair2.end, pair1);
            } else if(map.containsKey(value-1)) {
                Pair pair = map.get(value-1);
                pair.end = value;
                map.put(value, pair);
            } else if(map.containsKey(value+1)) {
                Pair pair = map.get(value+1);
                pair.start = value;
                map.put(value, pair);
            } else {
                Pair pair = new Pair(value, value);
                map.put(value, pair);
            }
            
            int left = map.get(value).start;
            int right = map.get(value).end;
            max = Math.max(max, right-left+1);
        }
        return max;
    }

    public int longestConsecutive2(int[] num) {
        Set<Integer> set = new HashSet<Integer>();
        for(int val : num)
            set.add(val);
            
        int max = 1;
        for(int i = 0; i < num.length; i++) {
            if(!set.contains(num[i]))
                continue;
                
            int left = num[i];
            while(set.contains(left-1)) {
                set.remove(left-1);
                left--;
            }
            
            int right = num[i];
            while(set.contains(right+1)) {
                set.remove(right+1);
                right++;
            }
            
            set.remove(num[i]);
            max = Math.max(right-left+1, max);
        }
        return max;
    }
}
