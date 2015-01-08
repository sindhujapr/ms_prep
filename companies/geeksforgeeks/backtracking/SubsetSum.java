package geeksforgeeks.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.geeksforgeeks.org/backttracking-set-4-subset-sum/
 */
public class SubsetSum {
	public static void main(String[] args) {
		int[] elements = {3, 5, 4, 2, -1, 6, -3, 9};
		find1(elements, 10);
		System.out.println("find2");
		find2(elements, 10);
	}

	public static void find1(int[] elements, int K) {
		int sum = 0;
		List<Integer> stack = new ArrayList<Integer>();
		int i = 0;
		do {
			while(i < elements.length) {
				stack.add(i);
				sum += elements[i++];
			
				if(sum == K) {
					for(int j = 0; sum == K && j < stack.size(); j++)
						System.out.print(elements[stack.get(j)] + ", ");
					System.out.println();
				}
			}

			int j = stack.remove(stack.size()-1);
			sum -= elements[j];
			i = j+1;
		} while(stack.size() > 0 || i < elements.length);
	}
	
	public static void find2(int[] elements, int K) {
		List<Integer> list = new ArrayList<Integer>();
		find2(elements, 0, 0, K, list);
	}

	private static void find2(int[] elements, int start, int sum, int K, List<Integer> list) {
		if(sum == K) {
			for(int i = 0; i < list.size(); i++)
				System.out.print(elements[list.get(i)] + ", ");
			System.out.println();
		}
		
		for(int i = start; i < elements.length; i++) {
			sum += elements[i];
			list.add(i);
			find2(elements, i+1, sum, K, list);

			sum -= elements[i];
			list.remove(list.size()-1);
		}
	}
}