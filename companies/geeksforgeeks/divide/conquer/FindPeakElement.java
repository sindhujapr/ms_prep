package geeksforgeeks.divide.conquer;

/*
 * http://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
 */
public class FindPeakElement {
	public static void main(String[] args) {
		int array1[] = {10, 20, 30, 40, 50};
		System.out.println(findPeak(array1));
		
		int arr[] = {1, 3, 20, 4, 1, 0};
		System.out.println(findPeak(arr));
	}
	
	public static int findPeak(int[] array) {
		return _findPeak(array, 0, array.length-1);
	}
	
	private static int _findPeak(int[] array, int start, int end) {
		if(start >= end)
			return array[start];

		int mid = (start+end) >>> 1;
		
		if(array[mid] >= array[mid-1] && array[mid] >= array[mid+1])
			return array[mid];
		else if(array[mid] < array[mid-1])
			return _findPeak(array, start, mid-1);
		else if(array[mid] < array[mid+1])
	 		return _findPeak(array, mid+1, end);
		
		return -1;
	}
}