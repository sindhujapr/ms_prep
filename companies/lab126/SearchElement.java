package interview.lab126;

/*
 * 3 4 5 6 5 4 3 4 3 2 1  0  -1 0  1
 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 
 */
public class SearchElement {
	public static void main(String[] args) {
		System.out.println(new SearchElement().search(new int[]{4, 5, 6, 5, 4, 3, 4, 3, 2, 1, 0, -1, 0, 1}, 3));
	}
	
	public int search(int[] array, int target) {
		for(int i = 0; i < array.length; ) {
			if(target == array[i])
				return i;
			
			i += Math.abs(array[i]-target);
		}
		
		return -1;
	}
	
	// naive implementation
	public int search1(int[] array, int searchTerm) {
		return search(array, 0, array.length - 1, searchTerm);
	}

	private int search(int[] array, int start, int end, int searchTerm) {
		if (start > end)
			return -1;

		int mid = (start + end) >> 1;
		int indexGap1 = mid - start, indexGap2 = end - mid;
		int valueGap1 = array[mid] - array[start], valueGap2 = array[end] - array[mid];

		if (indexGap1 == valueGap1 && array[start] <= searchTerm && searchTerm <= array[mid])
			return start + Math.abs(searchTerm - array[start]);

		if (indexGap2 == valueGap2 && array[mid] <= searchTerm && searchTerm <= array[end])
			return mid + Math.abs(searchTerm - array[mid]);

		if (indexGap1 > valueGap1) {
			int index = search(array, 0, mid - 1, searchTerm);
			if (index >= 0)
				return index;
		}

		if (indexGap2 > valueGap2) {
			int index = search(array, mid + 1, end, searchTerm);
			if (index >= 0)
				return index;
			else
				return -1;
		}

		return -1;
	}
}