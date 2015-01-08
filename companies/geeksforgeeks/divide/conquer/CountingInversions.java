package geeksforgeeks.divide.conquer;

/*
 * O(nlgn)
 * http://www.geeksforgeeks.org/counting-inversions/
 */
public class CountingInversions {
	public static void main(String[] args) {
		int arr[] = {1, 20, 6, 4, 5};
		
		System.out.println(count(arr, 0, arr.length-1));
	}
	
	public static int count(int[] arr, int start, int end) {
		int[] temp = new int[end-start+1];
		return merge(arr, temp, start, end);
	}
	
	private static int merge(int[] arr, int[] temp, int start, int end) {
		if(start >= end)
			return 0;
		
		int mid = (start+end) >>> 1;
		int count = merge(arr, temp, start, mid);
		count += merge(arr, temp, mid+1, end);
		return count + _merge(arr, temp, start, mid+1, end);
	}
	
	private static int _merge(int[] arr, int[] temp, int start, int mid, int end) {
		int i = start;
		int j = mid;
		
		int count = 0;
		int k = start;
		while(i <= mid-1 && j <= end) {
			if(arr[i] > arr[j]) {
				temp[k++] = arr[j++];
				count += mid-i;
			} else {
				temp[k++] = arr[i++];
			}
		}
		
		while(i <= mid-1)
			temp[k++] = arr[i++];
		while(j <= end)
			temp[k++] = arr[j++];
		
		for(int m = start; m <= end; m++)
			arr[m] = temp[m];

		return count;
	}
}
