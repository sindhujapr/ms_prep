package lc;

public class UniquePathsII {
    public static void main(String[] args) {
        int[][] array = new int[][] {new int[]{1}};
        System.out.println(new UniquePathsII().uniquePathsWithObstacles(array));
    }
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        assert obstacleGrid != null && obstacleGrid.length > 0;
        
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        
        int[][] matrix = new int[m][n];
        for(int i = 0; i < m; i++)
            matrix[i][0] = obstacleGrid[i][0] == 1 ? 0 : (i > 0 ? matrix[i-1][0] : 1);
        for(int i = 1; i < n; i++)
            matrix[0][i] = obstacleGrid[0][i] == 1 ? 0 : (i > 0 ? matrix[0][i-1] : 1);
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(obstacleGrid[i][j] == 1)
                    matrix[i][j] = 0;
                else
                    matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
            }
        }
        
        return matrix[m-1][n-1];
    }
}