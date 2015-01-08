package careercup.fb;

public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = {"..9748...".toCharArray(),
                "7........".toCharArray(),
                ".2.1.9...".toCharArray(),
                "..7...24.".toCharArray(),
                ".64.1.59.".toCharArray(),
                ".98...3..".toCharArray(),
                "...8.3.2.".toCharArray(),
                "........6".toCharArray(),
                "...2759..".toCharArray()};
        new SudokuSolver().solveSudoku(board);
    }
    
    public void solveSudoku(char[][] board) {
        solve(board, 0);
    }
    
    private boolean solve(char[][] board, int index) {
        if(index >= 81)
            return true;
        
        int i = index / 9, j = index % 9;
        if(board[i][j] != '.')
            return solve(board, index+1);

        for(int val = 1; val <= 9; val++) {
            board[i][j] = (char) (val + '0');
            if(valid(board, i, j) && solve(board, index+1))
                return true;
            board[i][j] = '.';
        }
        
        return false;
    }
    
    private boolean valid(char[][] board, int i, int j) {
        char val = board[i][j];
        for(int m = 0; m < 9; m++)
            if(m != i && board[m][j] == val)
                return false;
        for(int n = 0; n < 9; n++)
            if(j != n && board[i][n] == val)
                return false;
        
        int row = i-i%3, col = j-j%3;
        for(int k = row; k < row+3; k++) {
            for(int l = col; l < col+3; l++) {
                if(k != i && l != j && board[k][l] == val)
                    return false;
            }
        }
        return true;
    }
}