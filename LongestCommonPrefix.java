package lc;

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";

		for (int index = 0;; index++) {
			for (int i = 0; i < strs.length; i++) {
				if (strs[i].length() == index)
					return strs[i];

				if (i > 0 && strs[i].charAt(index) != strs[i - 1].charAt(index))
					return strs[i].substring(0, index);
			}
		}
	}

	public String longestCommonPrefix1(String[] strs) {
		assert strs != null;
		if (strs.length == 0)
			return "";

		// compare one character every time
		int i = 0;
		for (;; i++) {
			boolean found = true;
			for (int j = 0; j < strs.length; j++) {
				if (strs[j] == null || strs[j].length() <= i || (j > 0 && strs[j].charAt(i) != strs[j-1].charAt(i))) {
					found = false;
					break;
				}
			}

			if (!found)
				break;
		}

		return strs[0].substring(0, i);
	}
}
