package geeksforgeeks.dp;

public class LongestPalindromeSubsequence {
    public static void main(String[] args) {
        System.out.println(longest("GEEKS FOR GEEKS"));
    }
    
    public static int longest(String str) {
        assert str != null && str.length() > 0;
        
        int n = str.length();
        int length[][] = new int[n][n];
        for(int i = 0; i < n; i++)
            length[i][i] = 1;
    
        for(int gap = 2; gap <= n; gap++) {
            for(int i = 0; i+gap-1 < n; i++) {
                int j = i+gap-1;
                
                if (str.charAt(i) == str.charAt(j))
                    length[i][j] = (gap == 2 ? 0 : length[i+1][j-1]) + 2;
                else
                    length[i][j] = Math.max(length[i+1][j], length[i][j-1]);
            }
        }
        
        return length[0][n-1];
    }
}
