package careercup.fb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * http://www.careercup.com/question?id=65732
 */
public class DivideArrayInGroups {
    public static void main(String[] args) {
        int[] array = {8,2,4,7,1,0,3,6};
        divide(array);
        
        int[] array1 = {8, 11, 3, 10, 9, 7, 2, 1};
        divide(array1);
    }
    
    public static void divide(int[] array) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < array.length; i++) {
            int left = array[i]-1;
            int right = array[i]+1;

            // deal with duplicate elements
            if(map.containsKey(array[i])) {
                map.get(array[i]).add(array[i]);
                continue;
            }
            
            if(map.containsKey(left) && map.containsKey(right)) {
                List<Integer> lList = map.get(left);
                List<Integer> rList = map.get(right);
                lList.addAll(rList);
                lList.add(array[i]);
                
                map.put(array[i], lList);
                
                for(Integer value : rList)
                    map.put(value, lList);
            } else if(map.containsKey(left) && !map.containsKey(right)) {
                map.get(left).add(array[i]);
                map.put(array[i], map.get(left));
            } else if(!map.containsKey(left) && map.containsKey(right)) {
                map.get(right).add(array[i]);
                map.put(array[i], map.get(right));
            } else {
                // this is more efficient if we need to move elements
                List<Integer> list = new LinkedList<Integer>();
                list.add(array[i]);
                map.put(array[i], list);
            }
        }
        
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for(Integer value : map.keySet())
            set.add(map.get(value));
        
        for(List<Integer> list : set)
            System.out.println(list);
    }
}