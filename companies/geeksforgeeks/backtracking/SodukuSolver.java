package geeksforgeeks.backtracking;

/*
 * http://www.geeksforgeeks.org/backtracking-set-7-suduku/
 */
public class SodukuSolver {
	public static void main(String[] args) {
		int[][] matrix = new int[9][9];
		find(matrix, 9);
	}
	
	public static void find(int matrix[][], int N) {
		if(find(matrix, N, 0, 0)) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++)
					System.out.print(matrix[i][j] + " ");
				System.out.println();
			}
		} else {
			System.out.println("No solution found");
		}
	}

	private static boolean find(int[][] matrix, int N, int i, int j) {
		if(i == N-1 && j == N)
			return true;
		
		if(j == N) {
			i++;
			j %= N;
		}

		for(int num = 1; num <= N; num++) {
			if(isValid(matrix, i, j, num)) {
				matrix[i][j] = num;
				print(matrix, i, j);
				if(find(matrix, N, i, j+1))
					return true;
				matrix[i][j] = 0;
			}
		}

		return false;
	}
	
	public static void print(int[][] matrix, int i, int j) {
		System.out.println("[" + i + ", " + j + "]: " + matrix[i][j]);
	}

	private static boolean isValid(int[][] matrix, int i, int j, int num) {
		for(int col = 0; col < j; col++)
			if(matrix[i][col] == num)
				return false;
		
		for(int row = 0; row < i; row++)
			if(matrix[row][j] == num)
				return false;

		int row = i-i%3, col = j-j%3;
		for(int m = row; m < row+3; m++)
			for(int n = col; n < col+3; n++)
				if(matrix[m][n] == num)
					return false;

		return true;
	}
}
