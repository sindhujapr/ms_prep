package careercup.fb;

/*
 * http://www.careercup.com/question?id=6287528252407808
 */
public class KPalindrome {
    public static void main(String[] args) {
        System.out.println(kpalin("abdxa", 1));
    }
    
    public static boolean kpalin(String str, int K) {
        char[] chs = str.toCharArray();
        int n = chs.length;
        
        // matrix[i][i] and matrix[i][i+1] are automatically initialized to 0
        int[][] matrix = new int[n+1][n+1];

        for(int len = 2; len < n+1; len++) {
            for(int i = 0; i + len < n+1; i++) {
                int j = i+len;
                if(chs[i] == chs[j-1])
                    matrix[i][j] = matrix[i+1][j-1];
                else
                    matrix[i][j] = Math.min(matrix[i+1][j], matrix[i][j-1]) + 1;
            }
        }
        
        return matrix[0][n] <= K;
    }
}