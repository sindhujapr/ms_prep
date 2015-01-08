package lc;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        assert board != null && board.length > 0;
        if(board.length * board[0].length < word.length())
            return false;

        /*
         * if we put the below statement in the for loop, there
         * will be time limit problem
         */
        boolean used[][] = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(exist(board, i, j, word, 0, used))
                    return true;
            }
        }
        
        return false;
    }
    
    private boolean exist(char[][] board, int i, int j, String word, int start, boolean[][] used) {
        if(used[i][j] || start == word.length() || word.charAt(start) != board[i][j])
            return false;
            
        if(start == word.length()-1)
            return true;
            
        int m = board.length, n = board[0].length;
        
        used[i][j] = true;
        start++;
        boolean ret = (i+1 < m && exist(board, i+1, j, word, start, used)) || (i > 0 && exist(board, i-1, j, word, start, used)) ||
                    (j+1 < n && exist(board, i, j+1, word, start, used)) || (j > 0 && exist(board, i, j-1, word, start, used));
        used[i][j] = false;
        return ret;
    }
}