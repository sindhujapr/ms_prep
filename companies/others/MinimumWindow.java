package test;

/**
 * Created by qingcunz on 10/24/14.
 */
public class MinimumWindow {
    // BDOBECODEBANC, ABC
    public int minWindow(String source, String pattern) {
        char[] s = source.toCharArray();
        char[] p = pattern.toCharArray();
        int[] toBeFound = new int[256];
        int[] hasFound = new int[256];

        for(char ch : p)
            toBeFound[ch]++;

        int total = 0;
        for(int i = 0; i < s.length; i++) {
            if(toBeFound[s[i]] == 0)
                continue;

            hasFound[s[i]]++;
            total++;

            if(total < p.length)
                continue;


        }

        return 0;
    }
}
