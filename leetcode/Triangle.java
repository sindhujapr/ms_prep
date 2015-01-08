package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a triangle, find the minimum path sum from top to bottom. 
 * Each step you may move to adjacent numbers on the row below.
 For example, given the following triangle

 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 Note:
 Bonus point if you are able to do this using only O(n) extra space, 
 where n is the total number of rows in the triangle.
 */
public class Triangle {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        List<Integer> sum = null;
        for (ArrayList<Integer> array : triangle) {
            // calculate the shortest path for each layer
            List<Integer> tmp = new ArrayList<Integer>();

            for (int i = 0; i < array.size(); i++) {
                int weight = array.get(i);

                /*
                 * for the left and right most elements, we have no choice
                 */
                if (i == 0) {
                    if (sum == null)
                        tmp.add(weight);
                    else
                        tmp.add(sum.get(i) + weight);
                } else if (i == array.size() - 1) {
                    tmp.add(sum.get(i - 1) + weight);
                } else {
                    int sum1 = sum.get(i - 1);
                    int sum2 = sum.get(i);

                    if (sum1 < sum2)
                        tmp.add(sum1 + weight);
                    else
                        tmp.add(sum2 + weight);
                }
            }
            sum = tmp;
        }

        int min = Integer.MAX_VALUE;
        for (Integer value : sum)
            if (min > value)
                min = value;

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