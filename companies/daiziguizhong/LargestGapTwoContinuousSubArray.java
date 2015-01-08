package interview.daiziguizhong;

/*
 * http://chuansongme.com/n/115425
 * 
 * We need to find an index i, such that the gap between 
 * the max sum from the right and the min sum from the left, or the gap
 * between the min sum from the right and the max sum from the left is
 * maximized. 
 */
public class LargestGapTwoContinuousSubArray {
	public int largestGap(int[] arr) {
		assert arr != null && arr.length > 2;
		
		int n = arr.length;
		
		int[] maxLeft = new int[n], minLeft = new int[n];
		maxLeft[0] = minLeft[0] = arr[0];
		int maxSoFar = arr[0], minSoFar = arr[0];
		for(int i = 1; i < n; i++) {
			maxSoFar = Math.max(maxSoFar+arr[i], arr[i]);
			maxLeft[i] = Math.max(maxLeft[i-1], maxSoFar);
			
			minSoFar = Math.min(minSoFar+arr[i], arr[i]);
			minLeft[i] = Math.min(minLeft[i-1], minSoFar);
		}
		
		int[] maxRight = new int[n], minRight = new int[n];
		maxRight[n-1] = minRight[n-1] = arr[n-1];
		maxSoFar = minSoFar = arr[n-1];
		for(int i = n-2; i >= 0; i--) {
			maxSoFar = Math.max(maxSoFar+arr[i], arr[i]);
			maxRight[i] = Math.max(maxRight[i+1], maxSoFar);
			
			minSoFar = Math.min(minSoFar+arr[i], arr[i]);
			minRight[i] = Math.min(minRight[i+1], minSoFar);
		}
			
		// actually this loop can be combined with the above one
		int maxGap = Integer.MIN_VALUE;
		for(int i = 0; i < n-2; i++) {
			int v1 = Math.abs(maxLeft[i]-minRight[i+1]);
			int v2 = Math.abs(maxRight[i+1]-minLeft[i]);
			maxGap = Math.max(maxGap, Math.max(v1, v2));
		}
		
		return maxGap;
	}
	
	public static void main(String[] args) {
		System.out.println(new LargestGapTwoContinuousSubArray().largestGap(new int[] {2, -1, -2, 5, 7,  1, -4, 2, 8}));
	}
}