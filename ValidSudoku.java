package lc;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    public static void main(String[] args) {
        char[][] board = {"53..7....".toCharArray(),"6..195...".toCharArray(),".98....6.".toCharArray(),"8...6...3".toCharArray(),"4..8.3..1".toCharArray(),"7...2...6".toCharArray(),".6....28.".toCharArray(),"...419..5".toCharArray(),"....8..79".toCharArray() };  
        printArray(board);
        
        System.out.println(board.length);
        System.out.println(board[0].length);
        System.out.println(new ValidSudoku().isValidSudoku(board));
    }
    
    public static void printArray(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public boolean isValidSudoku(char[][] board) {
        assert board.length == 9;
        for(char[] array : board)
            assert array.length == 9;

        Set<Character> set = new HashSet<Character>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == '.')
                    continue;
                if(set.contains(board[i][j]))
                    return false;
                else
                    set.add(board[i][j]);
            }
            set.clear();
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[j][i] == '.')
                    continue;
                if(set.contains(board[j][i]))
                    return false;
                else
                    set.add(board[j][i]);
            }
            set.clear();
        }
        
        for(int i = 0; i < board.length; i+=3) {
            for(int j = 0; j < board[i].length; j+=3) {
                if(!judge(board, i, j))
                    return false;
            }
        }
        
        return true;
    }
    
    private boolean judge(char[][] board, int i, int j) {
        Set<Character> set = new HashSet<Character>();
        for(int m = i; m < i+3; m++) {
            for(int k = j; k < j+3; k++) {
                if(board[m][k] == '.')
                    continue;
                if(set.contains(board[m][k]))
                    return false;
                else
                    set.add(board[m][k]);
            }
        }
        return true;
    }
}