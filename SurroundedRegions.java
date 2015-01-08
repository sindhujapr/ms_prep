package lc2;

import java.util.ArrayList;
import java.util.List;

public class SurroundedRegions {
    public void solve(char[][] board) {
        if(board.length == 0)
            return;
            
        int m = board.length, n = board[0].length;
        for(int i = 0; i < m; i++) {
            solve(board, i*n);
            solve(board, i*n+n-1);
        }
        
        for(int j = 1; j < n-1; j++) {
            solve(board, j);
            solve(board,(m-1)*n+j);
        }
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                else if(board[i][j] == 'D')
                    board[i][j] = 'O';
            }
        }
    }
    
    private void solve(char[][] board, int p) {
        int m = board.length, n = board[0].length;
        
        List<Integer> stack = new ArrayList<Integer>();
        stack.add(p);
        while(stack.size() > 0) {
            int pos = stack.remove(stack.size()-1);
            int row = pos / n, col = pos % n;
    		/*
    		 * here we use condition board[x][y] != 'O'. if the element is 'X', we
    		 * cannot move forward because it's not a surrounded element. if the
    		 * element is 'D', we also cannot move forward because it has been
    		 * visited (and thus marked), otherwise there would be stack overflow.
    		 */
            if(row < 0 || row >= m || col < 0 || col >= n || board[row][col] != 'O')
                continue;
            
            board[row][col] = 'D';
            
            stack.add((row-1)*n+col);
            stack.add((row+1)*n+col);
            stack.add(row*n+col-1);
            stack.add(row*n+col+1);
        }
    }
}