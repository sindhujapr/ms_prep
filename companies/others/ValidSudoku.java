package test;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    public static void main(String[] args) {
        /*char[][] board = {
                ".87654321".toCharArray(),
                "2........".toCharArray(),
                "3........".toCharArray(),
                "4........".toCharArray(),
                "5........".toCharArray(),
                "6........".toCharArray(),
                "7........".toCharArray(),
                "8........".toCharArray(),
                "9........".toCharArray()};
        */
        char[][] board = {
                "....5..1.".toCharArray(),
                ".4.3.....".toCharArray(),
                ".....3..1".toCharArray(),
                "8......2.".toCharArray(),
                "..2.7....".toCharArray(),
                ".15......".toCharArray(),
                ".....2...".toCharArray(),
                ".2.9.....".toCharArray(),
                "..4......".toCharArray()};

        boolean ret = new ValidSudoku().isValidSudoku(board);
        System.out.println(ret);
    }

    public boolean isValidSudoku(char[][] board) {
        assert board != null && board.length == 9 && board[0].length == 9;

        for(int i = 0; i < 9; i++)
            if(!validRow(board, i))
                return false;

        for(int j = 0; j < 9; j++)
            if(!validCol(board, j))
                return false;

        for(int i = 0; i < 3; i += 3) {
            for(int j = 0; j < 3; j += 3) {
                if(!validBlock(board, i, j))
                    return false;
            }
        }
        return true;
    }

    private boolean validBlock(char[][] board, int row, int col) {
        System.out.println("validating row = " + row + ", col=" + col);

        Set<Character> set = new HashSet<Character>();
        for(int i = row; i < row+3; i++) {
            for(int j = col; j < col+3; j++) {
                if(board[i][j] == '.')
                    continue;
                if(set.contains(board[i][j]))
                    return false;

                set.add(board[i][j]);
            }
        }
        return true;
    }

    private boolean validRow(char[][] board, int row) {
        boolean[] flag = new boolean[9];
        for(int i = 0; i < 9; i++) {
            if(board[row][i] == '.')
                continue;
            if(flag[board[row][i]-'0'-1])
                return false;
            flag[board[row][i]-'0'-1] = true;
        }
        return true;
    }

    private boolean validCol(char[][] board, int col) {
        boolean[] flag = new boolean[9];
        for(int i = 0; i < 9; i++) {
            if(board[i][col] == '.')
                continue;
            if(flag[board[i][col]-'0'-1])
                return false;
            flag[board[i][col]-'0'-1] = true;
        }
        return true;
    }
}
