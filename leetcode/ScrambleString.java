package lc;

import java.util.Arrays;

public class ScrambleString {
    public static void main(String[] args) {
        System.out.println(new ScrambleString().isScramble("abc", "bca"));
    }

    /*
     * http://gongxuns.blogspot.com/2012/12/leetcode-scramble-string.html
     */
    public boolean isScramble(String s1, String s2) {
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        Arrays.sort(chs1);
        Arrays.sort(chs2);
        if(!Arrays.equals(chs1, chs2))
            return false;
        else if(chs1.length == 1)
            return true;
        
        for(int i = 1, n = s1.length(); i < n; i++) {
            if(isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            if(isScramble(s1.substring(0, i), s2.substring(n-i)) && isScramble(s1.substring(i), s2.substring(0, n-i)))
                return true;
        }
        return false;
    }
}
