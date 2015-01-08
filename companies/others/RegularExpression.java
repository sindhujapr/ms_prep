package test;

/**
 * Created by qingcunz on 11/2/14.
 */
public class RegularExpression {
    /*
     * s: abcdef
     * p: a.cde*f
     */
    public static boolean match(String s, int i, String p, int j) {
        if(s == null && p == null)
            return true;
        else if((s == null && p != null) || (s != null && p == null))
            return false;

        if(s.length() == i && p.length() == j)
            return true;

        if(p.charAt(i) == '*') {
            if(p.charAt(j-1) == s.charAt(i))
                return match(s, i-1, p, j-2) || match(s, i-1, p, j);
            else
                return match(s, i-1, p, j);
        }
        if(s.charAt(0) == p.charAt(0) && p.charAt(0) == '.')
            return match(s, i+1, p, j+1);

        return false;
    }
}
