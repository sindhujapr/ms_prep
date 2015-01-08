package lc2;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(0);
        
        for(ArrayList<Integer> line : triangle) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int i = 0; i < line.size(); i++) {
                int value = line.get(i);
                int left = i > 0 ? result.get(i-1) : Integer.MAX_VALUE;
                int right = i < result.size() ? result.get(i) : Integer.MAX_VALUE;
                
                int min = Math.min(left, right);
                temp.add(value+min);
            }
            
            result = temp;
        }
        
        int min = Integer.MAX_VALUE;
        for(Integer value : result)
            min = Math.min(value, min);
        return min;
    }

    public ArrayList<ArrayList<Integer>> init() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> l1 = new ArrayList<Integer>();
        l1.add(1);
        list.add(l1);

        ArrayList<Integer> l2 = new ArrayList<Integer>();
        l2.add(-5);
        l2.add(-2);
        list.add(l2);

        ArrayList<Integer> l3 = new ArrayList<Integer>();
        l3.add(3);
        l3.add(6);
        l3.add(1);
        list.add(l3);

        ArrayList<Integer> l4 = new ArrayList<Integer>();
        l4.add(-1);
        l4.add(2);
        l4.add(4);
        l4.add(-3);
        list.add(l4);

        return list;
    }

    public static void main(String[] args) {
        Triangle instance = new Triangle();
        System.out.println(instance.minimumTotal(instance.init()));
    }
}