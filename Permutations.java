package lc;

import java.util.ArrayList;

public class Permutations {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> result = new Permutations()
				.permute3(new int[] { 1 });
		for (ArrayList<Integer> list : result)
			System.out.println(list);
	}

	/*
	 * http://gongxuns.blogspot.com/2012/12/leetcode-permutations.html
	 */
	public ArrayList<ArrayList<Integer>> permute1(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		res.add(new ArrayList<Integer>());

		for (int i = 0; i < num.length; i++) {
			ArrayList<ArrayList<Integer>> cur = new ArrayList<ArrayList<Integer>>();

			for (ArrayList<Integer> temp : res) {
				for (int j = 0; j <= temp.size(); j++) {
					temp.add(j, num[i]);
					cur.add(new ArrayList<Integer>(temp));
					temp.remove(j);
				}
			}

			res = cur;
		}
		return res;
	}

	// latest
    public ArrayList<ArrayList<Integer>> permute3(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> one = new ArrayList<Integer>();
        // we can use one.size() as the start point
        permute3(num, 0, res, one);
        return res;
    }
    
    private void permute3(int[] num, int start, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> one) {
        if(start == num.length) {
            res.add(new ArrayList<Integer>(one));
        } else {
            for(int i = start; i < num.length; i++) {
                swap(num, start, i);
                one.add(num[start]);
                permute3(num, start+1, res, one);
                one.remove(one.size()-1);
                swap(num, start, i);
            }
        }
    }

    private void swap(int[] num, int i, int j) {
        if(i == j)
            return;
            
        num[i] ^= num[j];
        num[j] ^= num[i];
        num[i] ^= num[j];
    }

    // memory limit exceeded
	public ArrayList<ArrayList<Integer>> permutexxxx(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (num == null || num.length == 0) {
			return result;
		}

		ArrayList<Integer> indices = new ArrayList<Integer>();
		ArrayList<Integer> one = new ArrayList<Integer>();
		int index = 0;
		do {
			while (one.size() < num.length && index < num.length) {
				if (!indices.contains(index)) {
					one.add(num[index]);
					indices.add(index);
				}

				index = (index + 1) % num.length;
			}

			if (one.size() == num.length)
				result.add(new ArrayList<Integer>(one));

			one.remove(one.size() - 1);
			index = indices.remove(indices.size() - 1) + 1;
		} while (one.size() > 0 || index < num.length);

		return result;
	}

	public ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (num == null || num.length == 0) {
			return result;
		}

		ArrayList<Integer> one = new ArrayList<Integer>();

		permute(num, 0, result, one);
		return result;
	}

	private void permute(int[] num, int start,
			ArrayList<ArrayList<Integer>> result, ArrayList<Integer> one) {
		if (one.size() == num.length) {
			result.add(new ArrayList<Integer>(one));
			return;
		}

		for (int i = start; i < (num.length + start); i++) {
			int index = i % num.length;
			if (!one.contains(num[index])) {
				one.add(num[index]);

				permute(num, i + 1, result, one);

				one.remove(one.size() - 1);
			}
		}
	}
}
