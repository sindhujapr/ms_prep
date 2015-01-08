package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
 * http://archive.ite.journal.informs.org/Vol4No1/Sniedovich/index.php
 */
public class EggDropping {
    public static void main(String[] args) {
        System.out.println(minTrials(2, 36));
    }
    
    public static int minTrials(int e, int f) {
        int[][] trials = new int[e+1][f+1];
        for(int i = 0; i <= e; i++) {
            trials[i][0] = 0;
            trials[i][1] = 1;
        }
        
        for(int j = 0; j <= f; j++)
            trials[1][j] = j;
        
        for(int i = 2; i <= e; i++) {
            for(int j = 2; j <= f; j++) {
                trials[i][j] = Integer.MAX_VALUE;
                for(int x = 1; x <= j; x++)
                    trials[i][j] = Math.min(trials[i][j], 1 + Math.max(trials[i-1][x-1], trials[i][j-x]));
            }
        }
        
        return trials[e][f];
    }
}