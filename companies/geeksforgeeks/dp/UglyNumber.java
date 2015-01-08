package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/ugly-numbers/
 */
public class UglyNumber {
	public static void main(String[] args) {
		uglyNumber(150);
	}
	
	public static void uglyNumber(int seq) {
		int ugly[] = new int[seq];
		ugly[0] = 1;
		
		int i2 = 0, i3 = 0, i5 = 0;
		
		for (int i = 1; i < ugly.length; i++) {
			int next2 = ugly[i2] * 2;
			int next3 = ugly[i3] * 3;
			int next5 = ugly[i5] * 5;
			int next = Math.min(Math.min(next2, next3), next5);

			if(next == next2)
				next2 = ugly[i2++] * 2;
			
			if(next == next3)
				next3 = ugly[i3++] * 3;
			
			if(next == next5)
				next5 = ugly[i5++] * 5;
			
			ugly[i] = next;
		}
		
		for(int i = 0; i < 150; i++) {
			System.out.print(ugly[i]);
			System.out.print((i+1) % 9 == 0 ? "\n" : "\t");
		}
	}
}