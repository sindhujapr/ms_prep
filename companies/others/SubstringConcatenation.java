package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qingcunz on 10/25/14.
 */
public class SubstringConcatenation {
    public static void main(String[] args) {
        List<Integer> res = new SubstringConcatenation().findSubstring("barfoothefoobarman", new String[]{"foo","bar"});
        for (Integer pos : res)
            System.out.println(pos);
    }

    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> res = new ArrayList<Integer>();
        int length = L.length * L[0].length();

        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String str : L)
            map.put(str, map.containsKey(str) ? map.get(str)+1 : 1);

        for(int i = 0; i+length <= S.length(); i++) {
            String str = S.substring(i, i+length);
            if(valid(str, map, L[0].length()))
                res.add(i);
        }
        return res;
    }

    private boolean valid(String str, Map<String, Integer> map, int len) {
        Map<String, Integer> copy = new HashMap<String, Integer>(map);
        for(int start = 0; start+len <= str.length(); start += len) {
            String s = str.substring(start, start+len);
            if(!copy.containsKey(s) || copy.get(s) == 0)
                return false;

            if(copy.get(s) == 1)
                copy.remove(s);
            else
                copy.put(s, copy.get(s)-1);
        }
        return copy.isEmpty();
    }
}
