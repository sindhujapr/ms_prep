package geeksforgeeks.divide.conquer;

/*
 * http://www.geeksforgeeks.org/check-for-majority-element-in-a-sorted-array/
 */
public class CheckMajorityElement {
	public static void main(String[] args) {
		int arr[] = {1, 2, 3, 3, 3, 3, 10};
		System.out.println(isMajority(arr, 3));
	}
	
	public static boolean isMajority(int[] array, int num) {
		int pos = findFirst(array, 0, array.length-1, num);
		if(pos == -1)
			return false;
		
		/*
		 *  Should we differentiate odd and even?
		 *  We don't need to find the last occurrence of the number!!!
		 */
		int next = pos + array.length/2 - 1; 
		if(next < array.length && array[next] == num)
			return true;
		
		return false;
	}
	
	private static int findFirst(int[] array, int start, int end, int num) {
		if(start <= end) {
			int mid = (start+end) >>> 1;
			if((mid == start || array[mid-1] != num) && array[mid] == num)
				return mid;
			else if(array[mid] < num)
				return findFirst(array, mid+1, end, num);
			else
				return findFirst(array, start, mid-1, num);
		}
		
		return -1;
	}
}