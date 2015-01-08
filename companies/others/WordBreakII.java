package test;

import java.util.*;

/**
 * Created by qingcunz on 10/25/14.
 */
public class WordBreakII {
    public static void main(String[] args) {
        String[] strs = new String[] {"cat","cats","and","sand","dog"};

        Set<String> dict = new HashSet<String>();
        for(String str : strs)
            dict.add(str);

        List<String> res = new WordBreakII().wordBreak("catsanddog", dict);
        for(String str : res)
            System.out.println(str);
    }

    public List<String> wordBreak(String s, Set<String> dict) {
        int n = s.length();
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        boolean[] flag = new boolean[n+1];
        flag[0] = true;
        for(int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<Integer>());
            for(int j = 0; j < i; j++) {
                if(flag[j] && dict.contains(s.substring(j, i))) {
                    map.get(i).add(j);
                    flag[i] = true;
                    break;
                }
            }
        }

        return find(map, s, n);
    }

    private List<String> find(Map<Integer, List<Integer>> map, String s, int n) {
        List<String> res = new ArrayList<String>();

        for(int cut : map.get(n)) {
            if(cut == 0) {
                res.add(s.substring(0, n));
            } else {
                List<String> temp = find(map, s, cut);
                for(String str : temp)
                    res.add(str + " " + s.substring(cut, n));
            }
        }

        return res;
    }
}
