package lc;

public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;

        int low = 0;
        int high = m * n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int row = mid / n;
            int column = mid % n;

            if (matrix[row][column] == target)
                return true;
            else if (matrix[row][column] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return false;
    }
}
