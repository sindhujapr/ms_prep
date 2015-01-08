package geeksforgeeks.bit;

public class RotateBits {
	public static void main(String[] args) {
		System.out.println(rightRotate(1, 1));
	}
	
	public static int leftRotate(int num, int offset) {
		return (num << offset) | (num >>> (32-offset));
	}

	public static int rightRotate(int num, int offset) {
		return (num >>> offset) | (num << (32-offset));
	}
}