package interview.daiziguizhong;

/*
 * http://chuansongme.com/n/124001
 * 
 * conditions:
 * num[0] >= num[1], num[n-1] >= num[n-2]
 * 
 */
public class WaveTrough {
	// not sure whether it's bug-free code
	public int find(int[] num) {
		int low = 0, high = num.length-1;
		while(low <= high) {
			int mid = (low+high) >> 1;
			if((mid == 0 || num[mid] <= num[mid-1]) && (mid == num.length-1 || num[mid] <= num[mid+1]))
				return mid;
			else if(mid == 0 || num[mid] >= num[mid-1])
				high = mid;
			else if(mid == num.length-1 || num[mid] >= num[mid+1])
				low = mid;
			// both side should be ok.
			else
				high = mid;
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		int[] num = {9,	7,	7,	2,	1,	3,	7};
		System.out.println(new WaveTrough().find(num));
	}
}