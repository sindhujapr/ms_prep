package interview.daiziguizhong;

public class MagicIndex {
	public static void main(String[] args) {
		int[] arr = {-5, -2, -1, 0, 3, 4, 5, 6, 7, 8, 10};
		System.out.println(new MagicIndex().find(arr));
	}
	
	public int find(int[] arr) {
		int low = 0, high = arr.length-1;
		while(low <= high) {
			int mid = (low+high) >> 1;
			if(mid == arr[mid])
				return mid;
			else if(mid > arr[mid])
				low = Math.max(mid+1, arr[mid]);
			else
				high = Math.min(mid-1, arr[mid]);
		}
		
		return -1;
	}
}