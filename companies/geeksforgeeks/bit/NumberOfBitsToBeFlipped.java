package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/count-number-of-bits-to-be-flipped-to-convert-a-to-b/
 */
public class NumberOfBitsToBeFlipped {
	public static void main(String[] args) {
		System.out.println(numberOfBits(73, 21));
	}
	
	public static int numberOfBits(int x, int y) {
		int xor = x ^ y;
		int count = 0;
		while(xor != 0) {
			xor &= xor-1;
			count++;
		}
		return count;
	}
}