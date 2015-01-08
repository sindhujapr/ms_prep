package pattern.searching;

/*
 * http://www.geeksforgeeks.org/searching-for-patterns-set-3-rabin-karp-algorithm/
 */
public class PatternSearching {
    public static void main(String[] args) {
        search("XX TEST TEST", "TEST");
        search("AABAACAADAABAAABAA", "AABA");
    }
    
    public static void search(String source, String pattern) {
        int[] next = next(pattern);
        int slen = source.length(), plen = pattern.length();
        char[] s = source.toCharArray(), p = pattern.toCharArray();
        
        int i = 0, j = 0;
        while(i < slen) {
            while(i < slen && j < plen) {
                if(j == -1 || s[i] == p[j]) {
                    i++;
                    j++;
                } else {
                    j = next[j];
                }
            }
            
            if(j == plen)
                System.out.println(source.substring(i-plen) + ": " + (i-plen));
            
            if(i == slen)
                break;

            i = i-plen+1;
            j = 0;
        }
    }

    private static int[] next(String pattern) {
        assert pattern != null;

        int n = pattern.length();
        char[] p = pattern.toCharArray();
        int[] next = new int[n];
        next[0] = -1;
        int i = 0, j = -1;
        while(i < n-1) {
            if(j == -1 || p[i] == p[j]) {
                i++;
                j++;
                if(p[i] == p[j])
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