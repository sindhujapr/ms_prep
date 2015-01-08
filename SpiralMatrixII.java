package lc;

import java.util.HashSet;

public class SpiralMatrixII {
	public static void main(String[] args) {
		SpiralMatrixII instance = new SpiralMatrixII();
		int[][] matrix = instance.generateMatrix(3);
		instance.printMatrix(matrix);
	}
	
	public void printMatrix(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++)
				System.out.print(matrix[i][j] + "  ");
			System.out.println();
		}
	}
	
    public int[][] generateMatrix(int n) {        
        int[][] matrix = new int[n][n];
        
        if(n == 0)
            return matrix;

        generateMatrix(matrix, 0, n-1, 1);
        return matrix;
    }
    
    public void generateMatrix(int[][] matrix, int start, int end, int num) {
        if(start > end) {
            return;
        }
        if(start == end) {
        	matrix[start][end] = num;
        	return;
        }
        
        for(int i = start; i <= end; i++)
            matrix[start][i] = num++;
        for(int i = start+1; i <= end-1; i++)
            matrix[i][end] = num++;
        for(int i = end; i >= start; i--)
            matrix[end][i] = num++;
        for(int i = end-1; i > start; i--)
            matrix[i][start] = num++;
        
        generateMatrix(matrix, start+1, end-1, num);
    }
}