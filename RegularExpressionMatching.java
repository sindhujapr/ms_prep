package lc;

public class RegularExpressionMatching {
	public static void main(String[] args) {
		System.out.println(new RegularExpressionMatching().isMatch2("aab", "c*a*b"));
	}

	/*
	 * http://wizardrichard.blogspot.com/2013/02/leetcode-regular-expression-matching.html
	 * We suppose there is always a preceding character before '*'.
	 * Otherwise the pattern string is illegal.
	 */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] flag = new boolean[m+1][n+1];
        flag[0][0] = true;
        for(int i = 1; i <= m; i++)
            flag[i][0] = false;
        for(int j = 1; j <= n; j++)
            flag[0][j] = p.charAt(j-1) == '*' ? flag[0][j-2] : false;

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(p.charAt(j-1) == '*') {
					/*
					 * if s[i-1] == p[j-2], we can use p[j-2,j-1] to match none from s, which is flag[i][j-2] and
					 * p[j-2, j-1] is left out, or we can use them to match one char from s, which is flag[i-1][j]
					 * 
					 * if s[i-1] != p[j-2], we cannot use p[j-2,j-1] to match anything from s. So it's flag[i][j-2]
					 */
                    flag[i][j] = match(s.charAt(i-1), p.charAt(j-2)) ? flag[i-1][j] || flag[i][j-2] : flag[i][j-2];
                } else {
                    flag[i][j] = match(s.charAt(i-1), p.charAt(j-1)) && flag[i-1][j-1];
                }
            }
        }
        return flag[m][n];
    }
    
    private boolean match(char s, char p) {
        return p == '.' || s == p;
    }

	boolean match(char a, char b) {
		return b == '.' || a == b;
	}

	/*
	 * http://gongxuns.blogspot.com/2012/12/leetcoderegular-expression-matching.html
	 */
	public boolean isMatch(String s, String p) {
		assert p != null && (p.length() == 0 || p.charAt(0) != '*');

		if (p.length() == 0)
			return s.length() == 0;

		if (p.length() == 1 || p.charAt(1) != '*') {
			if (s.length() == 0 || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0)))
				return false;

			return isMatch(s.substring(1), p.substring(1));
		} else {
			int i = -1;
			while (i < s.length() && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
				if (isMatch(s.substring(i + 1), p.substring(2)))
					return true;
				i++;
			}

			return false;
		}
	}
}
