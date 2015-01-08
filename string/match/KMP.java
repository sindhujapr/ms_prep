package string.match;

import java.util.Arrays;

/*
 * http://blog.csdn.net/v_JULY_v/article/details/6545192
 * http://blog.csdn.net/v_july_v/article/details/7041827
 */
public class KMP {
    public void KMP1(String source, String pattern) {
        int[] next = KMP1_next(pattern);

        int i = 0, j = 0;
        int slen = source.length();
        int plen = pattern.length();

        while (i < slen && j < plen) {
            if (j == -1 || source.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        /* no need to be (j >= plen) */
        if (j == plen) {
            System.out.println("KMP1 found: " + (i - plen));
        } else {
            System.out.println("KMP1 didn't found a match");
        }
    }

    /*
     * used by backTrack1()
     * http://www.crimx.com/2014/09/07/kmp-algorithm/
     */
    private int[] KMP1_next(String pattern) {
        int plen = pattern.length();
        int[] next = new int[plen];
        // avoid next[0] out of array index bound
        if(plen == 0)
            return next;

        int i = 0;
        /*
         * initialize the value as -1 to indicate that we need to update the
         * position of the source string in case of mismatch. see KMP1.
         */
        next[i] = -1;
        int j = -1;
        while (i < plen - 1) {
            /*
             * j == -1 is a special case in that the index of main string
             * needs to be updated (by one)
             */
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                ++i;
                ++j;

                /*
                 * make sure pattern[j] != pattern[next[j]]. If the two equals,
                 * we don't move backward. For example, "aab", if mismatch
                 * happens for the second 'a' (with, eg, 'c'), then the first
                 * 'a' should also be ignored. This is ensured by the "else"
                 * clause. Generally, this is for performance improvement.
                 */
                if (pattern.charAt(i) != pattern.charAt(j))
                    next[i] = j;
                else
                    next[i] = next[j];
            } else {
                /*
                 * Apply the same idea iteratively:
                 * if pattern[i] != pattern[j], then we have pattern[i-j, i-1] = pattern[0, j-1]
                 * thus we need to move back j to next[j], which has been calculated, so that
                 * we have pattern[0, next[j]-1] == pattern[i-next[j], i-1].
                 */
                j = next[j];
            }
        }

        System.out.println("KMP1 next: " + Arrays.toString(next));
        return next;
    }

    /*
     * This algorithm is intuitive but performance isn't good, especially when
     * computing the next indices
     */
    public void KMP2(String source, String pattern) {
        System.out.println(source + "\t" + pattern);
        int i = 0;
        int slen = source.length();
        int plen = pattern.length();

        while (i < slen) {
            int j;
            for (j = 0; (i + j) < slen && j < plen && source.charAt(i + j) == pattern.charAt(j); j++)
                ;

            if (j == plen)
                System.out.println("KMP2 found: " + i);
            i = i + max(1, j - overlap(pattern.substring(0, j), pattern));
        }
    }

    private int max(int val1, int val2) {
        return val1 > val2 ? val1 : val2;
    }

    private int overlap(String str1, String str2) {
        for (int i = 1; i < str1.length(); i++) {
            String suffix = str1.substring(i);
            if (str2.startsWith(suffix) && str2.length() != suffix.length())
                return str2.length();
        }
        return 0;
    }

    /*
     * described in https://www.ics.uci.edu/~eppstein/161/960227.html
     */
    public void KMP3(String source, String pattern) {
        int[] next = KMP3_next(pattern);

        int slen = source.length();
        int j = 0;
        for (int i = 0; i < slen; i++) {
            for (;;) {
                if (source.charAt(i) == pattern.charAt(j)) {
                    // yes, move on to next state
                    j++;
                    if (j == pattern.length()) {
                        System.out.println("KMP3 found: " + (i - j + 1));
                        j = next[j];
                    }
                    // jump out of inner loop
                    break;
                } else if (j == 0) {
                    // no match in state j=0, give up
                    break;
                } else {
                    // try shorter partial match
                    j = next[j];
                }
            } // for
        }
    }

    private int[] KMP3_next(String pattern) {
        int plen = pattern.length();
        /*
         * the array needs to be a little bigger since when (i == plen-1), when
         * will calculate next[i+1]
         */
        int[] next = new int[plen + 1];
        next[0] = -1;

        for (int i = 0; i < plen; i++) {
            next[i + 1] = next[i] + 1;
            while (next[i + 1] > 0 && pattern.charAt(i) != pattern.charAt(next[i + 1] - 1))
                next[i + 1] = next[next[i + 1] - 1] + 1;
        }

        System.out.println("KMP3 next: " + Arrays.toString(next));
        return next;
    }

    public static void main(String[] args) {
        KMP instance = new KMP();
        instance.KMP1("abacababc", "abab");
        instance.KMP1("banananobano", "nano");
        instance.KMP1("banananobano", "ababc");

//      instance.KMP3("abacababc", "abab");
//      instance.KMP3("banananobano", "nano");
    }
}
