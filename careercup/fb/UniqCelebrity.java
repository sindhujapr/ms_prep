package careercup.fb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * http://www.careercup.com/question?id=15503949
 * 
 * The same as Influencier
 */
public class UniqCelebrity {
    public static void main(String[] args) {
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        Set<Integer> set = new HashSet<Integer>();
        set.add(1);
        set.add(2);
        map.put(0, set);
        
        set = new HashSet<Integer>();
        set.add(3);
        set.add(2);
        map.put(1, set);
        
        set = new HashSet<Integer>();
        set.add(0);
        set.add(2);
        map.put(3, set);
        
        int[] people = {0, 1, 2, 3};
        System.out.println(celeberity(map, people));
    }

    // there is at most one celebrity
    public static int celeberity(Map<Integer, Set<Integer>> map, int[] people) {
        assert map != null && people != null;

        int cele = -1;
        for(int i = 0; i < people.length; i++) {
            if(cele == -1 || map.containsKey(cele) && map.get(cele).contains(i)) {
                cele = i;
            }
        }
        
        for(int i = 0; cele != -1 && i < people.length; i++)
            if(cele != i && (!map.containsKey(i) || !map.get(i).contains(cele)))
                return -1;
        
        return cele;
    }
}