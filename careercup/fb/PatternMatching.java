package careercup.fb;

/*
 * http://www.careercup.com/question?id=19587667
 * 
 * Not all test cases pass. Seems like the question is confusing
 */
public class PatternMatching {
    public static void main(String[] args) {
        String pattern = "a*b";
//      String[] s1 = {"aaab", "ab", "aab", "aaab", "ab"};
//      for(int i = 0; i < s1.length; i++)
//          System.out.println(isMatch(pattern, s1[i]));
        
//      pattern = "a+aabc"; 
//      String[] s2 = {"abaabc", "aaabc", "aaaabc"};
//      for(int i = 0; i < s2.length; i++)
//          System.out.println(isMatch(pattern, s2[i]));
//      
        pattern = "aa*b*ab+"; 
        String[] s3 = {"aabaab"}; 
        for(int i = 0; i < s3.length; i++)
            System.out.println(isMatch(pattern, s3[i]));
//      
//      pattern = "a+a*b*"; 
//      String[] s4 = {"a ab", "aab", "aaabb"}; 
//      for(int i = 0; i < s4.length; i++)
//          System.out.println(isMatch(pattern, s4[i]));
        
        
    }
    
    // Suppose the input parameters are valid
    public static boolean isMatch(String pattern, String source) {
        assert pattern != null && source != null;
        
        char[] p = pattern.toCharArray();
        char[] s = source.toCharArray();
        
        int m = p.length, n = s.length;
        boolean[][] flag = new boolean[m+1][n+1];
        flag[0][0] = true;
        for(int i = 1; i < n+1; i++)
            flag[0][i] = false;
        for(int i = 1; i < m+1; i++)
            flag[i][0] = flag[i-1][0] && p[i-1] == '*';
        
        for(int i = 1; i < m+1; i++) {
            for(int j = 1; j < n+1; j++) {
                if(p[i-1] == s[j-1] && flag[i-1][j-1])
                    flag[i][j] = true;

                if((p[i-1] == '*' && flag[i-1][j]) || (i > 1 && s[j-1] == p[i-2] && p[i-1] == '*' && (flag[i][j-1] || flag[i-2][j-1])))
                    flag[i][j] = true;
                else if(i > 1 && s[j-1] == p[i-2] && p[i-1] == '+' && (flag[i-2][j-1] || flag[i][j-1]))
                    flag[i][j] = true;
                
                if(flag[i][j])
                    System.out.println(pattern.substring(0, i) + "\t" + source.substring(0, j));
            }
        }
        
        return flag[m][n];
    }
}