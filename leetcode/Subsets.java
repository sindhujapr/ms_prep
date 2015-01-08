package lc;

import java.util.ArrayList;
import java.util.Arrays;

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

    /*
     * As a new approach, we can use bit operation:
     * If there are n numbers, we map each to one bit.
     * The initial value is 0, indicating empty set.
     * Every time we increase the value by 1 and get the current set by that, if the 
     * corresponding bit is on, we take that number, otherwise ignore it.
     * Increase the value until 2^n
     */
    public List<List<Integer>> subsets2(int[] S) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(S == null || S.length == 0)
            return res;
            
        res.add(new ArrayList<Integer>());
        Arrays.sort(S);
        for(int i = 0; i < S.length; i++) {
            int size = res.size();
            for(int j = 0; j < size; j++) {
                List<Integer> list = res.get(j);
                List<Integer> copy = new ArrayList<Integer>(list);
                copy.add(S[i]);
                res.add(copy);
            }
            // we don't need to create another list and add the current element!!
        }
        
        return res;
    }

    public List<List<Integer>> subsets3(int[] S) {
        if(S == null || S.length == 0)
            return null;
        
        Arrays.sort(S);
        
        return subsets(S, 0);
    }
    
    private List<List<Integer>> subsets(int[] A, int index) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(index == A.length) {
            res.add(new ArrayList<Integer>());
            return res;
        }
        
        List<List<Integer>> temp = subsets(A, index+1);
        
        res.addAll(temp);
        for(List<Integer> list : temp) {
            List<Integer> copy = new ArrayList<Integer>(list);
            copy.add(0, A[index]);
            res.add(copy);
        }
        return res;
    }
}
