package lc;

public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] matrix = { "10101110".toCharArray(), "11011000".toCharArray(), "11100101".toCharArray(),
                "10111110".toCharArray(), "00011110".toCharArray() };
        System.out.println(new MaximalRectangle().maximalRectangle(matrix));
    }
    
    /*
     * my own latest implementation:
     * 1. for each row, get the height of the current column based on whether it's '1' or '0'
     * 2. calculate how far we can extend towards the left and right with the current height
     * 3. calculate the maximal area with the current height
     */
    public int maximalRectangle3(char[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return 0;
        
        int m = matrix.length, n = matrix[0].length;
        int max = 0;
        
        int[] left = new int[n], right = new int[n];
        int[] last = new int[n];
        for(int i = 0; i < m; i++) {
            int[] height = new int[n];
            for(int j = 0; j < n; j++)
                height[j] = matrix[i][j] == '1' ? last[j] + 1 : 0;
            
            left[0] = 0;
            for(int j = 1; j < n; j++) {
                if(height[j] <= height[j-1]) {
                    int index = left[j-1];
                    while(index > 0 && height[index-1] >= height[j])
                        index--;
                    
                    left[j] = index;
                } else {
                    left[j] = j;
                }
            }
            
            right[n-1] = n-1;
            for(int j = n-2; j >= 0; j--) {
                if(height[j] <= height[j+1]) {
                    int index = right[j+1];
                    while(index < n-1 && height[index+1] >= height[j])
                        index++;
                    
                    right[j] = index;
                } else {
                    right[j] = j;
                }
            }
            
            //this can be moved to the above loop
            for(int j = 0; j < n; j++)
                max = Math.max(max, (right[j]-left[j]+1) * height[j]);
            
            last = height;
        }
        
        return max;
    }

    /* refined code based on the below implementation:
     * http://gongxuns.blogspot.com/2012/12/leetcode-maximal-rectangle.html
     * Algorithm description:
     * 1) Calculate the left/right boundaries for each point. "Boundary" means the longest distance this "row" can extend.
     * 2) For each column, we have toLeft[] and toRight[] to record the left/right boundaries with the specific height of each point.
     * 3) The height of each point is the longest distance move upwards. If the upper point is '0' and the current point is '1', the height is reset to 1.
     * 4) Key idea (for points with value '1' ONLY, because we don't need to consider points '0'):
     * 4a) If the upper point is '0', then reset the height to 1 and use leftB/rightB to calculate the area.
     * 4b) Otherwise, the height is the height of upper point plus one. The left/right boundaries should be the shorted boundaries chosen from the boundaries
     * of the upper point and the current point.
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] leftB = new int[m][n];
        int[][] rightB = new int[m][n];
        int[] toLeft = new int[n];
        int[] toRight = new int[n];
        int[] height = new int[n];

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == '1')
                    leftB[i][j] = j > 0 && matrix[i][j - 1] == '1' ? leftB[i][j - 1] : j;

            for (int j = n - 1; j >= 0; j--)
                if (matrix[i][j] == '1')
                    rightB[i][j] = j < n - 1 && matrix[i][j + 1] == '1' ? rightB[i][j + 1] : j;

            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0')
                    continue;

                if (i > 0 && matrix[i - 1][j] == '1') {
                    height[j] = height[j] + 1;
                    toLeft[j] = Math.max(leftB[i][j], toLeft[j]);
                    toRight[j] = Math.min(rightB[i][j], toRight[j]);
                } else {
                    height[j] = 1;
                    toLeft[j] = leftB[i][j];
                    toRight[j] = rightB[i][j];
                }

                max = Math.max(max, height[j] * (toRight[j] - toLeft[j] + 1));
            }
        }
        return max;
    }

    /*
     * sometimes doesn't pass large judge
     */
    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] height = new int[m][n];
        int[][] length = new int[m][n];

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    height[i][j] = 0;
                    length[i][j] = 0;
                } else {
                    height[i][j] = i > 0 ? height[i - 1][j] + 1 : 1;
                    length[i][j] = j > 0 ? length[i][j - 1] + 1 : 1;
                }

                int cnt = j - 1;
                int maxHeight = height[i][j];
                max = Math.max(maxHeight, max);
                while (cnt >= 0 && height[i][cnt] > 0) {
                    maxHeight = Math.min(maxHeight, height[i][cnt]);
                    max = Math.max(maxHeight * (j - cnt + 1), max);
                    cnt--;
                }

                cnt = i - 1;
                int maxLength = length[i][j];
                max = Math.max(maxLength, max);
                while (cnt >= 0 && length[cnt][j] > 0) {
                    maxLength = Math.min(maxLength, length[cnt][j]);
                    max = Math.max(maxLength * (i - cnt + 1), max);
                    cnt--;
                }
            }
        }

        return max;
    }

    public void print(int[][] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++)
                System.out.print(result[i][j] + "  ");
            System.out.println();
        }

        System.out.println("#################################");
    }

    public void print(char[][] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++)
                System.out.print(result[i][j] + " ");
            System.out.println();
        }

        System.out.println("#################################");
    }
}
