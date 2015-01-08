package interview.lab126;

import java.util.ArrayList;
import java.util.List;

/*
 * Design/Coding: gym, walls (x), walkable areas (o), 3 bicycles(b), 1 water fountain(w)
 * A o o o o o o o o o o o 
 * o o o x o o o x x x o o
 * o o o x o o o x o o o o
 * o o o o o o o o o 2 o o
 * o o o W o B o o o o o o
 * o o x x x o o o o o o o
 * 1 o x x x o o o o o o o
 * o o o o o o o 3 o o o o
 * o o o o x x o o o o o o
 *
 * sum of the distance to all 3 bikes is minimum, you need to find the location.

 * D(W, 1) = 5
 * D(W, 2) = 7
 * D(W, 3) = 7
 *        19
 */
public class Gym {
    // member variables:
    private final char[][] matrix;
    // methods:
    public Gym(char[][] matrix) {
        this.matrix = matrix;
    }
    
    public int dist(int start_row, int start_col, int end_row, int end_col) {
        // Write implementation on this one, step by step approach
        int m = matrix.length, n = matrix[0].length;
        int step = 0;
        List<Integer> list = new ArrayList<Integer>();
        boolean[][] used = new boolean[m][n];
        
        list.add(start_row*n + start_col);
        while(true) {
            List<Integer> adjacent = findAdjacent(list, used);
            
            for(int pos : adjacent) {
                int row = pos / n, col = pos % n;                
                if(row == end_row && col == end_col)
                    return ++step;
                
            }
            
            step++;
        }
    }
    
    private List<Integer> findAdjacent(List<Integer> list, boolean[][] used) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> res = new ArrayList<Integer>();
        for(int pos : list) {
            int row = pos / n, col = pos % n;
            if(row + 1 < n && matrix[row+1][col] != 'x' && !used[row+1][col]) {
                res.add((row+1)*n+col);
                used[row+1][col] = true;
            }
            
            if(row-1 >= 0 && matrix[row-1][col] != 'x')
                res.add((row-1)*n+col);
        }
        
        return res;
    }
}