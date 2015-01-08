package lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistinctSubsequences {
    public static void main(String[] args) {
        DistinctSubsequences instance = new DistinctSubsequences();
        System.out.println(instance.numDistinct2("rabbbitr", "rabbit"));
        System.out.println(instance.numDistinct2("abab", "ab"));
    }
    
    // my latest code as of sep/2014 and it's much easier to understand
    public int numDistinct(String S, String T) {
        int m = S.length(), n = T.length();
        // not necessary but it's an optimization
        if(m < n)
            return 0;
            
        int[][] count = new int[m+1][n+1];
        for(int i = 0; i <= m; i++)
            count[i][0] = 1;
                                                        
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                /*
                 * if the current characters match, then we have two choices:
                 * 1. Use the current character in T, which is count[i-1][j]
                 * 2. Don't use the current character in T, which is count[i-1][j-1]
                 * In either case, the current character in S is used
                 *
                 * if the current characters don't match, then we need to use
                 * S.substring(0, i) to match T
                 */
                if(S.charAt(i-1) == T.charAt(j-1)) {
                    count[i][j] = count[i-1][j-1] + count[i-1][j];
                } else {
                    count[i][j] = count[i-1][j];
                }
            }
        }
        
        return count[m][n];
    }

    /*
     * Dynamic programming:
     * 
     * http://blog.unieagle.net/2012/12/15/leetcode-problemdistinct-subsequences/
     */
    public int numDistinct2(String S, String T) {
        if(S.length() < T.length())
            return 0;
            
        int[] last = new int[S.length()+1];
        for(int i = 0; i < last.length; i++)
            last[i] = 1;
        
        for(int i = 1; i <= T.length(); i++) {
            int[] temp = new int[S.length()+1];
            /*
             * special handling for character S.charAt(i-1):
             * the value of temp[i] could only be 1 or 0.
             */
            temp[i] = T.charAt(i-1) == S.charAt(i-1) ? last[i-1] : 0;

            for(int j = i+1; j <= S.length(); j++) {
                temp[j] = temp[j-1];
                temp[j] += T.charAt(i-1) == S.charAt(j-1) ? last[j-1] : 0;
            }
            
            last = temp;
        }
        
        return last[last.length-1];
    }
    
    public int numDistinct1(String S, String T) {
        Map<Character, List<Integer>> map = new HashMap<Character, List<Integer>>();

        for (int i = 0; i < T.length(); i++) {
            Character ch = T.charAt(i);
            
            if (map.containsKey(ch)) {
                map.get(ch).add(i);
            } else {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                map.put(ch, temp);
            }
        }

        int[] res = new int[T.length() + 1];
        res[0] = 1;

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (map.containsKey(c)) {
                List<Integer> temp = map.get(c);
                int[] old = new int[temp.size()];
                for (int j = 0; j < temp.size(); j++)
                    old[j] = res[temp.get(j)];
                for (int j = 0; j < temp.size(); j++)
                    res[temp.get(j) + 1] += old[j];
            }
        }
        return res[T.length()];
    }
}
