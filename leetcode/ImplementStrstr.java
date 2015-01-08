package lc;

public class ImplementStrstr {
    public static void main(String[] args) {
        System.out.println(new ImplementStrstr().strStr("abacabad", "caab"));
    }
    /*
     * code copied from KMP.java
     */
    public String strStr(String haystack, String needle) {
        assert haystack != null && needle != null;
        if(needle.length() == 0)
            return haystack;
            
        int[] next = KMP1_next(needle);

        int i = 0, j = 0;
        int slen = haystack.length();
        int plen = needle.length();

        while (i < slen && j < plen) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == plen) {
            return haystack.substring(i-plen);
        } else {
            return null;
        }
    }
    
    private int[] KMP1_next(String pattern) {
        int plen = pattern.length();
        int[] next = new int[plen];

        int i = 0;
        next[i] = -1; 
        int j = -1; 
        while (i < plen - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                ++i;
                ++j;

                if (pattern.charAt(i) != pattern.charAt(j))
                    next[i] = j;
                else
                    next[i] = next[j];
            } else {
                j = next[j];
            }   
        }   

        return next;
    }
    
    /*
     * Naive implementation but it passes judge large
     * http://gongxuns.blogspot.com/2012/12/leetcodeimplement-strstr.html
     */
    public String strStr1(String haystack, String needle) {
        assert(haystack!=null && needle!=null);
        if(needle.length()==0) return haystack;
        
        int i=0;
        while(i<haystack.length()){
            if(haystack.length()-i<needle.length()) 
                break;
            if(haystack.charAt(i)==needle.charAt(0)){
                int j=i;
                while(j-i<needle.length() && haystack.charAt(j)==needle.charAt(j-i))
                    j++;
                if(j-i==needle.length()) 
                    return haystack.substring(i);
            }
            i++;
        }
        return null;
    }
}