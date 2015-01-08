package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * http://blog.163.com/guixl_001/blog/static/417641042013319113320284/
 * http://blog.sina.com.cn/s/blog_b9285de20101iwqt.html
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 *  Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PalindromePartitioningII {
    Map<String, Integer> map = new HashMap<String, Integer>();

    /*
     * doesn't pass large judge
     */
    public int minCut1(String s) {
        if (map.get(s) != null)
            return map.get(s);

        if (isPalindrome(s)) {
            map.put(s, 0);
            return 0;
        }

        /*
         * ret will be updated in each iteration of the loop: each time a
         * smaller or equal value is assigned.
         */
        int ret = s.length() - 1;
        for (int i = 1; i < s.length(); i++) {
            ret = Math.min(ret, minCut1(s.substring(0, i)) + 1 + minCut1(s.substring(i)));
        }

        map.put(s, ret);
        return ret;
    }

    /*
     * also doesn't pass large judge
     */
    public int minCut2(String s) {
        int len = s.length();
        int[][] data = new int[len][len];
        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++)
                if (i == j)
                    data[i][j] = 0;
                else
                    data[i][j] = j - i;

        /*
         * the order of the below two loops cannot be changed, we need to make
         * sure, say, data[i][i+3] is available when we evaluate data[i][i+4].
         */
        for (int add = 1; add < len; add++)
            for (int i = 0; i + add < len; i++) {
                if (isPalindrome(s.substring(i, i + add + 1))) {
                    data[i][i + add] = 0;
                } else {
                    for (int d = 1; d <= add; d++) {
                        data[i][i + add] = Math.min(data[i][i + add], data[i][i + d - 1]
                                + data[i + d][i + add] + 1);
                    }
                }
            }
        return data[0][len - 1];
    }

    public int minCut(String s) {
        int[][] data = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                if (isPalindrome(substring))
                    data[i][j] = 0;
                // else

            }
        }

        return 0;
    }

    /*
     * passes judge small/large
     */
    public int minCut3(String s) {
        boolean[][] isPalindrome = new boolean[s.length() + 1][s.length() + 1];

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= Math.min(i, s.length() - 1 - i); j++) {
                if (s.charAt(i - j) == s.charAt(i + j))
                    isPalindrome[i - j][i + j + 1] = true;
                else
                    break;
            }
            if (i < s.length() - 1)
                for (int j = 0; j <= Math.min(i, s.length() - 2 - i); j++) {
                    if (s.charAt(i - j) == s.charAt(i + 1 + j))
                        isPalindrome[i - j][i + j + 2] = true;
                    else
                        break;
                }
        }

        int[] data = new int[s.length() + 1];
        boolean[] visit = new boolean[s.length() + 1];
        Arrays.fill(data, s.length() - 1);
        for (int i = 1; i <= s.length(); i++) {
            if (isPalindrome[0][i])
                data[i] = 0;
        }

        for (int i = 0; i < s.length(); i++) {
            int min = 0;
            for (int j = 1; j <= s.length(); j++) {
                if (!visit[j]) {
                    if (min == 0 || data[j] < data[min])
                        min = j;
                }
            }
            visit[min] = true;
            for (int j = min + 1; j <= s.length(); j++) {
                if (!visit[j] && isPalindrome[min][j]) {
                    data[j] = Math.min(data[j], data[min] + 1);
                }
            }
        }
        return data[s.length()];
    }

    public boolean isPalindrome(String s) {
        /*
         * here we can also keep all palindrome in a map thus we don't need to
         * judge every string, which sometimes is time-consuming.
         */
        return isPalindrome(s, 0, s.length() - 1);
    }

    public boolean isPalindrome(String s, int i, int j) {
        if (i > j)
            return true;

        if (s.charAt(i) != s.charAt(j))
            return false;

        return isPalindrome(s, i + 1, j - 1);
    }

    public static void main(String[] args) {
        PalindromePartitioningII instance = new PalindromePartitioningII();
        System.out
                .println(instance
                        .minCut1("ltsqjodzeriqdtyewsrpfscozbyrpidadvsmlylqrviuqiynbscgmhulkvdzdicgdwvquigoepiwxjlydogpxdahyfhdnljshgjeprsvgctgnfgqtnfsqizonirdtcvblehcwbzedsmrxtjsipkyxk"));
        System.out.println(instance.minCut1("eegiicgaeadbcfacfhifdbiehbgejcaeggcgbahfcajfhjjdgj"));

        System.out.println(instance.minCut2("cabababcbc"));
    }
}