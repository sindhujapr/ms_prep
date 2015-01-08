package lc;

import java.util.ArrayList;
import java.util.List;

public class KnapSack {
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<Integer>();
        input.add(9);
        input.add(7);
        input.add(2);
        input.add(8);
        input.add(1);
        input.add(6);
        
        List<List<Integer>> result = f(input, 9);
        for(List<Integer> one : result)
            System.out.println(one);
    }
    
    public static List<List<Integer>> f(List<Integer> input, int k) {
        List<Integer> one = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> indices = new ArrayList<Integer>();
        
        int index = 0;
        int sum = k;
        
        do {
            while(index < input.size() && sum > 0) {
                one.add(input.get(index));
                sum -= input.get(index);
                indices.add(index);
                index++;
            }
            
            if(sum == 0)
                result.add(new ArrayList<Integer>(one));
            
            index = indices.remove(indices.size()-1) + 1;
            sum += one.remove(one.size()-1);
        } while(index < input.size() || one.size() > 0);
        
        return result;
    }
}
