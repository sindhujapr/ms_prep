package lc2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	/*
	 * recursion edition
	 */
	public int[] twoSum2(int[] numbers, int target) {
		int[] result = new int[2];
		twoSum(result, 0, numbers, 0, target);

		return result;
	}

	private boolean twoSum(int[] result, int size, int[] numbers, int start, int target) {
		if (target == 0 && size == 2) {
			return true;
		}

		for (int i = start; i < numbers.length; i++) {
			if (numbers[i] <= target && size < 2) {
				target -= numbers[i];
				result[size++] = i + 1;
				boolean ret = twoSum(result, size, numbers, i + 1, target);
				if (ret)
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
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        if(numbers.length < 2)
            return result;
            
        int index = 0;
        int size = 0;
        do {
            while(index < numbers.length && size < 2) {
                if(numbers[index] <= target) {
                    target -= numbers[index];
                    result[size++] = index+1;
                }
                index++;
            }
            
            if(size == 2 && target == 0)
                return result;
            
            if(size > 0) {
                index = result[--size];
                target += numbers[index-1];
            }
        } while(size > 0 || index < numbers.length);

        return result;
    }

    public int[] twoSum_best(int[] num, int target) {
        int[] res = new int[2];
        if(num == null || num.length < 2)
            return res;
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < num.length; i++) {
            if(map.containsKey(target-num[i])) {
                res[0] = map.get(target-num[i]) + 1;
                res[1] = i+1;
            } else {
                map.put(num[i], i);
            }
        }
        return res;
    }

	public static void main(String[] args) {
		int[] numbers = { 2, 1, 9, 4, 4, 56, 90, 3 };
		int[] result1 = new TwoSum().twoSum(numbers, 8);
		int[] result2 = new TwoSum().twoSum2(numbers, 60);
		System.out.println(Arrays.toString(result1));
		System.out.println(Arrays.toString(result2));
	}
}
