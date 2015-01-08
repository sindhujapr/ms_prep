package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/find-position-of-the-only-set-bit/
 */
public class FindOnlySetBit {
	public static void main(String[] args) {
		System.out.println(find(4));
		System.out.println(find(1 << 20));
		System.out.println(find(18));
	}
	
	public static int find(int num) {
		int pos = -1;
		int i = 0;
		while(i < 32) {
			if((num & (1 << i)) != 0) {
				if(pos >= 0)
					return -1;
				else
					pos = i;
			}
			i++;
		}
		
		return pos;
	}
}