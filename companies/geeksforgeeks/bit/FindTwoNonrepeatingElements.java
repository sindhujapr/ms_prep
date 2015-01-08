package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/find-two-non-repeating-elements-in-an-array-of-repeating-elements/
 */
public class FindTwoNonrepeatingElements {
	public static void main(String[] args) {
		int[] array = {2, 4, 7, 6, 2, 4};
		find(array);
	}
	
	public static void find(int[] array) {
		int xor = 0;
		for(int i = 0; i < array.length; i++)
			xor ^= array[i];
		
		/*
		 * after the above operation, for each set bit of xor, there could
		 * be only one of the two unique elements has the same set bit.
		 * Thus in the second loop below, we can use (array[i] & setBit) != 0
		 * to distinguish the two sets of elements. 
		 */
		int setBit = xor & ~(xor-1);
		
		int x = 0, y = 0;
		for(int i = 0; i < array.length; i++) {
			if((array[i] & setBit) != 0)
				x ^= array[i];
			else
				y ^= array[i];
		}
		
		System.out.println(x + "\t" + y);
	}
}