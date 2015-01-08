package interview.java;

public class Test1 {
	private void test() {
		int i = 66;
		switch(i) {
		case 'A': System.out.println("zero"); break;
		case 'B': System.out.println("one"); break;
		case 'C': System.out.println("two"); break;
		default: System.out.println("default");
//		case 66: System.out.println("66");
		}
	}
	
	public static void main(String[] args) {
		Integer x = 9;
		int y = 5;
		x /= x;
		y *= y;
		y += y;
		x -= y;
		
		System.out.println(x);
		
		int[] i = {1, 2, 3, 4, 5};
		float[] j = new float[5];
		for (int k = 0; k < j.length; k++) {
			i[k] = (char)j[k];
			System.out.println(i[k]);
		}
	}
}
