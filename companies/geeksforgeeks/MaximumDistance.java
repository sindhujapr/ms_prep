package geeksforgeeks;

/*
 * http://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
 * 
 * Find the maximum value of j-i such that array[j] > array[i]
 */
public class MaximumDistance {
	public static void main(String[] args) {
//		int[] array = {3, 9, 1, 5, 4, 2, 3};
		int[] array = {3429, 2304, 2738, 3452, 2668, 1304, 2666, 2352, 1319, 3529, 2990, 881};
		System.out.println(find1(array));
	}
	
	public static int find1(int[] array) {
		assert array != null;

		int n = array.length;
		int[] lmin = new int[n];
		int[] rmax = new int[n];
		
		lmin[0] = array[0];
		for(int i = 1; i < n; i++)
			lmin[i] = Math.min(lmin[i-1], array[i]);
		
		rmax[n-1] = array[n-1];
		for(int i = n-2; i >= 0; i--)
			rmax[i] = Math.max(rmax[i+1], array[i]);

		int i = 0, j = 0;
		int dist = 0;
		while(i < n && j < n) {
			if(lmin[i] < rmax[j]) {
				dist = Math.max(dist, j-i);
				j++;
			} else {
				i++;
			}
		}
		
		return dist;
	}
}