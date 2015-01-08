package lc;

import java.util.ArrayList;

public class Combinations {
    public static void main(String[] args) {
        Combinations instance = new Combinations();
        ArrayList<ArrayList<Integer>> result = instance.combine(2, 2);
        for(ArrayList<Integer> list : result)
            System.out.println(list);
    }
    
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> one = new ArrayList<Integer>();
        
        combine(n, 1, k, result, one);
        return result;
    }
    
    /*
     * actually we don't need the second parameter since it's can be acquired by one.size()+1.
     * But it's quite weird that it failed with time limit if we don't use it.
     */
    private void combine(int n, int start, int k, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> one) {
        if(one.size() == k) {
            result.add(new ArrayList<Integer>(one));
            return;
        }
        
        for(int i = start; one.size() < k && i <= n; i++) {
            one.add(i);
            combine(n, i+1, k, result, one);
            one.remove(one.size()-1);
        }
    }
    
    public ArrayList<ArrayList<Integer>> combine1(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(k <= 0 || k > n)
            return res;
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        int index = 1;
        
        do {
            while(index <= n && list.size() < k)
                list.add(index++);
            
            if(list.size() == k)
                res.add(new ArrayList<Integer>(list));
                
            index = list.remove(list.size()-1) + 1;
        } while(list.size() > 0 || index <= n);
        
        return res;
    }
}