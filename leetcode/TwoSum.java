package leetcode;

import java.util.Arrays;

public class TwoSum {
    // my latest code. time efficient but not space efficient
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        if(numbers == null || numbers.length < 2)
            return res;
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i+1);
        }
        
        for(int i = 0; i < numbers.length; i++) {
            Integer index = map.get(target-numbers[i]);
            if(index != null && index != i+1) {
                res[0] = Math.min(index, i+1);
                res[1] = Math.max(index, i+1);
                return res;
            }
        }
        
        return res;
    }

    /*
     * recursion edition
     */
    public int[] twoSum1(int[] numbers, int target) {
        int[] result = new int[2];
        twoSum(result, 0, numbers, 0, target);
    
        return result;
    }
    
    private boolean twoSum(int[] result, int size, int[] numbers, int start, int target) {
        if(target == 0 && size == 2) {
            return true;
        }
        
        for(int i = start; i < numbers.length; i++) {
            if(numbers[i] <= target && size < 2) {
                target -= numbers[i];
                result[size++] = i+1;
                boolean ret = twoSum(result, size, numbers, i+1, target);
                if(ret)
                    return true;

                target += numbers[i];
                size--;
            }
        }
        
        return false;
    }
    
    /*
     * non-recursion
     */
    public int[] twoSum2(int[] numbers, int target) {
    int[] result = new int[2];
    int size = 0;
    int index = 0;

    do {
        while (index < numbers.length && target > 0) {
        if (numbers[index] <= target && size < 2) {
            target -= numbers[index];
            result[size++] = index+1;
        }
        index++;
        }

        if (target == 0 && size == 2)
        return result;

        size--;
        target += numbers[result[size] - 1];
        // point to the next element
        index = result[size];
    } while (size > 0 || index < numbers.length);

    return result;
    }

    public static void main(String[] args) {
    int[] numbers = { 2, 1, 9, 4, 4, 56, 90, 3 };
    int[] result1 = new TwoSum().twoSum2(numbers, 8);
    int[] result2 = new TwoSum().twoSum1(numbers, 60);
    System.out.println(Arrays.toString(result1));
    System.out.println(Arrays.toString(result2));
    }
}
