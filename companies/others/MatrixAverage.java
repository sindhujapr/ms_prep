package google;

import java.util.Arrays;

/**
 * Created by qingcunz on 1/6/15.
 */
public class MatrixAverage {
    public static void main(String[] args) {
        float[][] matrix = init(5);
        float[][] res = average(matrix, 3);
        for(float[] arr : res)
            System.out.println(Arrays.toString(arr));
    }

    // k is odd
    public static float[][] average(float[][] matrix, int k) {
        int n = matrix.length;
        float[][] res = new float[n][n];

        float[] colSum = new float[n];
        for(int i = 0; i < k/2; i++)
            for(int j = 0; j < n; j++)
                colSum[j] += matrix[i][j];

        for(int i = 0; i < n; i++) {
            int up = i-k/2-1, down = i+k/2;
            for(int j = 0; up >= 0 && j < n; j++)
                colSum[j] -= matrix[up][j];
            for(int j = 0; down < n && j < n; j++)
                colSum[j] += matrix[down][j];

            float sum = 0;
            for(int j = 0; j < k/2; j++)
                sum += colSum[j];

            int rs = Math.max(i-k/2, 0);
            int re = Math.min(i+k/2, n-1);
            int row = re-rs+1;

            for(int j = 0; j < n; j++) {
                int left = j-k/2-1, right = j+k/2;

                if(left >= 0)
                    sum -= colSum[left];
                if(right < n)
                    sum += colSum[right];

                int cs = Math.max(j-k/2, 0);
                int ce = Math.min(j+k/2, n-1);
                int col = ce-cs+1;

                res[i][j] = sum / (row*col);
            }
        }

        return res;
    }

    public static float[][] init(int n) {
        float[][] matrix = new float[n][n];

        int val = 1;
        for(int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = val++;
        return matrix;
    }
}
