package interview.ms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* compute the jth to the last element of a singly linked list.
 * One approach is to use an array to sort each element.
 * Another approach is recursion.
 * Both use O(n) memory!
 * The best solution involves using two pointers, where one starts off j spaces ahead
 */

public class ComputeJthToLast {
	static int start = 5;
	public static void main(String[] args) {
		List<Integer> list = new LinkedList<Integer>(Arrays.asList(9, 8, 4, 5, 6, 3, 7, 2, 1, 10)); 
		List<Integer> result = new ArrayList<Integer>();

		int size = list.size() - start + 1;
		Iterator<Integer> iter = list.iterator();
		for (int i = 0; i < size && iter.hasNext(); i++) {
			result.add(iter.next());
		}
		System.out.println(result);
		Collections.sort(result);
		System.out.println(result);

		while(iter.hasNext()) {
			Integer value = iter.next();
			System.out.println(value);
			if(value > result.get(0)) {
				result.set(0, value);
				Collections.sort(result);
			}
		}
		System.out.println(result);
	}
}