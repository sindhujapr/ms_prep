package lc;

import java.util.Set;

public class WordBreak {
    /*
     * http://gongxuns.blogspot.com/2013/10/word-break.html
     */
    public boolean wordBreak(String s, Set<String> dict) {
        assert s != null;
            
        boolean[] flag = new boolean[s.length()+1];
        flag[0] = true;
        
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if(flag[j] && dict.contains(s.substring(j, i))) {
                    flag[i] = true;
                    break;
                }
            }
        }

        return flag[s.length()];
    }
    
    /*
     * a little bit lengthy but easier to understand
     */
    public boolean wordBreak1(String s, Set<String> dict) {
        assert s != null;
        
        int n = s.length();
        // we actually use only half of the matrix
        boolean[][] flag = new boolean[n+1][n+1];
        for(int len = 1; len <= n; len++) {
            for(int start = 0; start+len <= n; start++) {
                String str = s.substring(start, start+len);
                if(dict.contains(str)) {
                    flag[start][start+len] = true;
                    continue;
                }
                
                for(int i = start+1; i < start+len; i++)
                    if(flag[start][i] && flag[i][start+len])
                        flag[start][start+len] = true;
            }
        }

        return flag[0][n];
    }
}
