package careercup.fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * http://www.careercup.com/question?id=5172577290944512
 */
public class TripletZero {
    public static void main(String[] args) {
        int[] array = {-1, -3, 5, 4, 2, 1, -2};
        tripletZero(array, 0);
    }
    
    /*
     * another implementation:
     * 1) for each element in the array, find the other two complementary elements
     * 2) To find the two complementary elements, use the same hash techniques:
     * a. scan the array to build the hash map: element value -> index
     * b. scan the array again to find the complementary element in the hash map 
     */
    public static void tripletZero(int[] array, int K) {
        int n = array.length;
        assert array != null;
        
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>(); 
        for(int i = 0; i < n; i++) {
            if(map.containsKey(array[i])) {
                map.get(array[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(array[i], list);
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = i+1; i < n; i++) {
                int complement = K - (array[i]+array[j]);
                if(map.containsKey(complement)) {
                    List<Integer> list = map.get(complement);
                    for(Integer index : list)
                        // to avoid duplicate
                        if(index > j)
                            System.out.println(array[i] + ", " + array[j] + ", " + array[index]);
                }
            }
        }
    }
}