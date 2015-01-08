package lc;

import java.util.ArrayList;

public class SpiralMatrix {
	public static void main(String[] args) {
		SpiralMatrix instance = new SpiralMatrix();
		int[][] matrix = {{7}, {9}, {6}};
		ArrayList<Integer> result = instance.spiralOrder(matrix);
		for(Integer value : result)
			System.out.print(value + ", ");
	}
	
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        assert matrix != null;
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(matrix.length == 0)
            return result;
        
        int m = matrix.length;
        int n = matrix[0].length;
        spiralOrder(matrix, 0, m-1, 0, n-1, result);
        return result;
    }
    
    private void spiralOrder(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd, ArrayList<Integer> result) {
        if(rowStart > rowEnd || colStart > colEnd) {
            return;
        } if(rowStart == rowEnd) {
            for(int i = colStart; i <= colEnd; i++)
                result.add(matrix[rowStart][i]);
            return;
        } else if(colStart == colEnd) {
            for(int i = rowStart; i <= rowEnd; i++)
                result.add(matrix[i][colStart]);
            return;
        }
        
        for(int i = colStart; i <= colEnd; i++) {
            result.add(matrix[rowStart][i]);
        }
            
        for(int j = rowStart+1; j <= rowEnd; j++)
            result.add(matrix[j][colEnd]);
            
        for(int i = colEnd-1; i >= colStart; i--)
            result.add(matrix[rowEnd][i]);
            
        for(int j = rowEnd-1; j > rowStart; j--)
            result.add(matrix[j][colStart]);
            
        spiralOrder(matrix, rowStart+1, rowEnd-1, colStart+1, colEnd-1, result);
    }
    
    public ArrayList<Integer> spiralOrder1(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;

        int m = matrix.length, n = matrix[0].length;
        int start = 0, right = n-1, bottom = m-1;
        while(start < right && start < bottom) {
            for(int i = start; i <= right; i++)
                res.add(matrix[start][i]);
            for(int j = start+1; j <= bottom; j++)
                res.add(matrix[j][right]);
            for(int i = right-1; i >= start; i--)
                res.add(matrix[bottom][i]);
            for(int j = bottom-1; j > start; j--)
                res.add(matrix[j][start]);
            
            start++;
            right--;
            bottom--;
        }

        if(start == right) {
            for(int i = start; i <= bottom; i++)
                res.add(matrix[i][right]);
            return res;
        }
            
        if(start == bottom) {
            for(int j = start; j <= right; j++)
                res.add(matrix[start][j]);
        }
        
        return res;
    }
}