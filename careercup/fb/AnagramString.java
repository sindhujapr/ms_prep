package careercup.fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * http://www.careercup.com/question?id=5111068527427584
 */
public class AnagramString {
    public static void main(String[] args) {
        String[] array = {"star", "rats", "ice", "cie", "arts"};
        List<String> list = new ArrayList<String>();
        for(String str : array)
            list.add(str);
        
        findAnagrams(list);
    }

    public static void findAnagrams(List<String> list) {
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        for(String str : list) {
            int hash = hash(str);
            if(!map.containsKey(hash))
                map.put(hash, new ArrayList<String>());
            map.get(hash).add(str);
        }
        
        for(Integer hash : map.keySet()) {
            if(map.get(hash).size() > 1)
                System.out.println(map.get(hash).toString());
        }
    }
    
    private static int hash(String str) {
        int result = 0;
        for(char c : str.toCharArray())
            result += (int) c * (int) c;

        return result;
    }
}