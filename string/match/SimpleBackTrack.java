package string.match;

/*
 * http://blog.csdn.net/v_JULY_v/article/details/6545192
 */
public class SimpleBackTrack {
    /*
     * find the first position that pattern occurs in source
     */
    public int backTrack(String source, String pattern) {
    int idx1 = 0, idx2 = 0;
    int slen = source.length();
    int plen = pattern.length();

    while(idx1 < slen && idx2 < plen) {
        char s = source.charAt(idx1);
        char p = pattern.charAt(idx2);
        if(s == p) {
        idx1++;
        idx2++;
        } else {
        idx1 = idx1-idx2+1;
        /*
         * Optimization: not enough chars left in the source
         */
        if(slen-idx1 < plen)
            return -1;
        idx2 = 0;
        }
    }
    
    /*
     * if idx2 has chance to increase to pattern.length(), all chars
     * in the pattern are matched
     */
    if(idx2 == pattern.length()) {
        return idx1-idx2;
    } else {
        return -1;
    }
    }
    
    public static void main(String[] args) {
    SimpleBackTrack instance = new SimpleBackTrack();
    System.out.println(instance.backTrack("fcc", "ccp"));
    }
}