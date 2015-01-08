
public class LongestSubstringAtMostTwoDistinctCharacters {
    /*
     * http://www.danielbit.com/blog/puzzle/leetcode/leetcode-longest-substring-with-at-most-two-distinct-characters
     * https://changhaz.wordpress.com/2014/11/26/leetcode-longest-substring-with-at-most-two-distinct-characters/
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        /*
         * i and k point to the beginning and end of the window. j points
         * to the character previous to the 2nd last turning point.
         * The basic idea is, (j, k) contains characters that are different
         * from s[j] (if j is not -1). So if s[k] != s[j], we hit the third
         * character. Otherwise, we're hitting one of the two characters.
         */
        int i = 0, j = -1, maxLen = 0;
        for (int k = 1; k < s.length(); k++) {
            if (s.charAt(k) == s.charAt(k - 1))
                continue;

            if (j >= 0 && s.charAt(j) != s.charAt(k)) {
                maxLen = Math.max(k - i, maxLen);
                i = j + 1;
            }
            j = k - 1;
        }
        return Math.max(s.length() - i, maxLen);
    }
}
