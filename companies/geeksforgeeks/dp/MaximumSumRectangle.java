package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
 */
public class MaximumSumRectangle {

	public static void main(String[] args) {
		int[][] rect = {{1,2,-1,-4,-20}, {-8,-3,4,2,1}, {3,8,10,1,3}, {-4,-1,1,7,-6}};
		System.out.println(maxArea(rect));
	}
	
	public static int maxArea(int[][] rect) {
		assert rect != null && rect.length > 0 && rect[0].length > 0;

		int max = Integer.MIN_VALUE;
		
		int m = rect.length;
		int n = rect[0].length;
		int sum[] = new int[m];
		
		for(int col1 = 0; col1 < n; col1++) {
			for(int row = 0; row < m; row++)
				sum[row] = 0;

			for(int col2 = col1; col2 < n; col2++) {
				for(int row = 0; row < m; row++)
					sum[row] += rect[row][col2];
				
				max = Math.max(max, findMax(sum));
			}
		}
		
		return max;
	}
	
	private static int findMax(int[] array) {
		assert array != null && array.length > 1;

		int max = array[0];
		int sum = array[0];
		for(int  i = 1; i < array.length; i++) {
			sum = Math.max(array[i], sum+array[i]);
			max = Math.max(sum, max);
		}
		return max;
	}
}