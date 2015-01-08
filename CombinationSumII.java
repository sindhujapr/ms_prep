package lc;

import java.util.ArrayList;
import java.util.Arrays;

public class CombinationSumII {
	public static void main(String[] args) {
		int[] num = {1, 1, 1, 2};
//		ArrayList<ArrayList<Integer>> res = new CombinationSumII().combinationSum2(num, 3);
		ArrayList<ArrayList<Integer>> res = new CombinationSumII().combinationSumII_iter(num, 3);
		for(ArrayList<Integer> tmp : res)
			System.out.println(tmp);
	}

	public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (num == null || num.length == 0)
			return result;

		Arrays.sort(num);
		ArrayList<Integer> one = new ArrayList<Integer>();
		find(num, 0, target, result, one);
		return result;
	}

	private void find(int[] candidates, int start, int target, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> one) {
		if (target == 0) {
			result.add(new ArrayList<Integer>(one));
			return;
		}

		
		/*
		 * trick: we use the same element for different positions. For example, 
		 * if there are two 1's, then we'd use them as the first and second element,
		 * respectively. However, we won't use them in the same position (in one).
		 * That means, the two 1's cannot occur as the first element (in one).
		 * This is the reason why we have the code below.
		 */
		for (int i = start; i < candidates.length; i++) {
			if (target < candidates[i])
				break;

			if (i > start && candidates[i] == candidates[i-1])
				continue;

			one.add(candidates[i]);
			find(candidates, i + 1, target-candidates[i], result, one);
			one.remove(one.size() - 1);
		}
	}
	
	public ArrayList<ArrayList<Integer>> combinationSumII_iter(int[] num, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> one = new ArrayList<Integer>();
		List<Integer> stack = new ArrayList<Integer>();
		Arrays.sort(num);

		int index = 0;
		do {
			// think about why we use target > 0 but not target >= num[index]
			while(index < num.length && target > 0) {
				one.add(num[index]);
				target -= num[index];
				stack.add(index);
				index++;
			}
			
			if(target == 0)
				result.add(new ArrayList<Integer>(one));
			
			index = stack.remove(stack.size()-1) + 1;
			target += one.remove(one.size()-1);
			while(index < num.length && num[index] == num[index-1])
				index++;
		} while(one.size() > 0 || index < num.length);
		
		return result;
	}
}
