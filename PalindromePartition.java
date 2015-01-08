package lc2;

import java.util.ArrayList;
import java.util.List;

/*
 * http://blog.sina.com.cn/s/blog_b9285de20101jbtl.html
 */
public class PalindromePartition {
	public static void main(String[] args) {
		PalindromePartition instance = new PalindromePartition();
		ArrayList<ArrayList<String>> result = instance.partition2("ababaca");
		for (ArrayList<String> one : result)
			System.out.println(one);
		// instance.partition2("aba");
	}

	ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>();

	boolean isPalin(String s, int i, int j) {
		/*
		 * if i==j, there is only one char and definitely it's a palindrome but
		 * we need to make sure i <= j
		 */
		while (i < j) {
			if (s.charAt(i) != s.charAt(j))
				return false;
			i++;
			j--;
		}
		return true;
	}

	void dfs(String s, int start, ArrayList<String> al) {
		/*
		 * we are reaching the end of the string and thus one permutation is
		 * found
		 */
		if (start == s.length()) {
			/*
			 * copy the elements. otherwise it will be changed
			 */
			all.add(new ArrayList<String>(al));
			return;
		}

		/*
		 * starting from (start+1) so at least we will evaluate the next ONE
		 * char
		 */
		for (int i = start + 1; i <= s.length(); i++) {
			/*
			 * if [start, i) is palindrome, add it and evaluate the remaining
			 * substring starting from i.
			 */
			if (isPalin(s, start, i - 1)) {
				System.out.println("iter" + i + " add " + s.substring(start, i));
				al.add(s.substring(start, i));
				dfs(s, i, al);
				/*
				 * when the above call returns, we have just found all
				 * permutations for split point (i) and thus we need to
				 * "put back" the last added palindrome (to the original string)
				 * so that we can consider substring [start, i+1).
				 */
				System.out.println("iter" + i + " rem " + al.get(al.size() - 1));
				al.remove(al.size() - 1);
			}
		}
	}

	public ArrayList<ArrayList<String>> partition(String s) {
		ArrayList<String> al = new ArrayList<String>();
		dfs(s, 0, al);
		return all;
	}

    public List<List<String>> partition2(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();
        List<Integer> stack = new ArrayList<Integer>();
        int index = 1, start = 0;
        
        do {
            while(index <= s.length()) {
                String sub = s.substring(start, index);
                if(isPalindrome(sub)) {
                    list.add(sub);
                    stack.add(index);
                    
                    start = index++;
                } else {
                    index++;
                }
            }
            
            if(start == s.length())
                res.add(new ArrayList<String>(list));
            
            if(stack.size() > 0) {
            	index = stack.remove(stack.size()-1)+1;
            	list.remove(list.size()-1);
            }
            start = stack.size() > 0 ? stack.get(stack.size()-1) : 0;
        } while(index <= s.length() || stack.size() > 0);

        return res;
    }
    
    private boolean isPalindrome(String s) {
        int len = s.length();
        for(int i = 0; i < len/2; i++)
            if(s.charAt(i) != s.charAt(len-i-1))
                return false;
        return true;
    }

	private int findNextPalindrome(String s, int start) {
		for (int i = start; i < s.length(); i++) {
			if (isPalin(s, start, i))
				return i + 1;
		}

		return -1;
	}
}
