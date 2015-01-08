package lc;

public class MinPathSum {
    public int minPathSum(int[][] grid) {
        assert grid != null && grid.length > 0;

        int m = grid.length;
        int n = grid[0].length;
        int[][] weight = new int[m][n];
        for(int i = 0; i < m; i++)
            weight[i][0] = i == 0 ? grid[i][0] : grid[i][0]+weight[i-1][0];
        for(int i = 0; i < n; i++)
            weight[0][i] = i == 0 ? grid[0][i] : grid[0][i]+weight[0][i-1];
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                weight[i][j] = Math.min(weight[i-1][j], weight[i][j-1]) + grid[i][j];
            }
        }
        
        return weight[m-1][n-1];
    }
}