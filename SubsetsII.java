package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> result = new SubsetsII().subsetsWithDup1(new int[] { 2, 1, 2 });
		System.out.println("result #: " + result.size());
		for (List<Integer> one : result)
			System.out.println(one);
	}
	
	/*
	 * can we do it with recursion?
	 */
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (num == null)
			return res;

		Arrays.sort(num);
		// must!
		res.add(new ArrayList<Integer>());

		for (int i = 0, index = 0; i < num.length; i++) {
			if (num[i] != num[index])
				index = i;

			/*
			 * if len > 0, then we're processing duplicate element (the current
			 * element is the same as the one before). In this case, we should
			 * only append the current element to the list that has <len>
			 * elements equal to the current element. For example, given array
			 * {1, 2, 2}, when we process the 2nd 2, it shouldn't be appended to
			 * list [1] because otherwise there will be duplicate.
			 */
			int len = i - index;
			/*
			 * we have to get the size first. if we use iterator, there will be
			 * concurrent modification exception.
			 */
			for (int j = 0, size = res.size(); j < size; j++) {
				List<Integer> list = res.get(j);

				// the list may contain (<len) elements
				if (len > 0 && (list.size() < len || list.get(list.size() - len) != num[i]))
					continue;

				List<Integer> temp = new ArrayList<Integer>(list);
				temp.add(num[i]);
				res.add(temp);
			}
		}

		return res;
	}

	// http://gongxuns.blogspot.com/2012/12/leetcodesubsets-ii.html
	public ArrayList<ArrayList<Integer>> subsetsWithDup1(int[] num) {
		if (num == null)
			return null;

		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> prev = new ArrayList<ArrayList<Integer>>();

		/*
		 * this is actually the same idea as subsetWithDup(). If we are dealing with
		 * a different element from last time, we just clone the previous subsets and
		 * append the new elements to each subset and also create a new subset that 
		 * only contain the new element. Otherwise if it's the same element as last
		 * time, we just reuse the subsets that contain enough this element and append
		 * this element.
		 */
		for (int i = 0; i < num.length; i++) {
			if (i == 0 || num[i] != num[i-1]) {
				prev = new ArrayList<ArrayList<Integer>>();

				for (int j = 0; j < res.size(); j++)
					prev.add(new ArrayList<Integer>(res.get(j)));
			}

			for (ArrayList<Integer> temp : prev)
				temp.add(num[i]);

			if (i == 0 || num[i] != num[i-1]) {
				ArrayList<Integer> temp1 = new ArrayList<Integer>();
				temp1.add(num[i]);
				prev.add(temp1);
			}

			for (ArrayList<Integer> temp : prev)
				res.add(new ArrayList<Integer>(temp));
		}
		res.add(new ArrayList<Integer>());
		return res;
	}

	/*
	 * this is wrong implementation. See reason below
	 */
	public ArrayList<ArrayList<Integer>> subsetsWithDup2(int[] num) {
		if (num == null)
			return null;

		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> prev = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < num.length; i++) {
			/*
			 * prev always has the last element appended but say if we have 1,
			 * 2, 3 and now we're processing element 3, what we have in prev is
			 * [1, 2] and [2], thus we'll miss subset [1, 3]
			 */
			for (List<Integer> list : prev)
				list.add(num[i]);

			if (i == 0 || num[i] != num[i - 1]) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(num[i]);
				prev.add(temp);
			}

			for (List<Integer> list : prev)
				res.add(new ArrayList<Integer>(list));
		}
		res.add(new ArrayList<Integer>());
		return res;
	}
}
