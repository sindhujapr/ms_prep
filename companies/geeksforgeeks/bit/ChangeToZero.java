package geeksforgeeks.bit;

import java.util.Arrays;

/*
 * http://www.geeksforgeeks.org/a-boolean-array-puzzle/
 */
public class ChangeToZero {
	public static void main(String[] args) {
		int[] array = {1, 0};
		change1(array);
		System.out.println(Arrays.toString(array));
		
		int[] array1 = {0, 1};
		change2(array1);
		System.out.println(Arrays.toString(array1));
	}
	
	public static void change1(int[] array) {
		array[array[1]] = array[array[0]];
	}
	
	public static void change2(int[] array) {
		array[0] = array[array[0]];
		array[1] = array[0];
	}
}