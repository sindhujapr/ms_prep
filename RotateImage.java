package lc;

public class RotateImage {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return;

        for(int i = 0; i < matrix.length/2; i++) {
            rotate(matrix, i);
        }
    }
    
    private void rotate(int[][] matrix, int start) {
        int end = matrix.length-start-1;
        for(int i = 0; i < end-start; i++) {
            int temp = matrix[start][start];
            for(int j = start+1; j <= end; j++) {
                matrix[j-1][start] = matrix[j][start];
            }
            for(int j = start+1; j <= end; j++) {
                matrix[end][j-1] = matrix[end][j];
            }
            for(int j = end; j > start; j--) {
                matrix[j][end] = matrix[j-1][end];
            }
            for(int j = end; j > start; j--) {
                matrix[start][j] = matrix[start][j-1];
            }
            matrix[start][start+1] = temp;
        }
    }
}