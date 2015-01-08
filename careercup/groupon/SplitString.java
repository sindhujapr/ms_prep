package careercup.groupon;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.careercup.com/question?id=20014662
 * 
 * Given a string T of length n, partition it in n' "phrases" (p1, p2, ..., pn'), such that 
 * pi = pj + c, for some j<i, where + is string concatenation and c is a character 
 */
public class SplitString {
    public static void main(String[] args) {
        split("ababababababcabababcd");
    }

    /*
     * suppose the input string is valid, which means it's splitable
     * this algorithm only finds one split
     */
    public static void split(String str) {
        assert str != null && str.length() > 0;
        
        char[] s = str.toCharArray();
        List<Integer> indices = new ArrayList<Integer>();
        for(int i = 0; i < s.length; i++)
            if(s[i] == s[0])
                indices.add(i);
        
        if(indices.size() == 1) {
            System.out.println(str);
            return;
        }

        int i = 1;
        for(; i < indices.size(); i++) {
            // check
            int gap = indices.get(i);
            int next = gap;
            while(next < s.length) {
                if(s[next] != s[0])
                    break;
                
                gap++;
                next += gap;
            }
            
            if(next >= s.length)
                break;
        }
        
        int gap = indices.get(i);
        int next = gap;
        int cur = 0;
        while(next <= s.length) {
            System.out.println(str.substring(cur, next));
            gap++;
            cur = next;
            next += gap;
        }
    }
}