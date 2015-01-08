package lc;

public class InterleavingString {
    public static void main(String[] args) {
        System.out.println(isInterleave("aabaac", "aadaaeaaf", "aadaaeaabaafaac"));
    }
    
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        
        boolean[][] mat = new boolean[s1.length() + 1][s2.length() + 1];
        mat[0][0] = true;
        for (int i = 1; i <= s1.length(); ++i)
            mat[i][0] = mat[i - 1][0] && (s3.charAt(i - 1) == s1.charAt(i - 1));
        
        for (int i = 1; i <= s2.length(); ++i)
            mat[0][i] = mat[0][i - 1] && (s3.charAt(i - 1) == s2.charAt(i - 1));
        
        for (int i = 1; i <= s1.length(); ++i)
            for (int j = 1; j <= s2.length(); ++j)
                mat[i][j] = (mat[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) || (mat[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
        
        return mat[s1.length()][s2.length()];
    }

    // time limit exceeded
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length())
            return false;
        
        int m = s1.length(), n = s2.length(), l = s3.length();
        
        return inter(s1, m, s2, n, s3, l);
    }
    
    private boolean inter(String s1, int m, String s2, int n, String s3, int l) {
        if(m == 0)
            return s3.substring(0, l).equals(s2.substring(0, n));
        
        if(n == 0)
            return s3.substring(0, l).equals(s1.substring(0, m));
        
        if(s1.charAt(m-1) == s3.charAt(l-1) && inter(s1, m-1, s2, n, s3, l-1))
            return true;
        
        if(s2.charAt(n-1) == s3.charAt(l-1) && inter(s1, m, s2, n-1, s3, l-1))
            return true;
        return false;
    }
}
