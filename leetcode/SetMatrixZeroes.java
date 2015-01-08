package lc;


public class SetMatrixZeroes {
    /*
     * http://gongxuns.blogspot.com/2012/12/leetcode-set-matrix-zeroes.html
     * Use the elements in the first row and column as flags.
     * My own stupid first thought was to use a copy of that matrix, which
     * takes additional O(m*n) space.
     */
    public void setZeroes(int[][] matrix) {
        if (matrix == null)
            return;
        int m = matrix.length;
        if (m == 0)
            return;
        int n = matrix[0].length;

        boolean firstRow = false;
        boolean firstColumn = false;

        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColumn = true;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstColumn) {
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        }

        if (firstRow) {
            for (int j = 0; j < n; j++)
                matrix[0][j] = 0;
        }
    }
}
