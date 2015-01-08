package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingcunz on 11/24/14.
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        List<Integer> res = new SpiralMatrix().spiralOrder(new int[][] { new int[] {1}});
        for(int val : res)
            System.out.println(val);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0)
            return res;

        int m = matrix.length, n = matrix[0].length;
        for(int start = 0; start <= m/2; start++) {
            int end = m-start-1;
            int colStart = start, colEnd = n-colStart-1;

            if(start > end || colStart > colEnd)
                break;

            if(start == end) {
                while(colStart <= colEnd)
                    res.add(matrix[start][colStart++]);
                break;
            } else if(colStart == colEnd) {
                while(start <= end)
                    res.add(matrix[start++][colStart]);
                break;
            }

            for(int i = colStart; i <= colEnd; i++)
                res.add(matrix[start][i]);
            for(int i = start+1; i <= end; i++)
                res.add(matrix[i][colEnd]);
            for(int i = colEnd-1; i >= colStart; i--)
                res.add(matrix[end][i]);
            for(int i = end-1; i > start; i--)
                res.add(matrix[i][colStart]);
        }
        return res;
    }
}
