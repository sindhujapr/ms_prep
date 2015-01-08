package lc;

import java.util.HashSet;
import java.util.Set;

public class SudokuSolver {
	/*
	 * optimized from:
	 * http://gongxuns.blogspot.com/2012/12/leetcode-sudoku-solver.html
	 */
    public boolean solveSudoku(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    for(int k = 0; k < 9; k++) {
                        board[i][j] = (char) ('1' + k);
                        if(isValid(board, i, j) && solveSudoku(board))
                            return true;
                        board[i][j] = '.';
                    }
                }
            }
        }
        return false;
    }
    
    private boolean isValid(char[][] board, int m, int n) {
        Set<Character> set = new HashSet<Character>();
        for(int i = 0; i < 9; i++) {
            if(set.contains(board[i][n]))
                return false;
            if(board[i][n] >= '1' && board[i][n] <= '9')
                set.add(board[i][n]);
        }
        
        set = new HashSet<Character>();
        for(int j = 0; j < 9; j++) {
            if(set.contains(board[m][j]))
                return false;
            if(board[m][j] >= '1' && board[m][j] <= '9')
                set.add(board[m][j]);
        }
        
        int a = m/3 * 3;
        int b = n/3 * 3;
        set = new HashSet<Character>();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(set.contains(board[a+i][b+j]))
                    return false;
                if(board[a+i][b+j] >= '1' && board[a+i][b+j] <= '9')
                    set.add(board[a+i][b+j]);
            }
        }
        return true;
    }

	// optimized from the above implementation
    public void solveSudoku(char[][] board) {   
        if(board == null || board.length != 9 || board[0].length !=9)  
            return;  
        helper(board,0,0);  
    }   
    
    private boolean helper(char[][] board, int i, int j) {   
        if(j>=9)  
            return helper(board,i+1,0);  

        if(i==9)
            return true;  

        if(board[i][j]=='.') {   
            for(int k=1;k<=9;k++) {   
                board[i][j] = (char)(k+'0');  
                if(isValid(board,i,j) && helper(board,i,j+1))
                        return true;  
                board[i][j] = '.';  
            }   
        }  else  {   
            return helper(board,i,j+1);  
        }   
        return false;  
    }   

    private boolean isValid(char[][] board, int i, int j)  {   
        for(int k=0;k<9;k++)  {   
            if(k!=j && board[i][k]==board[i][j])  
                return false;  
        }   
        for(int k=0;k<9;k++)
            if(k!=i && board[k][j]==board[i][j])  
                return false;  
        for(int row = i/3*3; row<i/3*3+3; row++)
            for(int col=j/3*3; col<j/3*3+3; col++)
                if((row!=i || col!=j) && board[row][col]==board[i][j])  
                    return false;  
        return true;  
    }
}
