package lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {
	/*
	 * http://gongxuns.blogspot.com/2012/12/leetcode-substring-with-concatenation.html
	 */
	public List<Integer> findSubstring(String S, String[] L) {
		if (L == null || L.length == 0)
			return null;
		int n = L.length, m = L[0].length(), l = S.length();
		List<Integer> res = new ArrayList<Integer>();

		Map<String, Integer> covered = new HashMap<String, Integer>();
		for (int j = 0; j < n; j++)
			covered.put(L[j], covered.containsKey(L[j]) ? covered.get(L[j]) + 1 : 1);

		int i = 0;
		while (l - i >= n * m) {
			Map<String, Integer> temp = new HashMap<String, Integer>(covered);
			for (int j = 0; j < n; j++) {
				String testStr = S.substring(i + j * m, i + (j + 1) * m);
				if (temp.containsKey(testStr)) {
					if (temp.get(testStr) == 1)
						temp.remove(testStr);
					else
						temp.put(testStr, temp.get(testStr) - 1);
				} else
					break;
			}
			if (temp.size() == 0)
				res.add(i);
			i++;
		}
		return res;
	}
    
	public static void main(String[] args) {
		ArrayList<Integer> result = new SubstringWithConcatenationOfAllWords().
				findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
		System.out.println(result);
	}

	/*
	 * inefficient!!!
	 */
	public ArrayList<Integer> findSubstring1(String S, String[] L) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		int length = 0;
		for (String word : L)
			length += word.length();

		/*
		 * +1!!
		 */
		for (int i = 0; i < S.length()-length+1; i++) {
			List<String> usedString = new ArrayList<String>();
			if (isPermutation(S, i, L, usedString))
				result.add(i);
		}
		return result;
	}

	private boolean isPermutation(String S, int start, String[] L,
			List<String> usedString) {
		if (usedString.size() == L.length)
			return true;

		if (start > S.length())
			return false;

		String subString = S.substring(start);
		String found = null;
		for (String word : L) {
			/*
			 * there may be duplicate words in L!!
			 */
			if (subString.startsWith(word) && !contains(usedString, L, word)) {
				if (found == null || word.length() > found.length())
					found = word;
			}
		}

		if (found != null) {
			usedString.add(found);
			return isPermutation(S, start + found.length(), L, usedString);
		}
		return false;
	}
	
	private boolean contains(List<String> list, String[] words, String word) {
		if(!list.contains(word))
			return false;
		
		int cnt1 = 0;
		for(String str : words) {
			if(str.equals(word))
				cnt1++;
		}
		
		int cnt2 = 0;
		for(String str : list) {
			if(str.equals(word))
				cnt2++;
		}
		
		if(cnt1 > cnt2)
			return false;
	
		return true;
	}
}
