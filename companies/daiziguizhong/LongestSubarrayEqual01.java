package interview.daiziguizhong;

import java.util.HashMap;
import java.util.Map;

/*
 * http://chuansongme.com/n/122275
 */
public class LongestSubarrayEqual01 {
    public int maxLen(int[] num) {
        for(int i = 0; i < num.length; i++)
            if(num[i] == 0)
                num[i] = -1;
        
        // length is num.length+1!!!!
        int[] dp = new int[num.length+1];
        dp[0] = 0;
        for(int i = 1; i <= num.length; i++)
            dp[i] = dp[i-1] + num[i-1];
        
        /*
         * Keep the first index of a given sum.
         * 
         * we don't need to keep all positions for a given sum, only 
         * the earliest is enough.
         */
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int maxLen = 0;
        for(int i = 0; i <= num.length; i++) {
            if(map.containsKey(dp[i]))
                maxLen = Math.max(maxLen, i-map.get(dp[i]));
            else
                map.put(dp[i], i);
        }
        
        return maxLen;
    }
    
    public static void main(String[] args) {
        System.out.println(new LongestSubarrayEqual01().maxLen(new int[] {1,0,1,0,1,0,1,0}));
        System.out.println(new LongestSubarrayEqual01().maxLen(new int[] {1,1,0,1,0,0,0}));
        System.out.println(new LongestSubarrayEqual01().maxLen(new int[] {1,1,0,1,1,0,0}));
    }
}