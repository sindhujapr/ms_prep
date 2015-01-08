package google;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Given array[n]，let s[i] as the number of elements larger than a[i] in subarray a[i+1,...,n-1]
 * Get the maximum s[i]. Requires O(nlgn) time complexity.
 */
public class CountBigger {
    public static void main(String[] args) {
        CountBigger instance = new CountBigger();
        instance.verify(new int[]{3, 2, 1, 5, 7, 4, 6, 8});
        instance.verify(new int[]{5, 4, 6, 5, 2, 3, 3, 4});
        instance.verify(new int[]{8, 7, 1, 3, 2, 5, 6, 4});
        instance.verify(new int[]{5, 4, 2, 3, 7, 6, 1, 8});
        instance.verify(new int[]{3, 7, 2, 1, 8, 4, 6, 10, 5, 9});

        for(int i = 0; i < 100; i++)
            instance.verify();
    }

    public void verify(int[] arr) {
        int count1 = count_verify(arr);
        int count2 = count(arr);
        if(count1 != count2)
            System.out.println(count1 + "\t" + count2);
    }

    private static final Random rand = new Random();

    public void verify() {
        int len = rand.nextInt(300);
        int[] arr = new int[len];
        for(int i = 0; i < len; i++)
            arr[i] = rand.nextInt(10000);

        count(arr);
    }

    public int count_verify(int[] arr) {
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length-1; i++) {
            int count = 0;
            for(int j = i+1; j < arr.length; j++) {
                count += arr[j] > arr[i] ? 1 : 0;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    /*
     * Every time we count the numbers from left and right side respectively and then
     * do a merge. During the merge, if the the left element is smaller than the right
     * element, that means we have (b-j+1) elements in the right part larger than the
     * left element, thus we increase the count for this element with (b-j+1). The count
     * of the right elements don't change during the merge.
     * Time complexity: O(nlgn). Space complexity: O(n).
     *
     * The idea is similar to inversion count:
     * http://www.geeksforgeeks.org/counting-inversions/
     */
    public int count(int[] arr) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int val : arr)
            map.put(val, 0);

        _count(arr, 0, arr.length-1, map);

        int res = Integer.MIN_VALUE;
        for(int len : map.values())
            res = Math.max(res, len);
        return res;
    }

    private void _count(int[] arr, int a, int b, Map<Integer, Integer> map) {
        if(a >= b)
            return;

        int m = (a+b) >> 1;
        _count(arr, a, m, map);
        _count(arr, m+1, b, map);

        _merge(arr, a, m, b, map);
    }

    private void _merge(int[] arr, int a, int m, int b, Map<Integer, Integer> map) {
        int[] temp = new int[b-a+1];

        for(int i = a, j = m+1, index = 0; i <= m || j <= b; index++) {
            if(i <= m && j <= b) {
                if(arr[i] < arr[j]) {
                    int count = map.get(arr[i]);
                    map.put(arr[i], count+(b-j+1));

                    temp[index] = arr[i++];
                } else {
                    temp[index] = arr[j++];
                }
            } else if(i <= m) {
                temp[index] = arr[i++];
            } else {
                temp[index] = arr[j++];
            }
        }

        for(int i = a, index = 0; i <= b; i++, index++)
            arr[i] = temp[index];
    }
}