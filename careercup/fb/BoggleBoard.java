package careercup.fb;

/*
 * http://www.careercup.com/question?id=16142677
 */
public class BoggleBoard {
    public static void main(String[] args) {
        char[][] board = {"smef".toCharArray(), "ratd".toCharArray(),
                        "loni".toCharArray(), "kafb".toCharArray()};
        
        System.out.println(find(board, "star"));
        System.out.println(find(board, "tone"));
        System.out.println(find(board, "note"));
        System.out.println(find(board, "sand"));
    }
    
    public static boolean find(char[][] board, String word) {
        int n = board.length;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                boolean[][] flag = new boolean[n][n];
                if(board[i][j] == word.charAt(0) && find(board, flag, i, j, word, 0))
                    return true;
            }
        }
        
        return false;
    }
    
    private static boolean find(char[][] board, boolean[][] flag, int i, int j, String word, int pos) {
        int n = board.length;
        if(pos >= word.length())
            return true;
        
        if(i < 0 || i >= n || j < 0 || j >= n)
            return false;
        
        if(word.charAt(pos) != board[i][j] || flag[i][j])
            return false;

        flag[i][j] = true;
        if(find(board, flag, i+1, j, word, pos+1) ||
            find(board, flag, i-1, j, word, pos+1) ||
            find(board, flag, i, j+1, word, pos+1) ||
            find(board, flag, i, j-1, word, pos+1) ||
            find(board, flag, i-1, j-1, word, pos+1) ||
            find(board, flag, i+1, j-1, word, pos+1) ||
            find(board, flag, i-1, j+1, word, pos+1) ||
            find(board, flag, i+1, j+1, word, pos+1))
            return true;
        
        return false;
        
    }
}