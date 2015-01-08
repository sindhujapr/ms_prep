package lc;

public class WildcardMatching {
    public static void main(String[] args) {
        WildcardMatching instance = new WildcardMatching();
        System.out.println(instance.isMatch("hi", "*abc"));
    }

    /*
     * Greedy:
     * http://blog.sina.com.cn/s/blog_b9285de20101gw2x.html
     * There is still possible improvement: we can combine this
     * code with KMP, thus when mismatch happens and we need to use
     * a previous wildcard, we don't need to use it to match each
     * character in s. For example, s = "abeaba" and p = "*aba".
     * When "aba" doesn't match "abe", we don't need to use * to compare
     * each character in "abe". Instead, we could jump directly to the
     * next occurrence of 'a'.
     */
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        int i = 0;
        int j = 0;
        int star = -1;
        int sp = 0;

        while (i < n) {
            // Compact multiple asterisks into one.
            while (j < m && p.charAt(j) == '*') {
                /*
                 * star and sp are used to record the position of the asterisk (in pattern)
                 * and the position of the letter it's used to match in the source. So that
                 * if there is a mismatch happens, we can use asterisk to match one more letter.
                 * This can be achieved by setting i to ++sp. But note that star doesn't change.
                 */
                star = j++; // * match 0 character
                sp = i;
            }

            /*
             * no more letters in pattern but there are more letters (i < n) in the source,
             * or the current letters (i, j) don't match.
             */
            if (j == m || (p.charAt(j) != s.charAt(i) && p.charAt(j) != '?')) {
                // if there is no asterisk to backtrack, of course pattern and source don't match
                if (star < 0)
                    return false;
                else {
                    j = star + 1;
                    i = ++sp; // * match 1 character
                }
            } else {
                i++;
                j++;
            }
        }

        // ignore the asterisks at the end of the pattern
        while (j < m && p.charAt(j) == '*')
            j++;
        return j == m;
    }

    // time limit exceeded
    public boolean isMatch2(String s, String p) {
        assert s != null && p != null;
        
        if(s.length() == 0 && p.length() == 0)
            return true;
        else if(s.length() == 0) {
            for(int j = 0; j < p.length(); j++)
                if(p.charAt(j) != '*')
                    return false;
            return true;
        } else if(p.length() == 0)
            return false;
    
        int sn = s.length(), pn = p.length();
        if(s.charAt(sn-1) == p.charAt(pn-1) || p.charAt(pn-1) == '?')
            return isMatch(s.substring(0, sn-1), p.substring(0, pn-1));
        
        if(p.charAt(pn-1) == '*')
            return isMatch(s.substring(0, sn-1), p.substring(0, pn-1)) ||
                    isMatch(s, p.substring(0, pn-1));
        
        return false;
    }

    /*
     * memory limit exceeded
     */
    public boolean isMatch1(String s, String p) {
        boolean matrix[][] = new boolean[p.length() + 1][s.length() + 1];
        matrix[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && matrix[i][0])
                matrix[i + 1][0] = true;
            else
                matrix[i + 1][0] = false;
        }

        for (int i = 0; i < s.length(); i++)
            matrix[0][i + 1] = false;

        for (int i = 0; i < p.length(); i++) {
            char ch1 = p.charAt(i);
            for (int j = 0; j < s.length(); j++) {
                char ch2 = s.charAt(j);
                if (ch2 == ch1 || ch1 == '?')
                    matrix[i + 1][j + 1] = matrix[i][j];
                else if (ch1 == '*')
                    matrix[i + 1][j + 1] = matrix[i + 1][j] || matrix[i][j]
                            || matrix[i][j + 1];
            }
        }

        return matrix[p.length()][s.length()];
    }
}