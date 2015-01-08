package geeksforgeeks.dp;

public class IntegerKnapsack01 {
	public static void main(String[] args) {
		int[] sizes = {2, 1, 3, 1, 2, 4};
		int[] values = {3, 2, 5, 3, 2, 9};
		
		for(int i = 0; i < 20; i++)
			System.out.println(i + ":\t" + largest(sizes, values, i));
	}
	
	/*
	 * different from integer knapsack problem, we evaluate whether we could
	 * take the current element into consideration. Thus, matrix[i][j] means, 
	 * given the capacity of i, the maximum values we could gain after evaluating
	 * element j. For integer knapsack problem, we always evaluate the whole
	 * array to get the maximum total value.
	 */
	public static int largest(int[] sizes, int[] values, int capacity) {
		// use default values for matrix[0, ..., capacity+1][0] and matrix[0][0, ..., length+1]
		int[][] res = new int[capacity+1][sizes.length+1];
		
		for(int j = 1; j <= capacity; j++) {
			for(int i = 1; i <= sizes.length; i++) {
				/*
				 * we only take into consideration the current element. if it doesn't
				 * fit into the capacity, then the maximum value equals to the case
				 * without the current element.
				 * 
				 * Note that the initialization of matrix[j][i] is within the inner loop,
				 * while for integer knapsack problem, it's beyond the inner loop.
				 */
				res[j][i] = res[j][i-1];
				if(j >= sizes[i-1])
					res[j][i] = Math.max(res[j][i], res[j-sizes[i-1]][i-1] + values[i-1]);
			}
		}
		
		return res[capacity][sizes.length];
	}
}