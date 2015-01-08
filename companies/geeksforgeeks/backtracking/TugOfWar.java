package geeksforgeeks.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.geeksforgeeks.org/tug-of-war/
 */
public class TugOfWar {
	public static void main(String[] args) {
		int[] array =  {3, 4, 5, -3, 100, 1, 89, 54, 23, 20};
		System.out.println(find(array));
		
		int[] array1 = {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4};
		System.out.println(find(array1));
	}
	
	public static int find(int[] array) {
		int sum = 0;
		for(int value : array)
			sum += value;

		List<Integer> list = new ArrayList<Integer>();
		return find(array, 0, Integer.MAX_VALUE, sum, list);
	}

	private static int find(int[] array, int start, int minSoFar, int arraySum, List<Integer> stack) {
		int sum = 0;
		for(int index : stack)
			sum += array[index];

		int len = array.length;
		if((len%2 == 0 && stack.size() == len/2) || (len%2 == 1 && stack.size() == (len-1)/2)) {
			if(Math.abs(2*sum-arraySum) < minSoFar) {
				minSoFar = Math.abs(2*sum-arraySum); 
				for(int index : stack)
					System.out.print(array[index] + " ");
				System.out.println("sum " + sum + " diff " + Math.abs(2*sum-arraySum));
			}
			return minSoFar;
		}

		for(int i = start; i < len; i++) {
			stack.add(i);
			minSoFar = find(array, i + 1, minSoFar, arraySum, stack);
			stack.remove(stack.size() - 1);
		}
		
		return minSoFar;
	}
}
