package careercup.fb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * http://www.careercup.com/question?id=12718665
 * 
 * To use dynamic programming, we must have overlapping subproblem and 
 * optimal substructure, which means the optimal solution to the original problem
 * can be achieved by "combining" the optimal solutions of subproblems. However,
 * we cannot use DP for this because, for a given one more character, we don't
 * know which solution to the subproblem will yield to the overall optimal solution.
 * In another words, we have to iterate through all the solutions to the
 * subproblem and check which one is the optimal. However, we can still optimize the
 * algorithm by memorizing the solutions to subproblem to avoid re-cacluation.
 * 
 * There seems like another very easy solution:
 * Count the number of occurrences of each letter in the input string [numA, numB, numC] 
 * If two of these counts are 0, then return string.length 
 * Else if (all counts are even) or (all counts are odd), then return 2 
 * Else, then return 1
 */
public class StringReduction {
    public static void main(String[] args) {
        reduce("aaa");
        reduce("bb");
        
        reduce("abc");
        reduce("ababcc");
        reduce("aabbcc");
        reduce("aabbccabc");
        
        reduce("abca");
        reduce("bcca");
        reduce("abbccc");
        reduce("abcbbacaa");
    }

    public static void reduce(String str) {
        Map<String, Set<String>> map = initMap();
        
        Set<String> set = reduce(str, map);
        String shortest = null;
        for(String s : set)
            if(shortest == null || s.length() < shortest.length())
                shortest = s;
        
        System.out.println(str + " result\t\t: " + shortest);
        for(String s : map.keySet())
            System.out.println(s + "\t\t: " + map.get(s));
    }
    
    private static Map<String, Set<String>> initMap() {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        
        Set<String> set = new HashSet<String>();
        set.add("c");
        map.put("ab", set);
        map.put("ba", set);
        
        set = new HashSet<String>();
        set.add("b");
        map.put("ac", set);
        map.put("ca", set);
        
        set = new HashSet<String>();
        set.add("a");
        map.put("cb", set);
        map.put("bc", set);
        
        return map;
    }

    /*
     * basic conditions:
     * 1. The string contains only two different letters (length===2)
     * 2. The string contains only only one kind of letter (length may vary)
     * 3. The map already contains the key
     */
    private static Set<String> reduce(String str, Map<String, Set<String>> map) {
        int len = str.length();
        if(map.containsKey(str))
            return map.get(str);
        
        Set<String> result = new HashSet<String>();
        int j;
        for(j = 1; j < len ; j++)
            if(str.charAt(j) != str.charAt(0))
                break;
        if(j == len) {
            result.add(str);
            map.put(str, result);
            return result;
        }
        
        
        for(int i = 1; i < len; i++) {
            String left = str.substring(0, i);
            String right = str.substring(i);
            
            Set<String> leftReduce = reduce(left, map);
            Set<String> rightReduce = reduce(right, map);
            for(String l : leftReduce) {
                for(String r : rightReduce) {
                    String merge = l+r;
                    /*
                     * to avoid stack overflow for input such as "abb":
                     * when i == 1, left = a, right = bb, we will be in
                     * an infinite loop for "abb"
                     */
                    if(!merge.equals(str))
                        result.addAll(reduce(merge, map));
                }
            }
        }

        map.put(str, result);
        return result;
    }
}