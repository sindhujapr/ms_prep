package interview.daiziguizhong;

/*
 * http://chuansongme.com/n/117305
 */
public class MinimumRange {
	/*
	 * it's possible that p1 and p2 point to the same value. In that case, the min range
	 * won't decrease if we only move one of them. But it will be a complex to code. So
	 * we just move them one by one.
	 */
	public int minRange(int[] arr1, int[] arr2, int[] arr3) {
		int minRange = Integer.MAX_VALUE;
		
		for(int i = 0, j = 0, k = 0; i < arr1.length && j < arr2.length && k < arr3.length; ) {
			minRange = gap(arr1[i], arr2[j], arr3[k]);
			if(arr1[i] <= arr2[j] && arr1[i] <= arr3[k]) {
				i++;
			} else if(arr2[j] <= arr1[i] && arr2[j] <= arr3[k]) {
				j++;
			} else {
				k++;
			}
		}
		
		return minRange;
	}
	
	private int gap(int m, int n, int j) {
		return Math.max(m, Math.max(n, j)) - Math.min(m, Math.min(n, j));
	}
	
	public static void main(String[] args) {
		int[] arr1 = {4, 10, 15, 24, 26};
		int[] arr2 = {0, 9, 12, 20};
		int[] arr3 = {5, 18, 22, 30};
		
		System.out.println(new MinimumRange().minRange(arr1, arr2, arr3));
	}
}