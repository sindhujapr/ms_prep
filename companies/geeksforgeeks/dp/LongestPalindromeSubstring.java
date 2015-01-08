package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
 */
public class LongestPalindromeSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindromeSubstring("forgeeksskeegfor"));
        System.out.println(longest("forgeeksskeegfor"));
        System.out.println(longestPalindromeSubstring("ababba"));
        System.out.println(longest("ababba"));
    }
    
    public static int longestPalindromeSubstring(String str) {
        assert str != null && str.length() > 0;
        
        int n = str.length();
        int max = 1;
        for(int k = 0; k < n; k++) {
            int i = k-1, j = k+1;
            while(i >= 0 && j < n && str.charAt(i) == str.charAt(j)) {
                i--;
                j++;
            }
            
            max = Math.max(max, j-i-1);
            
            if(k+1 < n && str.charAt(k) == str.charAt(k+1))
                max = Math.max(max, 2);
            else
                continue;
            
            i = k-1;
            j = k+2;
            while(i >= 0 && j < n && str.charAt(i) == str.charAt(j)) {
                i--;
                j++;
            }
            
            max = Math.max(max, j-i-1);
        }
        
        return max;
    }
    
    public static int longest(String str) {
        assert str != null && str.length() > 0;
        int n = str.length();
        boolean[][] table = new boolean[n][n];
        
        int max = 1;
        for(int i = 0; i < n; i++)
            table[i][i] = true;
        
        for(int i = 0; i < n-1; i++) {
            if(str.charAt(i) == str.charAt(i+1)) {
                table[i][i+1] = true;
                max = 2;
            }
        }
        
        for(int gap = 3; gap <= n; gap++) {
            for(int i = 0; i+gap-1 < n; i++) {
                int j = i+gap-1;
                if(str.charAt(i) == str.charAt(j) && table[i+1][j-1]) {
                    max = gap;
                    table[i][j] = true;
                }
            }
        }
        
        return max;
    }
}