package lc;

import java.util.ArrayList;
import java.util.List;

public class ValidParentheses {
	/*
	 * we can optimize to improve the performance for 50% cases:
	 * if the number of characters is odd, then it's invalid
	 */
    public boolean isValid(String s) {
        List<Character> list = new ArrayList<Character>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{') {
                list.add(ch);
                continue;
            }
            
            if(list.size() == 0)
                return false;
            
            char top = list.remove(list.size()-1);
            if((ch == ')' && top != '(') || (ch == ']' && top != '[') || (ch == '}' && top != '{'))
                return false;
        }
        
        return list.isEmpty();
    }

	/*
	 * from Google interview: use O(1) space to check whether the input string is valid (only ()).
	 * I haven't verified it.
	 */
    public static boolean check(String s) {
        int left = 0, right = 0;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(')
                left++;
            else
                right++;

            if(left < right)
                return false;
        }

        return left == right;
    }
}
