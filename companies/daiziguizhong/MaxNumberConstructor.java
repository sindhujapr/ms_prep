package interview.daiziguizhong;

/*
 * http://chuansongme.com/n/120936
 */
public class MaxNumberConstructor {
	public int maxNum(int[] arr) {
		qsort(arr, 0, arr.length-1);
		
		int res = 0;
		for(int val : arr)
			res = concatenate(res, val);
		return res;
	}
	
	private void qsort(int[] arr, int start, int end) {
		if(start >= end)
			return;

		int j = start, i = start+1;
		while(i <= end) {
			if(compare(arr[i], arr[start]))
				swap(arr, i, ++j);
			++i;
		}
		
		swap(arr, start, j);
		qsort(arr, start, j-1);
		qsort(arr, j+1, end);
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private boolean compare(int v1, int v2) {
		int val1 = concatenate(v1, v2), val2 = concatenate(v2, v1);
		return val1 > val2;
	}
	
	private int concatenate(int v1, int v2) {
		int v = v2;
		while(v > 0) {
			v1 *= 10;
			v /= 10;
		}
		return v1+v2;
	}
	
	public static void main(String[] args) {
		System.out.println(new MaxNumberConstructor().maxNum(new int[] {4, 94, 9, 14, 1}));
	}
}