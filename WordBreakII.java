package lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * adapted from:
 * http://gongxuns.blogspot.com/2013/10/word-break-ii.html
 */
public class WordBreakII {
    public static void main(String[] args) {
        Set<String> dict = new HashSet<String>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        
        ArrayList<String> res = new WordBreakII().wordBreak("catsanddog", dict);
        for(String str : res)
            System.out.println(str);
    }
    
    // find all the break point and then use backtrack to get all permutations
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if((j == 0 || map.containsKey(j)) && dict.contains(s.substring(j, i))) {
                    if(!map.containsKey(i))
                        map.put(i, new ArrayList<Integer>());
                    map.get(i).add(j);
                }
            }
        }
        
        return backtrack(s, map, s.length());
    }

    private ArrayList<String> backtrack(String s, Map<Integer, List<Integer>> map, int index) {
        ArrayList<String> res = new ArrayList<String>();
        if(!map.containsKey(index))
            return res;
        
        for(int cut : map.get(index)) {
            if(cut == 0) {
                res.add(s.substring(0, index));
            } else {
                ArrayList<String> prev = backtrack(s, map, cut);
                for(String str : prev)
                    res.add(str + " " + s.substring(cut, index));
            }
        }
        return res;
    }
}