package careercup.fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * http://www.careercup.com/question?id=14921707
 */
public class RearrangeNumbers {
    public static void main(String[] args) {
        int[] array = {19,17,27,82,80,90};
        rearrange(array, 3, 63);
    }
    
    public static void rearrange(int[] array, int k, int num) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < array.length; i++) {
            int mod = array[i] % num;
            if(map.containsKey(mod)) {
                map.get(mod).add(array[i]);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(array[i]);
                map.put(mod, list);
            }
        }
        
        // make sure we have the necessary number of elements in each group
        int sum =0;
        int count = 0;
        for(Integer mod : map.keySet()) {
            if(count == 0)
                count = map.get(mod).size();
            else if(map.get(mod).size() != count)
                count = -1;
                
            sum += map.get(mod).get(0);
        }
        
        if(sum != num || map.size() != k || count == -1) {
            System.out.println("not found");
            return;
        }
        
        /*
         * Java doesn't guarantee iterate over the map in the same order
         * for different run. So it's better we have control over it.
         */
        int[] keys = new int[map.size()];
        int j = 0;
        for(Integer mod : map.keySet())
            keys[j++] = mod;
            
        for(int i = 0; i < count; i++) {
            for(int m = 0; m < keys.length; m++) {
                int key = keys[m];
                List<Integer> list = map.get(key);
                System.out.print(list.get(i) + ", ");
            }
        }
    }
}