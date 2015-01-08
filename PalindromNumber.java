package lc;

public class PalindromNumber {
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;

		int count = count(x);
		for (int i = 0; i < count / 2; i++) {
			int v1 = (x / power(count - i - 1)) % 10;
			int v2 = (x / power(i)) % 10;
			if (v1 != v2)
				return false;
		}

		return true;
	}

	private int power(int n) {
		int value = 1;
		while (n-- > 0)
			value *= 10;
		return value;
	}

	private int count(int x) {
		int cnt = 0;
		while (x != 0) {
			x /= 10;
			cnt++;
		}
		return cnt;
	}
}