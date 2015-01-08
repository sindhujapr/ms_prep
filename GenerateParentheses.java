package lc;

import java.util.ArrayList;

public class GenerateParentheses {
	public static void main(String[] args) {
		ArrayList<String> result = new GenerateParentheses().generateParenthesis(3);
		for (String str : result)
			System.out.println(str);
	}


	// easier to understnad: http://blog.csdn.net/fightforyourdream/article/details/14159435
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        generate(0, 0, n, "", res);
        return res;
    }
    
    private void generate(int left, int right, int n, String str, List<String> res) {
		//invariant: the number of '(' must be greater than the number of ')'
        if(left < right)
            return;
            
        if(left == n && right == n) {
            res.add(str);
            return;
        }
        
		// if we have enough '(', then we can only use ')'
        if(left == n) {
            generate(left, right+1, n, str+")", res);
            return;
        }
        
        generate(left+1, right, n, str+"(", res);
        generate(left, right+1, n, str+")", res);
    }

	/*
	 * http://gongxuns.blogspot.com/2012/12/leetcode-generate-parentheses.html
	 */
	public ArrayList<String> generateParenthesis(int n) {
		ArrayList<String> res = new ArrayList<String>();
		ArrayList<Integer> diff = new ArrayList<Integer>();
		res.add("");
		diff.add(0);

		for (int i = 0; i < 2 * n; i++) {
			ArrayList<String> temp1 = new ArrayList<String>();
			ArrayList<Integer> temp2 = new ArrayList<Integer>();
			for (int j = 0; j < res.size(); j++) {
				String s = res.get(j);
				int k = diff.get(j);
				if (i < 2 * n - 1) {
					temp1.add(s + "(");
					temp2.add(k + 1);
				}

				if ((k == 1 && i == 2 * n - 1) || (k > 0 && i < 2 * n - 1)) {
					temp1.add(s + ")");
					temp2.add(k - 1);
				}
			}
			res = temp1;
			diff = temp2;
		}

		return res;
	}

	public ArrayList<String> generateParenthesis2(int n) {
		ArrayList<String> result = new ArrayList<String>();
		if (n == 0)
			return result;

		StringBuilder builder = new StringBuilder();
		find(0, n, builder, result);
		return result;
	}

	private void find(int start, int end, StringBuilder builder, ArrayList<String> result) {
		int cnt = 0;
		for (int i = 0; i < builder.length() - 1; i++)
			if (builder.charAt(i) == '(')
				cnt++;

		if (cnt == end) {
			result.add(builder.toString());
			return;
		}

		// fix the position for '('
		int lpos = start;
		for (int j = start; j < 2 * end; j++) {
			if (j >= builder.length() || (builder.charAt(j) != '(' && builder.charAt(j) != ')')) {
				lpos = j;
				break;
			}
		}
		if (lpos >= builder.length())
			builder.append('(');
		else
			builder.setCharAt(lpos, '(');

		for (int rpos = lpos + 1; rpos < 2 * end; rpos += 2) {
			if (rpos >= builder.length())
				builder.append(')');
			else
				builder.setCharAt(rpos, ')');

			find(lpos + 1, end, builder, result);
			builder.setCharAt(rpos, '-');

			/*
			 * ((-), in this case, we cannot append ')' since there is a ')'
			 * before it.
			 */
			if (rpos + 1 < builder.length()
					&& (builder.charAt(rpos + 1) == ')' || builder.charAt(rpos + 1) == '('))
				break;
		}
		builder.setCharAt(lpos, '-');
	}
}
