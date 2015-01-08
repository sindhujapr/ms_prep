public class MinimumPathSum {
    /*
     * We shouldn't use matrix[m+1][n+1] and set the border element as Integer.MAX_VALUE, since
     * there will be overflow if we add it with whatever positive integer in the grid.
     */
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        
        int m = grid.length, n = grid[0].length;
        int[][] matrix = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0)
                    matrix[i][j] = grid[i][j];
                else if(i == 0 && j != 0) 
                    matrix[i][j] = matrix[i][j-1] + grid[i][j];
                else if(i != 0 && j == 0)
                    matrix[i][j] = matrix[i-1][j] + grid[i][j];
                else
                    matrix[i][j] = Math.min(matrix[i-1][j], matrix[i][j-1]) + grid[i][j];
            }
        }
        
        return matrix[m-1][n-1];
    }
}
