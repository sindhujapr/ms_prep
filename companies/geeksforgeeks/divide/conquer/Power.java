package geeksforgeeks.divide.conquer;

/*
 * http://www.geeksforgeeks.org/write-a-c-program-to-calculate-powxn/
 */
public class Power {
	public static void main(String[] args) {
		System.out.println(power(2, -2));
//		
//		System.out.println(-2 >> 1);
//		System.out.println(-2 >>> 1);
//		System.out.println(-2/2);
	}
	
	public static double power(int x, int n) {
		if(n == 0)
			return 1;
		
		double y = power(x, n >> 1);
		if(n % 2 == 0)
			return y * y;
		else {
			if(n > 0)
				return y * y * x;
			else
				return y * y / x;
		}
	}
}