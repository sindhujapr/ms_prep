package test;

/**
 * http://www.careercup.com/question?id=5179916190482432
 * input [2,3,1,4]
 output [12,8,24,6]

 Multiply all fields except it's own position.

 Restrictions:
 1. no use of division
 2. complexity in O(n)
 * Created by qingcunz on 11/9/14.
 */
public class ProductWithoutCurrent {
    public static void main(String[] args) {
        int[] res = product(new int[] {2, 3, 1, 4});
        for(int val : res)
            System.out.print(val + "\t");
    }

    public static int[] product(int[] arr) {
        if(arr == null || arr.length == 0)
            return null;

        int[] res = new int[arr.length];
        int[] left = new int[arr.length], right = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
            left[i] = i == 0 ? 1 : left[i-1] * arr[i-1];

        for(int i = arr.length-1; i >= 0; i--)
            right[i] = i == arr.length-1 ? 1 : right[i+1] * arr[i+1];

        for(int i = 0; i < arr.length; i++)
            res[i] = left[i] * right[i];
        return res;
    }
}
