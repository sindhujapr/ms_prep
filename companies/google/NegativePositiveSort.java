/*
 * Give you an array which has n integers, t has both positive and negative integers.
 * Now you need sort this array in a special way. After that, the negative integers
 * should in the front, and the positive integers should in the back. Also the relative
 * position should not be changed.
 * eg. -1 1 3 -2 2 ans: -1 -2 1 3 2.
 * o(n)time complexity and o(1) space complexity is required.
 */

public class NegativePositiveSort {
    /*
     * not sure if it's O(n) and haven't been fully tested
     * 
     * some other references:
     * http://www.geeksforgeeks.org/forums/topic/given-an-array-of-integers-move-negative-towards-front-maintaining-their-order/ (memory move is O(1)?)
     * http://apps.topcoder.com/forums/?module=Thread&threadID=808463&start=0&mc=14#1833320
     * 
     * 
     */
    public static void sort(int[] arr) {
        int i = 0, j = 0;

        while(j < arr.length) {
            while(j < arr.length && arr[j] > 0)
                j++;

            while(i < j && arr[i] < 0)
                i++;

            if(i < j && j < arr.length) {
                int temp = arr[j];

                for(int k = j; k > i; k--)
                    arr[k] = arr[k-1];

                arr[i] = temp;
            }

            j++;
            i++;
        }

        System.out.println(Arrays.toString(arr));
    }
}
