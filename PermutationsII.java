package lc;

import java.util.ArrayList;
import java.util.Arrays;

public class PermutationsII {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> result = new PermutationsII().permuteUnique2(new int[] { -1, -1, 3, -1});
//		ArrayList<ArrayList<Integer>> result = new PermutationsII().permuteUnique4(new int[] { 1, 1, 2, 2, 2});
//		ArrayList<ArrayList<Integer>> result = new PermutationsII().permuteUnique4(new int[] { 0, 1, 0, 0, 9});
		for (ArrayList<Integer> list : result)
			System.out.println(list);
	}

	// output limit exceeded!!
	public ArrayList<ArrayList<Integer>> permuteUnique3(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (num == null || num.length == 0)
			return res;

		Arrays.sort(num);
		permute3(num, res, new ArrayList<Integer>());
		return res;
	}

	private void permute3(int[] num, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list) {
		int start = list.size();
		if (start == num.length) {
			res.add(new ArrayList<Integer>(list));
		} else {
			for (int i = start; i < num.length; i++) {
				/*
				 * if (i == start), it's the first time that value num[start]
				 * occurs at position start, thus it's ok to proceed. Otherwise
				 * if the below conditions stand, value num[start] has occurred
				 * before, thus we should skip this combination.
				 */
				if (i == start && i > 0 && num[i] == num[i - 1])
					continue;

				swap(num, i, start);
				list.add(num[start]);
				permute3(num, res, list);
				list.remove(list.size() - 1);
				swap(num, i, start);
			}
		}
	}

	/*
	 * wrong implementation: take [1, 1, 2, 2] for example, there are three
	 * combinations for [1, 1, 2]: {1, 1 ,2}, {1, 2, 1} and {2, 1, 1}. For the
	 * last element 2, though we will not insert it before and after the
	 * existing element 2 in {1, 1, 2}, we will still insert it in position 1 of
	 * {1, 1, 2} and position 3 of {1, 2, 1}, which would be duplicate.
	 */
	public ArrayList<ArrayList<Integer>> permuteUnique5(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (num == null || num.length == 0)
			return result;

		Arrays.sort(num);

		result.add(new ArrayList<Integer>());
		for (int i = 0; i < num.length; i++) {
			ArrayList<ArrayList<Integer>> cur = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> temp : result) {
				for (int j = 0; j <= temp.size(); j++) {
					if (j > 0 && num[i] == temp.get(j - 1))
						continue;
					temp.add(j, num[i]);
					cur.add(new ArrayList<Integer>(temp));
					temp.remove(j);
				}
			}
			result = cur;
		}

		return result;
	}

	public ArrayList<ArrayList<Integer>> permuteUnique1(int[] num) {
		if (num == null)
			return null;

		Arrays.sort(num);
		boolean[] used = new boolean[num.length];
		return perm(num, 0, used);
	}

	public ArrayList<ArrayList<Integer>> perm(int[] num, int start, boolean[] used) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (start == num.length) {
			result.add(new ArrayList<Integer>());
			return result;
		}

		for (int i = 0; i < num.length; i++) {
			/*
			 * if this element has been used, or it's the same as the previous
			 * element, which has been used, then we cannot use this element.
			 * That means, we can ONLY place the same elements in reverse order.
			 */
			if (used[i] || (i > 0 && num[i] == num[i - 1] && used[i - 1]))
				continue;
			used[i] = true;
			for (ArrayList<Integer> x : perm(num, start + 1, used)) {
				x.add(0, num[i]);
				result.add(x);
			}
			used[i] = false;
		}

		return result;
	}

	// this is good implementation
	public ArrayList<ArrayList<Integer>> permuteUnique2(int[] num) {
		assert num != null;

		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();

		// no need to sort. see match()
		permute2(num, res, list);
		return res;
	}

	private void permute2(int[] num, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list) {
		int start = list.size();

		if (start == num.length) {
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = start; i < num.length; i++) {
			if (!match(num, start, i)) {
				swap(num, start, i);
				list.add(num[start]);

				permute2(num, res, list);

				list.remove(list.size() - 1);
				swap(num, start, i);
			}
		}
	}

	private boolean match(int[] num, int i, int j) {
		if (i == j)
			return false;

		for (; i < j; i++)
			if (num[i] == num[j])
				return true;
		return false;
	}

	private void swap(int[] s, int i, int j) {
		if (i == j)
			return;

		s[i] ^= s[j];
		s[j] ^= s[i];
		s[i] ^= s[j];
	}

	/*
	 * http://gongxuns.blogspot.com/2012/12/leetcodepermutations-ii.html
	 */
	public ArrayList<ArrayList<Integer>> permuteUnique4(int[] num) {
		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

		int i = num.length - 1;
		res.add(convert(num));
		while (i > 0) {
			/* 
			 * find the right-most element that hasn't been processed yet.
			 * Since the array was sorted, thus if num[j] >= num[j+1], either
			 * num[j] is equal to num[j+1] (and we need to skip it), or it
			 * was swapped before (in which case we also need to skip it).
			 */
			int j = i - 1;
			while (j >= 0 && num[j] >= num[j + 1])
				j--;
			if (j >= 0) {
				// restore the ascending order starting from index j
				for (int m = j; m < i - 1; m++)
					for (int k = i; k > m + 1; k--)
						if (num[k] < num[k - 1])
							swap(num, k, k - 1);

				// find an element to swap
				int m = j + 1;
				// why not == here?
				while (num[m] <= num[j])
					m++;
				swap(num, m, j);
				res.add(convert(num));
			} else {
				i--;
			}
		}
		return res;
	}

	public ArrayList<Integer> convert(int[] num) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < num.length; i++)
			res.add(num[i]);
		return res;
	}
}