package careercup.fb;

import java.util.HashMap;
import java.util.Map;

/*
 * http://www.careercup.com/question?id=12332722
 */
public class StartingPointOfStringConcatenation {
    public static void main(String[] args) {
        String[] strs = {"fooo", "barr", "wing", "ding", "wing"};
        String str = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        System.out.println(findFirst(str, strs));
        
        System.out.println(indexOf(str, "foo", 10));
    }
        
    /*
     * suppose the input is valid
     * 
     * 
     * all strings in the array are of the same length.
     */
    public static int findFirst(String source, String[] strs) {
        for(int i = 0; i < source.length(); i++) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            for(int j = 0; j < strs.length; j++) {
                if(map.containsKey(strs[j]))
                    map.put(strs[j], map.get(strs[j])+1);
                else
                    map.put(strs[j], 1);
            }

            int n = strs[0].length();
            int k;
            for(k = i; k < source.length() && k+n <= i + n * strs.length; k += n) {
                String str = source.substring(k, k+n);
                if(map.containsKey(str) && map.get(str) > 0) {
                    map.put(str, map.get(str)-1);
                } else {
                    break;
                }
            }
            
            if(k -i == n * strs.length)
                return i;
        }
        
        return -1;
    }
    
    
    public static int indexOf(String source, String pattern, int start) {
        assert source != null && pattern != null;
        
        int[] next = next(pattern);
        int m = source.length(), n = pattern.length();
        int i = start, j = 0;
        while(i < m && j < n) {
            if(j == -1 || source.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        
        if(j == n)
            return i-j;
        else
            return -1;
    }
    
    private static int[] next(String str) {
        assert str != null;
        
        char[] s = str.toCharArray();
        int n = str.length();
        int[] next = new int[n];
        next[0] = -1;
        int j = -1, i = 0;
        while(i < n-1) {
            if(j == -1 || s[i] == s[j]) {
                i++;
                j++;
                
                if(s[i] == s[j])
                    next[i] = next[j];
                else
                    next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }
}