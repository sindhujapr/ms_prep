package lc;

public class NQueenII {
    public int totalNQueens(int n) {
        int[] matrix = new int[n];
        return solve(n, 0, matrix);
    }
    
    private int solve(int n, int start, int[] matrix) {
        if(start == n) {
            return 1;
        }

        int count = 0;
        for(int j = 0; j < n; j++) {
            matrix[start] = j;
            if(isValid(matrix, start)) {
                count += solve(n, start+1, matrix);
            }
        }
        return count;
    }
    
    private boolean isValid(int[] matrix, int i) {
        for(int j = 0; j < i; j++)
            if(matrix[j] == matrix[i] || Math.abs(matrix[i]-matrix[j]) == i-j)
                return false;
        return true;
    }
}
