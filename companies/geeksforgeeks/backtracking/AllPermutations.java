package geeksforgeeks.backtracking;

import java.util.ArrayList;
import java.util.List;

public class AllPermutations {
    public static void main(String[] args) {
//      permutations("abc");
        permute("1212");
    }
    
    public static void permutations(String str) {
        assert str != null && str.length() > 0;

        char[] s = str.toCharArray();

        List<StringBuilder> list = new ArrayList<StringBuilder>();
        StringBuilder build = new StringBuilder();
        build.append(s[0]);
        list.add(build);

        for(int j = 1; j < s.length; j++) {
            char ch = s[j];
            List<StringBuilder> temp = new ArrayList<StringBuilder>();
            for(StringBuilder builder : list) {
                for(int i = 0; i <= builder.length(); i++) {
                    StringBuilder sb = new StringBuilder(builder.toString());
                    sb.insert(i, ch);
                    temp.add(sb);
                }
            }
            list = temp;
        }
        
        for(StringBuilder builder : list)
            System.out.println(builder.toString());
    }
    
    public static void permute(String str) {
        char[] s = str.toCharArray();
//      permute(s, 0, s.length);
        permute_noduplicate(s, 0, s.length);
    }

    @SuppressWarnings("unused")
    private static void permute(char[] s, int i, int n) {
        if(i == n) {
            System.out.println(s);
            return;
        }
        
        for(int j = i; j < n; j++) {
            swap(s, i, j);
            permute(s, i+1, n);
            swap(s, i, j);
        }
    }

    private static void swap(char[] s, int i, int j) {
        if(i == j)
            return;
        
        s[i] ^= s[j];
        s[j] ^= s[i];
        s[i] ^= s[j];
    }
    
    /*
     * very nice solution!
     * 
     * http://ideone.com/acjCxb
     */
    private static void permute_noduplicate(char[] s, int i, int n) {
        if(i == n) {
            System.out.println(s);
            return;
        }
        
        for(int j = i; j < n; j++) {
            /*
             * it means we have placed the same character as s[j] at 
             * position i. In another words, we have ever permute on
             * the same set of characters s[i, ..., j].
             */
            if(match(s, i, j))
                continue;

            swap(s, i, j);
            permute_noduplicate(s, i+1, n);
            swap(s, i, j);
        }       
    }

    private static boolean match(char[] s, int i, int j) {
        // this is an exception!!!
        if(i == j)
            return false;
        
        for(; i < j; i++)
            if(s[i] == s[j])
                return true;

        return false;
    }
}