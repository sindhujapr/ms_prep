package lc;

import java.util.ArrayList;
import java.util.List;

public class LongestValidParentheses {
	public static void main(String[] args) {
		System.out.println(longestValidParentheses("()(())"));
		System.out.println(longestValidParentheses(")(((((()())()()))()(()))("));
		System.out.println(longestValidParentheses1(")(((((()())()()))()(()))("));
	}

    public static int longestValidParentheses(String s) {
        assert s != null;
        
        List<Integer> stack = new ArrayList<Integer>();
        int[] left = new int[s.length()];
        for(int i = 0; i < left.length; i++)
            left[i] = -1;
        
        int maxLen = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.add(i);
            } else if(stack.size() > 0) {
                left[i] = stack.remove(stack.size()-1);
                int temp = left[i]-1;
                if(temp >= 0 && left[temp] >= 0)
                    left[i] = left[temp];
                
                maxLen = Math.max(maxLen, i-left[i]+1);
            }
        }
        
        return maxLen;
    }

	// slightly different from the above implementation
	public static int longestValidParentheses1(String s) {
		char[] chs = s.toCharArray();
		List<Integer> stack = new ArrayList<Integer>();
		int[] left = new int[chs.length];
		
		// must!
		for(int i = 0; i < left.length; i++)
		    left[i] = -1;

		int res = 0;
		for (int i = 0; i < chs.length; i++) {
			if (chs[i] == '(') {
				stack.add(i);
			} else {
				if (!stack.isEmpty()) {
					left[i] = stack.remove(stack.size() - 1);
					int temp = left[i] - 1;
					if (temp >= 0 && left[temp] >= 0)
						left[i] = left[temp];

					res = Math.max(i - left[i] + 1, res);
				}
			}
		}
		return res;
	}
}