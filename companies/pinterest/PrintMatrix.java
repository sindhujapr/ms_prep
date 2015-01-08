package interview.pinterest;

/*
 * http://chuansongme.com/n/249226
 */
public class PrintMatrix {
	/*
	 * suppose the matrix has same number of rows and cols
	 */
	public void print(char[][] matrix) {
		int m = matrix.length;
		for(int i = 0; i < m; i++)
			System.out.print(matrix[i][i] + " ");
		System.out.println();
		
		for(int i = 1; i < m; i++) {
			for(int j = 0; j + i < m; j++)
				System.out.print(matrix[j][j+i] + " ");
			
			System.out.println();
		}
		
		for(int i = 1; i < m; i++) {
			for(int j = 0; j+i < m; j++)
				System.out.print(matrix[j+i][j] + " ");
			
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		char[][] matrix = {"abcd".toCharArray(), "efgh".toCharArray(), "ijkl".toCharArray(), "mnop".toCharArray()};
		new PrintMatrix().print(matrix);
	}
}