package interview.linkdin;

import java.util.Arrays;

/*
 * http://www.careercup.com/question?id=9100691
 */
public class FormatArray {
	public int[][] format(int[] arr, int nCol) {
		int nRow = arr.length/nCol;
		if(arr.length%nCol != 0)
			nRow++;
		
		int[][] res = new int[nRow][nCol];
		int k = 0;
		for(int i = 0; i < nCol; i++) {
			for(int j = 0; j < nRow; j++) {
				if(j == nRow-1 && i >= arr.length%nCol)
					continue;
				
				res[j][i] = arr[k++];
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7};
		int[][] res = new FormatArray().format(arr, 3);
		System.out.println(Arrays.deepToString(res));
	}
}