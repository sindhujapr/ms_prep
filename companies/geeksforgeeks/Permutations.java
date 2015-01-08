package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 */
public class Permutations {
    public static void main(String[] args) {
        for(String str : permutation("abcd"))
            System.out.println(str);
        
        System.out.println("recursion: ");
        permutation_recur("abcd");
    }
    
    public static List<String> permutation(String s) {
        List<StringBuilder> result = new ArrayList<StringBuilder>();
        result.add(new StringBuilder());

        for(int i = 0; i < s.length(); i++) {
            List<StringBuilder> temp = new ArrayList<StringBuilder>();
            
            char ch = s.charAt(i);
            for(StringBuilder builder : result) {
                for(int j = 0; j <= builder.length(); j++) {
                    StringBuilder sb = new StringBuilder(builder.toString());
                    sb.insert(j, ch);
                    temp.add(sb);
                }
            }
            
            result = temp;
        }
        
        List<String> list = new ArrayList<String>();
        for(StringBuilder builder : result)
            list.add(builder.toString());
        return list;
    }
    
    public static void permutation_recur(String s) {
        permutation_recur(s, 0, s.length()-1);
    }
    public static void permutation_recur(String s, int start, int end) {
        if(start >= end) {
            System.out.println(s);
            return;
        }
        
        for(int i = start; i <= end; i++) {
            StringBuilder sb = new StringBuilder(s);
            char ch = s.charAt(start);
            sb.setCharAt(start, s.charAt(i));
            sb.setCharAt(i, ch);
            
            permutation_recur(sb.toString(), i+1, end);
        }
    }
}