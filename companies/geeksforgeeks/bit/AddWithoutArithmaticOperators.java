package geeksforgeeks.bit;

import java.util.Random;

/*
 * http://www.geeksforgeeks.org/add-two-numbers-without-using-arithmetic-operators/
 */
public class AddWithoutArithmaticOperators {
	public static void main(String[] args) {
		Random rand = new Random();
		for(int i = 0; i < 10; i++) {
			int x = rand.nextInt(100);
			int y = rand.nextInt(20);
			int sum1 = add(x, y);
			int sum2 = x+y;
			System.out.println(x + "+" + y + "=" + sum1 + "\t" + sum2 + "\t" + (sum1 == sum2));
		}
		
		int x = Integer.MIN_VALUE/3, y = Integer.MIN_VALUE/2;
		int sum1 = add(x, y);
		int sum2 = x+y;
		System.out.println(x + "+" + y + "=" + sum1 + "\t" + sum2 + "\t" + (sum1 == sum2));

	}
	
	public static int add2(int x, int y) {
		int result = 0, carrier = 0;
		int offset = 0;
		while(x != 0 || y != 0 || carrier != 0) {
			int n1 = (x == 0 || (x & 1) == 0) ? 0 : 1;
			int n2 = (y == 0 || (y & 1) == 0) ? 0 : 1;
			
//			int bit;
//			if(n1 == 0 && n2 == 0 && carrier == 0) {
//				carrier = 0;
//				bit = 0;
//			} else if((n1 == 0 && n2 == 0) || (n1 == 0 && carrier == 0) || (n2 == 0 && carrier == 0)) {
//				carrier = 0;
//				bit = 1;
//			} else if(n1 == 0 || n2 == 0 || carrier == 0) {
//				carrier = 1;
//				bit = 0;
//			} else {
//				carrier = 1;
//				bit = 1;
//			}
				
			int temp = carrier;
			carrier = (n1 & n2) | (n1 & carrier) | (n2 & carrier);
			int bit = (n1 & n2 & temp) | (n1 & ~n2 & ~temp) | (~n1 & n2 & ~temp) | (~n1 & ~n2 & temp);
			result |= bit << offset++;
			
			x >>= 1;
			y >>= 1;
		}
		
		return result;
	}
	
	public static int add(int x, int y) {
		while(y != 0) {
			int carrier = x & y;
			x = x ^ y;
			y = carrier << 1;
		}
		return x;
	}
}