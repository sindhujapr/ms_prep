package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 */
public class LargestSumContiguousSubarray {
    public static void main(String[] args) {

    }

    public int largest(int[] array) {
        int max_so_far = array[0];
        int curr_max = array[0];

        for (int i = 1; i < array.length; i++) {
            curr_max = Math.max(array[i], curr_max + array[i]);
            max_so_far = Math.max(max_so_far, curr_max);
        }
        return max_so_far;
    }
}