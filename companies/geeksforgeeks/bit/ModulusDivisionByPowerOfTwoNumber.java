package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/compute-modulus-division-by-a-power-of-2-number/
 */
public class ModulusDivisionByPowerOfTwoNumber {
	public static void main(String[] args) {
		System.out.println(modulo(6, 4));
		System.out.println(modulo(8, 4));
		System.out.println(modulo(30, 4));
	}
	
	// d = 1 << n
	public static int modulo(int num, int d) {
		return num & (d-1);
	}
}