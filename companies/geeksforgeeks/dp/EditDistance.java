package geeksforgeeks.dp;

public class EditDistance {

	public static void main(String[] args) {
		
	}
	
	/*
	 * see leetcode 
	 */
	public static int distance(char[] arr1, char[] arr2) {
		int m = arr1.length;
		int n = arr2.length;
		int[][] dist = new int[m+1][n+1];
		for(int i = 0; i < m+1; i++)
			dist[i][0] = i;
		
		for(int j = 1; j < n+1; j++)
			dist[0][j] = j;
		
		for(int i = 1; i < m+1; i++) {
			for(int j = 1; j < n+1; j++) {
				
			}
		}
		
		return dist[m][n];
	}
}