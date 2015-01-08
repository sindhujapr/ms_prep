package string.match;

import java.util.Arrays;

/*
 * http://www.cs.utexas.edu/~moore/best-ideas/string-searching/fstrpos-example.html
 * http://www.ruanyifeng.com/blog/2013/05/boyer-moore_string_search_algorithm.html
 * http://www.stoimen.com/blog/2012/04/17/computer-algorithms-boyer-moore-string-search-and-matching/
 * http://blog.csdn.net/v_JULY_v/article/details/6545192
 */
public class BnM {
    public int BnM1(String src, String pattern) {
    int slen = src.length();
    int plen = pattern.length();

    int s_idx = plen, p_idx;
    if (plen == 0 || slen == 0)
        System.out.println("Invalid input");

    while (s_idx <= slen) {
        p_idx = plen;
        while (src.charAt(--s_idx) == pattern.charAt(--p_idx)) {
        if (p_idx == 0) {
            return s_idx;
        }
        }
        s_idx += (plen - p_idx) + 1;
    }

    return -1;
    }

    public int BnM2(String source, String pattern) {
    int[] shift = buildBadCharShift(pattern);
    int plen = pattern.length();
    int slen = source.length();

    int s_idx = plen, p_idx;
    int skip_stride;
    if (plen == 0)
        return -1;

    while (s_idx <= slen) {
        p_idx = plen;
        while (source.charAt(--s_idx) == pattern.charAt(--p_idx)) {
        if (p_idx == 0) {
            return s_idx;
        }
        }

        skip_stride = shift[(int) source.charAt(s_idx)];
        s_idx += ((skip_stride > (plen - p_idx)) ? skip_stride
            : (plen - p_idx)) + 1;
    }

    return -1;
    }

    /*
     * we need to build an array of offsets. when mismatch happens between chars
     * from source and from pattern, we need to move the index of the source by
     * <offset> such that source[<index>] == pattern[<biggest index of char
     * source[<index>] in pattern]
     */
    private int[] buildBadCharShift(String pattern) {
    int[] shift = new int[256];
    int plen = pattern.length();

    for (int i = 0; i < 256; i++)
        shift[i] = plen;

    while (plen > 0) {
        int index = (int) pattern.charAt(--plen);
        shift[index] = plen + 1;
    }

    System.out.println(Arrays.toString(shift));
    return shift;
    }

    private int[] buildGoodSuffixShift(String pattern) {
    int plen = pattern.length();
    int[] shift = new int[plen];
    shift[plen - 1] = 1;

    char end_val = pattern.charAt(plen - 1);
    int p_prev, p_next, p_temp;
    int p_now = plen - 2;
    boolean isgoodsuffixfind = false;
    for (int i = plen - 2; i >= 0; --i, --p_now) {
        p_temp = plen - 1;
        isgoodsuffixfind = false;
        while (true) {
        while (p_temp >= 0 && pattern.charAt(p_temp--) != end_val)
            ;
        p_prev = p_temp;
        p_next = plen - 2;

        if (p_prev < 0 && pattern.charAt(p_temp + 1) != end_val)
            break;

        boolean match_flag = true;
        while (p_prev >= 0 && p_next > p_now) {
            if (pattern.charAt(p_prev--) != pattern.charAt(p_next--)) {
            match_flag = false;
            break;
            }
        }

        if (!match_flag)
            continue;
        else {
            if (p_prev < 0
                || pattern.charAt(p_prev) != pattern.charAt(p_next)) {
            isgoodsuffixfind = true;
            break;
            }
        }
        }
        shift[i] = plen - i + p_next - p_prev;
        if (isgoodsuffixfind)
        shift[i]--;
    }

    return shift;
    }

    public static void main(String[] args) {
    BnM instance = new BnM();
    // instance.BnM1("abacababc", "abcdefghijklmnopqrstuvwxyz");
    // System.out.println(instance.BnM2("abacababc", "acab"));
    System.out.println(instance.BnM2("abacbdac", "bdac"));
    System.out
        .println(instance.BnM2("here is a simple example", "example"));
    }
}