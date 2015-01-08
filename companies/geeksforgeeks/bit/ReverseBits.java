package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/write-an-efficient-c-program-to-reverse-bits-of-a-number/
 */
public class ReverseBits {
	public static void main(String[] args) {
		System.out.println(BitUtil.convertIntToBitsWithPadding(10));
		System.out.println(BitUtil.convertIntToBitsWithPadding(reverse(10)));
		System.out.println(BitUtil.convertIntToBitsWithPadding(reverse2(10)));
	}
	
	// swap each pair of bits
	public static int reverse(int num) {
		for(int i = 0; i < 16; i++) {
			int b1 = (num >>> (31-i)) & 0x1;
			int b2 = (num >> i) & 0x1;
			
			int mask = b1 ^ b2;
			if(mask == 1) {
				num ^= mask << (31-i);
				num ^= mask << i;
			}
		}
		
		return num;
	}
	
	public static int reverse2(int num) {
		// we shouldn't reuse num!!!
		int temp = 0;
		for (int i = 0; i < 32; i++) {
			if((num & (1 << i)) != 0)
				temp |= 1 << (31-i);
		}
		return temp;
	}
	
	// another approach is using lookup table
}