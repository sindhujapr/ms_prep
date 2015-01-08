package geeksforgeeks.divide.conquer;

public class CountOccurrencesInSortedArray {
	public static void main(String[] args) {
		int array[] = {1, 3, 5, 5, 5, 5, 9, 11};
		System.out.println(count(array, 0, array.length-1, 5));
		System.out.println(count1(array, 0, array.length-1, 5));
		System.out.println(count1(array, 0, array.length-1, 3));
	}
	
	public static int count(int[] array, int start, int end, int num) {
		if(start <= end) {
			int mid = (start+end) >>> 1;
			int sum = 0;
			if(array[mid] == num)
				sum += count(array, start, mid-1, num) + count(array, mid+1, end, num) + 1;
			else if(array[mid] < num)
				sum += count(array, mid+1, end, num);
			else
				sum += count(array, start, mid-1, num);
			
			return sum;
		}
		
		return 0;
	}
	
	public static int count1(int[] array, int start, int end, int num) {
		int last = findLast(array, start, end, num);
		int first = findFirst(array, start, end, num);
		if(last == -1 || first == -1)
			return 0;
		
		return last - first + 1;
	}
	
	private static int findFirst(int[] array, int start, int end, int num) {
		if(start > end)
			return -1;

		int mid = (start+end) >>> 1;
		if((mid == 0 || array[mid-1] != num) && array[mid] == num)
			return mid;
		else if(array[mid] < num)
			return findFirst(array, mid+1, end, num);
		else
			return findFirst(array, start, mid-1, num);
	}
	
	private static int findLast(int[] array, int start, int end, int num) {
		if(start > end)
			return -1;
		
		int mid = (start+end) >>> 1;
		if((mid == end || array[mid+1] != num) && array[mid] == num)
			return mid;
		else if(array[mid] > num)
			return findLast(array, start, mid-1, num);
		else
			return findLast(array, mid+1, end, num);
	}
}