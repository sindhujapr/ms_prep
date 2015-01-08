package geeksforgeeks.backtracking;

import java.util.Arrays;

/*
 * http://www.geeksforgeeks.org/backtracking-set-3-n-queen-problem/
 */
public class NQueens {
	public static void main(String[] args) {
		int[][] queens = new int[16][16];
		solve(queens);
	}

	public static void solve(int[][] queens) {
		solve(queens, 0);
	}

	private static boolean solve(int[][] queens, int row) {
		if (row == queens.length) {
			for (int i = 0; i < queens.length; i++)
				System.out.println(Arrays.toString(queens[i]));
			return true;
		}

		// find a column in <row> to place
		for (int col = 0; col < queens.length; col++) {
			/*
			 * it's better to set the position after checking whether it's available.
			 * Otherwise we need to exclude this position when evaluating whether it's safe
			 */
			if (isSafe(queens, row, col)) {
				queens[row][col] = 1;

				if(solve(queens, row + 1))
					return true;

				queens[row][col] = 0;
			}
		}
		return false;
	}

	private static boolean isSafe(int[][] queens, int row, int col) {
		for (int i = 0; i < row; i++) {
			if(queens[i][col] == 1)
				return false;
		}
		
		for(int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--)
			if(queens[i][j] == 1)
				return false;
		
		for(int i = row-1, j = col+1; i >= 0 && j < queens.length; i--, j++)
			if(queens[i][j] == 1)
				return false;

		return true;
	}
}