package lc;

public class SearchRotatedSortedArray {
	public static void main(String[] args) {
		int[] array = {3, 1};
		for(int i = 0; i < array.length; i++)
			System.out.println(search(array, array[i]));
	}

	public static int search(int[] array, int key) {
		int low = 0, high = array.length-1;
		while(low <= high) {
			int mid = (low+high) >>> 1;
			if(array[mid] == key) {
				return mid;
			}
			
			// left half is sorted
			if(array[mid] >= array[low]) {
				// only when the key falls into the sorted part
				if(key >= array[low] && key <= array[mid])
					high = mid-1;
				else
					low = mid+1;
			} else {
				if(array[mid] <= key && key <= array[high])
					low = mid+1;
				else
					high = mid-1;
			}
		}
		
		return -1;
	}

	public int search1(int[] A, int target) {
		return searchRecursionImp(A, 0, A.length - 1, target);
	}

	public int searchRecursionImp(int[] A, int left, int right, int target) {
		int mid = (left + right) / 2;
		if (A[mid] == target)
			return mid;

		if (left > right)
			return -1;

		if (A[mid] >= A[left]) {
			if (target >= A[left] && target <= A[mid])
				return searchRecursionImp(A, left, mid - 1, target);
			else
				return searchRecursionImp(A, mid + 1, right, target);
		} else {
			if (target <= A[right] && target >= A[mid])
				return searchRecursionImp(A, mid + 1, right, target);
			else
				return searchRecursionImp(A, left, mid - 1, target);
		}
	}
}