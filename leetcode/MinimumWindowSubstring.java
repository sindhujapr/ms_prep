package lc;

import java.util.HashMap;
import java.util.Map;

// http://www.cnblogs.com/lichen782/p/leetcode_minimum_window_substring_3.html
public class MinimumWindowSubstring {
    public static void main(String[] args) {
//      System.out.println(new MinimumWindowSubstring().minWindow1("a", "b"));
        
        Map<Integer, Double> map = null;
        if (map == null || map.isEmpty()) 
            map = new HashMap<Integer, Double>();
    }
    
    // my latest implementation based on the first version below
    public String minWindow1(String S, String T) {
        if(S == null || T == null) 
            return "";
        
        int m = S.length(), n = T.length();
        int[] toBeFound = new int[1 << 8];
        for(int i = 0; i < n; i++)
            toBeFound[T.charAt(i)]++;
        
        int[] hasFound = new int[1 << 8];
        String minStr = "";
        for(int start = 0, end = 0, count = 0; end < m; end++) {
            char ch = S.charAt(end);
            if(toBeFound[ch] == 0)
                continue;
            
            // order matters
            if(hasFound[ch] < toBeFound[ch])
                count++;
            hasFound[ch]++;
            
            /*
             * count will not be reset after reaching n.
             */
            if(count == n) {
                ch = S.charAt(start);
                while(toBeFound[ch] == 0 || hasFound[ch] > toBeFound[ch]) {
                    if(hasFound[ch] > toBeFound[ch])
                        hasFound[ch]--;
                    ch = S.charAt(++start);
                }
                
                if(minStr.length() == 0 || minStr.length() > (end-start+1))
                    minStr = S.substring(start, end+1);
            }
        }
        return minStr;
    }
    
    /*
     * http://discuss.leetcode.com/questions/97/minimum-window-substring
     */
    public String minWindow(String S, String T) {
        int sLen = S.length();
        int tLen = T.length();
        int needToFind[] = new int[256];

        for (int i = 0; i < tLen; i++)
            needToFind[T.charAt(i)]++;

        int hasFound[] = new int[256];
        int count = 0;
        
        int minBegin = -1;
        int minEnd = -1;
        for (int begin = 0, end = 0; end < sLen; end++) {
            char ch = S.charAt(end);
            if (needToFind[ch] == 0)
                continue;
            hasFound[ch]++;
            /*
             * trick here: count only indicates there are enough characters found,
             * but may not be enough for each character needs to be found. 
             * Since we increased hasFound[ch] above, we have to include '=' below.
             */
            if (hasFound[ch] <= needToFind[ch])
                count++;

            if (count == tLen) {
                ch = S.charAt(begin);
                while (needToFind[ch] == 0 || hasFound[ch] > needToFind[ch]) {
                    if (hasFound[ch] > needToFind[ch])
                        hasFound[ch]--;
                    ch = S.charAt(++begin);
                }

                int windowLen = end - begin + 1;
                if (minBegin == -1 || windowLen < (minEnd-minBegin+1)) {
                    minBegin = begin;
                    minEnd = end;
                }
            }
        }

        return minBegin == -1 ? "" : S.substring(minBegin, minEnd+1);
    }
}
