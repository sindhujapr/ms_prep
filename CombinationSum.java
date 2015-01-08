package lc;

import java.util.ArrayList;
import java.util.Arrays;

public class CombinationSum {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> res = new CombinationSum().combinationSum(new int[]{1, 2, 2, 3}, 5);
        for(ArrayList<Integer> tmp : res)
            System.out.println(tmp);
    }

    /*
     * my own code, which is more concise
     */
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        assert candidates != null;

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(candidates);
            
        find(candidates, 0, target, result, new ArrayList<Integer>());
        return result;
    }
    
    /*
     * actually parameter "end" is not necessary because we can use candidates.length instead.
     */
    private void find(int[] candidates, int start, int target, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> one) {
        if(target == 0) {
            result.add(new ArrayList<Integer>(one));
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            if(candidates[i] > target)
                break;
            
            list.add(candidates[i]);
            /*
             * different than normal DFS, we don't use i+1 here, which means we still 
             * want to evaluate candidates[i] until target is negative.
             */
            find(candidates, i, target-candidates[i], res, list);
            list.remove(list.size()-1);
        }
    }

    /*
     * http://gongxuns.blogspot.com/2012/12/leetcodecombination-sum.html
     */
    public ArrayList<ArrayList<Integer>> combinationSum1(int[] candidates,
            int target) {
        Arrays.sort(candidates);
        ArrayList<ArrayList<Integer>> prev = new ArrayList<ArrayList<Integer>>();
        prev.add(new ArrayList<Integer>());
        
        return combinationSum1(candidates, target, 0, prev);
    }

    public ArrayList<ArrayList<Integer>> combinationSum1(int[] candidates,
            int target, int i, ArrayList<ArrayList<Integer>> prev) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (target == 0) {
            for (ArrayList<Integer> temp : prev) {
                ArrayList<Integer> temp1 = new ArrayList<Integer>(temp);
                res.add(temp1);
            }
            return res;
        }
        for (int j = i; j < candidates.length; j++) {
            if (candidates[j] > target)
                break;

            for (ArrayList<Integer> temp : prev)
                temp.add(candidates[j]);
            
            ArrayList<ArrayList<Integer>> next = combinationSum1(candidates,
                    target - candidates[j], j, prev);

            if (next.size() > 0)
                res.addAll(next);
            for (ArrayList<Integer> temp : prev)
                temp.remove(temp.size() - 1);
        }
        return res;
    }
}
