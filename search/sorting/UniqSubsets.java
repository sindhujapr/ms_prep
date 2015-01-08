package search.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * http://www.careercup.com/question?id=16760663
 */
public class UniqSubsets {
    public static void main(String[] args) {
        int[] array = {2, 1, 2, 3, 1, 1};
        Set<List<Integer>> result = find(array, 3);
        for(List<Integer> list : result)
            System.out.println(list);
        
        System.out.println("Iter: ");
        int[] array1 = {2, 1, 2, 3, 1};
        Set<List<Integer>> result1 = find_iter(array1, 3);
        for(List<Integer> list : result1)
            System.out.println(list);
        
        System.out.println("find map");
        int[] array2 = {2, 1, 1, 3};
        find_map(array2);
    }
    
    public static Set<List<Integer>> find(int[] array, int K) {
        Arrays.sort(array);

        List<Integer> list = new ArrayList<Integer>();
        Set<List<Integer>> result = new HashSet<List<Integer>>();
        find(array, 0, K, list, result);
        return result;
    }

    private static void find(int[] array, int start, int K, List<Integer> list, Set<List<Integer>> result) {
        /*
         * this is to avoid duplicate but it simply doesn't work. however, it doesn't
         * affect the final result since I'm using Set.
         */
        if(start > 0 && start < array.length && array[start] == array[start-1] && list.size() == 0)
            return;

        if(list.size() == K) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        for(int i = start; i < array.length; i++) {
            list.add(array[i]);
            find(array, i+1, K, list, result);
            list.remove(list.size()-1);
        }
    }
    
    public static Set<List<Integer>> find_iter(int[] array, int K) {
        Arrays.sort(array);

        List<Integer> list = new ArrayList<Integer>();
        List<Integer> stack = new ArrayList<Integer>();
        Set<List<Integer>> result = new HashSet<List<Integer>>();

        int index = 0;
        do {
            while(index < array.length && list.size() < K) {
                // doesn't work. see comment above
                if(list.size() == 0 && index > 0 && array[index] == array[index-1]) {
                    index++;
                    continue;
                }
                
                list.add(array[index]);
                stack.add(index++);
            }
            
            if(list.size() == K)
                result.add(new ArrayList<Integer>(list));
            
            list.remove(list.size()-1);
            index = stack.remove(stack.size()-1) + 1;
        } while(index < array.length || list.size() > 0);

        return result;
    }

    public static void find_map(int[] array) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i]))
                map.put(array[i], map.get(array[i]) + 1);
            else
                map.put(array[i], 1);
        }

        Iterator<Integer> it1, it2, it3;
        for (it1 = map.keySet().iterator(); it1.hasNext();) {
            int key1 = it1.next();

            map.put(key1, map.get(key1) - 1);
            it2 = it1;
            int key2 = key1;
            if (map.get(key1) == 0)
                key2 = it2.next();

            for (; it2.hasNext();) {
                // if(it2->second >0)
                it3 = it2;
                int key3 = key2;
                if (map.get(key2) == 0)
                    key3 = it3.next();

                System.out.println(key1 + " " + key2 + " " + key3);
            }
        }
    }
}