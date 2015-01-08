package careercup.fb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * http://www.careercup.com/question?id=5172027535130624
 * 
 * this problem can be extended to this:
 * is there a continuous subarray that sums up to K?
 */
public class ZeroSumContinuousSubarray {
    public static void main(String[] args) {
        int[] array =  {-1, -3, 4, -9, 5, 4, -4};
        zeroSum1(array);
        zeroSum2(array);
    }
    
    /*
     * O(n)
     */
    public static void zeroSum1(int[] array) {
        System.out.println("zero sum1:");
        
        int n = array.length;
        int[] data = new int[n+1];
        /*
         * it's important to either explicitly or implicitly set data[0]
         * as 0 as sentinel
         */
        for(int i = 1; i < n+1; i++)
            data[i] = data[i-1] + array[i-1];
        
        System.out.println(Arrays.toString(data));
        
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < n+1; i++) {
            if(map.get(data[i]) != null) {
                for(Integer prev : map.get(data[i]))
                    System.out.println(prev + ", " + (i-1));
                map.get(data[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(data[i], list);
            }
        }
    }
    
    /*
     * naive implementation: O(n^2)
     */
    public static void zeroSum2(int[] array) {
        System.out.println("zero sum2:");
        int n = array.length;
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i; j < n; j++) {
                sum += array[j];
                if(sum == 0)
                    System.out.println(i + ", " + j);
            }
        }
    }
}