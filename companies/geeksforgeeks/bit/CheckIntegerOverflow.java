package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/check-for-integer-overflow/
 */
public class CheckIntegerOverflow {
	public static void main(String[] args) {
		System.out.println(overflow(Integer.MAX_VALUE/2, (Integer.MAX_VALUE/3) * 2));
		System.out.println(overflow(Integer.MIN_VALUE/2, (Integer.MIN_VALUE/3) * 2));
	}
	
	/*
	 * overflow condition:
	 * 1. both operands are of the same sign (negative or positive)
	 * 2. the sign of the sum is different from the operand
	 */
	public static boolean overflow(int x, int y) {
		int sum = x+y;
		if(x > 0 && y > 0 && sum < 0)
			return true;
		else if(x < 0 && y < 0 && sum > 0)
			return true;
		
		return false;
	}
}