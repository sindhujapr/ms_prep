package lc;

import java.util.ArrayList;

// see also 76 of http://se7so.blogspot.com/2014/02/how-to-prepare-for-interview-8.html
public class GrayCode {
	public static void main(String[] args) {
		List<Integer> result = new GrayCode().grayCode(5);
		for(Integer i : result)
			System.out.print(i + ", ");
	}

	/*
	 * http://en.wikipedia.org/wiki/Gray_code
	 */
	List<Integer> grayCode(int n) {
		List<Integer> ret = new ArrayList<Integer>();
		int size = 1 << n;
		for (int i = 0; i < size; ++i)
			ret.add((i >> 1) ^ i);
		return ret;
	}

	/*
	 * http://gongxuns.blogspot.com/2012/12/leetcode-gray-code.html
	 */
	public List<Integer> grayCode1(int n) {
		List<Integer> prev = new ArrayList<Integer>();
		
		for (int i = 0; i < n; i++) {
			List<Integer> cur = new ArrayList<Integer>();
			for (Integer temp : prev) {
				cur.add(temp);
			}
			cur.add(i + 1);
			for (Integer temp : prev) {
				cur.add(temp);
			}
			prev = cur;
		}

		int start = 0;
		List<Integer> res = new ArrayList<Integer>();
		res.add(start);

		for (Integer temp : prev) {
			start = flipAtPosition(start, temp);
			res.add(start);
		}
		return res;
	}

	public int flipAtPosition(int a, int k) {
		int temp = (a >> (k - 1)) & 1;
		if (temp == 1)
			return a - (1 << (k - 1));
		else
			return a + (1 << (k - 1));
	}
}
