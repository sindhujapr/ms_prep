package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qingcunz on 9/13/14.
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] S) {
        assert S != null && S.length > 0;

        Arrays.sort(S);
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        /*
         * The subsets may contain 0 up to S.length elements.
         */
        for(int i = 0; i <= S.length; i++)
            combine(S, 0, i, result, new ArrayList<Integer>());

        return result;
    }

    private void combine(int[] S, int start, int k, List<List<Integer>> result, List<Integer> one) {
        if(one.size() == k) {
            result.add(new ArrayList<Integer>(one));
            return;
        }

        for(int i = start; i < S.length; i++) {
            if(one.size() < k) {
                one.add(S[i]);
                combine(S, i+1, k, result, one);
                one.remove(one.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        int[] S = {1, 2, 2};
        List<List<Integer>> res = new Subsets().subsets(S);
        for(List<Integer> list : res)
            System.out.println(list);
    }
}
