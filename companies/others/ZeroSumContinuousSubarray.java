package review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qingcunz on 10/11/14.
 */
public class ZeroSumContinuousSubarray {
    public static void main(String[] args) {
        new ZeroSumContinuousSubarray().continuousSubarray(new int[] { 3, -3, 2, 1});
    }

    public void continuousSubarray(int[] A) {
        int[] sum = new int[A.length+1];
        for(int i = 1; i <= A.length; i++)
            sum[i] = sum[i-1]+A[i-1];

        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < sum.length; i++) {
            if(map.containsKey(sum[i])) {
                for(int index : map.get(sum[i]))
                    System.out.println(index + "\t" + (i-1));
                map.get(sum[i]).add(i);
            } else {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                map.put(sum[i], temp);
            }
        }
    }
}
