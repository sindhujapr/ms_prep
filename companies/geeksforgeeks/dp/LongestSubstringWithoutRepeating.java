package geeksforgeeks.dp;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeating {
	public static void main(String[] args) {
		System.out.println(longestSubstring("GEEKSFORGEEKS"));
		System.out.println(longest("GEEKSFORGEEKS"));
	}
	
	public static int longestSubstring(String str) {
		char[] chars = str.toCharArray();
		int start = 0;
		int maxLen = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < chars.length; i++) {
			if(!map.keySet().contains(chars[i])) {
				map.put(chars[i], i);
			} else {
				int pos = map.get(chars[i]);
				for(int j = start; j <= pos; j++)
					map.remove(chars[j]);
				start =  pos+ 1;
			}
			maxLen = Math.max(maxLen, i-start);
		}
		
		return maxLen;
	}
	
	/*
	 * better implementation from:
	 * http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
	 * Note that the below implementation only supports capitals!!!
	 */
	
	public static int longest(String str) {
		char[] chars = str.toCharArray();
		int maxLen = 1;
		int curLen = 1;
		int[] visited = new int[26];
		visited[0] = 0;
		
		// MUST initialized the array!
		for(int i = 1; i < visited.length; i++)
			visited[i] = -1;
		
		for(int i = 1; i < chars.length; i++) {
			int pos = visited[chars[i]-'A'];
			if(pos == -1 || i - curLen > pos) {
				curLen++;
			} else {
				maxLen = Math.max(maxLen, curLen);
				curLen = i - pos;
			}
			visited[chars[i]-'A'] = i;
		}
		
		maxLen = Math.max(maxLen, curLen);
		return maxLen;
	}
}