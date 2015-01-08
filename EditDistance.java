package lc;

public class EditDistance {
    /*
     * http://gongxuns.blogspot.com/2012/12/edit-distance-given-two-words-word1.html
     * 
     * Another problem that can be translated into edit distance 
     * http://www.quora.com/A-k-palindrome-is-a-string-which-transforms-into-a-palindrome-on-removing-at-most-k-characters-from-it-Given-a-string-S-and-an-interger-K-find-whether-S-is-a-k-palindrome-or-not-Constraints-S-has-at-most-20-000-characters-and-0-k-30
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] distances = new int[m + 1][n + 1];
        distances[0][0] = 0;
        for (int i = 1; i <= m; i++)
            distances[i][0] = i;
        for (int i = 1; i <= n; i++)
            distances[0][i] = i;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    distances[i][j] = distances[i - 1][j - 1];
                else
                    distances[i][j] = 1 + Math.min(distances[i - 1][j - 1],
                            Math.min(distances[i - 1][j], distances[i][j - 1]));
            }
        }
        return distances[m][n];
    }
}
