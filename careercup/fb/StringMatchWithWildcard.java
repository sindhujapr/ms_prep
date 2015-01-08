package careercup.fb;

/*
 * http://www.careercup.com/question?id=6631993756352512
 */
public class StringMatchWithWildcard {
    public static void main(String[] args) {
        System.out.println(isMatch("ab*.", "abc"));
    }
    
    public static boolean isMatch(String pattern, String str2) {
        assert pattern != null && str2 != null;
        char[] chs1 = pattern.toCharArray();
        char[] chs2 = str2.toCharArray();
        int m = chs1.length, n = chs2.length;
        
        boolean[][] matrix = new boolean[m+1][n+1];
        matrix[0][0] = true;
        for(int i = 1; i <= m; i++)
            matrix[i][0] = matrix[i-1][0] && chs1[i-1] == '*';
        
        for(int j = 1; j <= n; j++)
            matrix[0][j] = false;
        
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(chs1[i-1] == chs2[j-1] || chs1[i-1] == '.')
                    matrix[i][j] = matrix[i-1][j-1]; 
                
                if(chs1[i-1] == '*')
                    matrix[i][j] = matrix[i-1][j-1] || matrix[i-1][j];
            }
        }
        
        return matrix[m][n];
    }
}
