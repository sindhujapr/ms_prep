package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	public static void main(String[] args) {
		// ArrayList<String> result = new Anagrams().anagrams(new String[] {"h",
		// "h", "h"});
		ArrayList<String> result = new Anagrams().anagrams(new String[] { "ab", "ba" });
		System.out.println(result.size());
		for (String str : result)
			System.out.println(str);
	}

	/*
	 * algorithm from
	 * http://www.cnblogs.com/AnnieKim/archive/2013/04/25/3041982.html
	 */
	public ArrayList<String> anagrams(String[] strs) {
		ArrayList<String> result = new ArrayList<String>();
		if (strs == null || strs.length == 0)
			return result;

		Map<String, Integer> map = new HashMap<String, Integer>();
		boolean[] used = new boolean[strs.length];
		for (int i = 0; i < strs.length; i++) {
			char[] chs = strs[i].toCharArray();
			Arrays.sort(chs);
			String sortedChs = Arrays.toString(chs);
			
			Integer index = map.get(sortedChs);
			if (index == null) {
				map.put(sortedChs, i);
			} else {
				result.add(strs[i]);
				used[i] = true;

				if (!used[index]) {
					result.add(strs[index]);
					used[index] = true;
				}
			}
		}

		return result;
	}
}