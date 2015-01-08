package lc;

import java.util.ArrayList;
import java.util.Arrays;

public class NQueen {
	public static void main(String[] args) {
		ArrayList<String[]> result = new NQueen().solveNQueens(2);
		System.out.println(result.size());
		for(String[] strs : result) {
			for(String str : strs)
				System.out.println(str);
			System.out.println("======================");
		}
	}

    public ArrayList<String[]> solveNQueens(int n) {
        List<String[]> result = new ArrayList<String[]>();
        
        int[] matrix = new int[n];
        solve(n, 0, result, matrix);
        return result;
    }
    
    private void solve(int n, int start, List<String[]> result, int[] matrix) {
        if(start == n) {
            result.add(buildStringArray(matrix));
            return;
        }
        
        for(int j = 0; j < n; j++) {
            matrix[start] = j;
            if(isValid(matrix, start)) {
                solve(n, start+1, result, matrix);
            }
        }
    }
    
    private boolean isValid(int[] matrix, int i) {
        for(int j = 0; j < i; j++)
            if(matrix[j] == matrix[i] || Math.abs(matrix[i]-matrix[j]) == i-j)
                return false;
        return true;
    }

    private String[] buildStringArray(int[] matrix) {
        String[] strs = new String[matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < matrix.length; j++)
                if(j == matrix[i])
                    sb.append('Q');
                else
                    sb.append('.');
            strs[i] = sb.toString();
        }
        return strs;
    }
}
