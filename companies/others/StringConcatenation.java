package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qingcunz on 9/21/14.
 */
public class StringConcatenation {
    public static void main(String[] args) {
        List<Integer> res = new StringConcatenation().findSubstring("a", new String[] {"a"});
        for(Integer i : res)
            System.out.println(i);
    }

    public List<Integer> findSubstring(String S, String[] L) {
        int m = S.length(), n =L.length, l = L[0].length();

        List<Integer> res = new ArrayList<Integer>();

        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < n; i++)
            map.put(L[i], map.containsKey(L[i]) ? map.get(L[i])+1 : 1);

        for(int i = 0; i < m-n*l; i++) {
            // check if S:[i, i+n*l] includes all string in the map
            Map<String, Integer> copy = new HashMap<String, Integer>(map);

            for(int k = 0; k < n; k++) {
                String str = S.substring(i+k*l, i+(k+1)*l);
                if(copy.containsKey(str)) {
                    if(copy.get(str) == 1) {
                        copy.remove(str);
                    } else {
                        copy.put(str, copy.get(str)-1);
                    }
                } else {
                    break;
                }
            }

            if(copy.size() == 0) {
                res.add(i);
            }
        }

        return res;
    }
}
