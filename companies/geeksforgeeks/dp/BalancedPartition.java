package geeksforgeeks.dp;

public class BalancedPartition {
	public static void main(String[] args) {
		int[] values = {1,3,10,20,30};
		System.out.println(partition(values, 5));
	}

	public static int partition(int[] values, int K) {
		int sum = 0;
		for(int i = 0; i < values.length; i++)
			sum += values[i];

		int[][] matrix = new int[values.length+1][sum+1];
		for(int i = 0; i < values.length+1; i++)
			matrix[i][0] = 1;

		for(int i = 1; i <= values.length; i++) {
			for(int j = 1; j <= sum; j++) {
				matrix[i][j] = Math.max(matrix[i-1][j], (j >= values[i-1] ? matrix[i-1][j-values[i-1]] : 0));
			}
		}
		
		int avg = sum/2;
		for(int i = 0; i <= avg; i++)
			if(matrix[values.length][avg-i] == 1)
				return (sum-2*avg+2*i);
		
		return -1;
	}
}