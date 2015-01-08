package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/efficient-way-to-multiply-with-7/
 */
public class MultiplySeven {
	public static void main(String[] args) {
		
	}
	
	public static int multiply(int num) {
		// make sure no overflow
		return (num << 3) - num;
	}
}