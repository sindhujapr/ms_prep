package interview.linkdin;

public class FindMajorityNumber {
	/*
	 * http://www.careercup.com/question?id=15206756
	 * 
	 * Moores voting: one pass!!!
	 */
	public int findMajorityElement(int arr[]) {
		if (arr == null)
			return Integer.MIN_VALUE;

		int length = arr.length;
		int majorIndex = 0;
		int count = 1;
		for (int i = 0; i < length; i++) {
			if (arr[majorIndex] == arr[i])
				count++;
			else
				count--;
			if (count == 0) {
				majorIndex = i;
				count = 1;
			}
		}
		return arr[majorIndex];
	}

	public int isMajority(int arr[]) {
		if (arr == null)
			return -1;
		
		int element = findMajorityElement(arr);
		int length = arr.length;
		int count = 0;
		for (int i = 0; i < length; i++) {
			if (arr[i] == element)
				count++;
		}
		
		return count > length/2 ? element : -1;
	}
	
	public static void main(String[] args) {
		int[] arr = {3, 1, 1, 2};
		System.out.println(new FindMajorityNumber().isMajority(arr));
	}
}