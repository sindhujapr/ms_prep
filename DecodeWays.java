package lc;

public class DecodeWays {
	public static void main(String[] args) {
		DecodeWays instance = new DecodeWays();
		System.out.println(instance.numDecodings("11023"));
	}

	/*
	 * http://gongxuns.blogspot.com/2012/12/leetcodedecode-ways.html
	 * 
	 * this is actually DP problem:
	 * for the current index i, the # of ways to decode depends on 
	 * the # of ways to decode at index i-1 and i-2, which are denoted
	 * by hist[1] and hist[0] below.
	 * 1. if the current digit (at index i) is non-zero, then we can
	 * count in hist[1].
	 * 2. if the current digit combined with the previous digit falls
	 * between 1 and 26, then we can count in hist[0].
	 */
	public int numDecodings(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int[] hist = new int[2];
		hist[0] = 1;
		hist[1] = 1;

		for (int i = 0; i < s.length(); i++) {
			int temp = 0;
			if (s.charAt(i) != '0')
				temp += hist[1];

			if (i > 0 && s.charAt(i - 1) != '0' && Integer.parseInt(s.substring(i - 1, i + 1)) <= 26)
					temp += hist[0];
			
			hist[0] = hist[1];
			hist[1] = temp;
		}

		return hist[1];
	}
}