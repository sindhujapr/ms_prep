package interview.linkdin;

/*
 * http://www.mitbbs.com/article_t/JobHunting/32848895.html
 * http://www.careercup.com/question?id=8591375
 * Returns the next integer a in the arithmetic sequence of integers where 
 * a = m^n, m > 1 n > 1, and m and n are both integers 
 * Thus, the first few outputs will be 4, 8, 9, 16, 25, 27, 32, 64, etc. 
 */
public class NextPower {
	private long current = 3;
	public long next() {
		for(long value = current+1; ; value++) {
			if(isPower(value)) {
				current = value;
				return current;
			}
		}
	}
	
	private boolean isPower(long v) {
		for(long i = 2; i <= v >> 1; i++) {
			int low = 2, high = (int) i;
			while(low <= high) {
				int mid = (low+high) >> 1;
				long power = power(i, mid);
				if(power == v)
					return true;
				else if(power < v)
					low = mid+1;
				else
					high = mid-1;
			}
/*
 			for(int n = 2; ; n++) {
				long power = power(i, n);
				if(v == power)
					return true;
				else if(v < power)
					// continue with the next base
					break;
			}
*/
		}
		
		return false;
	}
	
	private long power(long i, int n) {
		long res = 1;
		while(n > 0) {
			if(n % 2 == 1)
				res *= i;
			
			i *= i;
			n >>= 1;
		}
		return res;
	}
	
	public static void main(String[] args) {
		NextPower instance = new NextPower();
		for(int i = 1; i <= 100; i++)
			System.out.print(instance.next() + ((i % 10 == 0) ? "\n" : "\t"));
	}
}
