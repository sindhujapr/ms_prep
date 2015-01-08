package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingcunz on 10/11/14.
 */
public class SubsetII {
    public static void main(String[] args) {
        List<List<Integer>> res = SubsetII.subset(new int[] {1, 2, 2, 3, 3});
        for (List<Integer> list : res)
            System.out.println(list);
    }

    public static List<List<Integer>> subset(int[] A) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(A == null || A.length == 0)
            return res;

        res.add(new ArrayList<Integer>());

        for(int i = 0, start = 0; i < A.length; i++) {
            if(i > 0 && A[i] != A[i-1])
                start = i;

            int len = i-start;

            for(int j = 0, size = res.size(); j < size; j++) {
                List<Integer> list = res.get(j);
                if(len > 0 && (list.size() < len || list.get(list.size()-len) != A[i]))
                    continue;

                List<Integer> temp = new ArrayList<Integer>(list);
                temp.add(A[i]);
                res.add(temp);
            }
        }

        return res;
    }
}
