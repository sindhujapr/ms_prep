package lc;

import java.util.Arrays;

public class ThreeSumClosest {
	public static void main(String[] args) {
		int[] num = {0, 1, 2};
		System.out.println(new ThreeSumClosest().threeSumClosest(num, 1));
	}
	
    public int threeSumClosest(int[] num, int target) {
        int gap = Integer.MAX_VALUE;
        int ret = 0;
        
        Arrays.sort(num);
        
        for(int i = 0; i < num.length; i++) {
            int low = i+1;
            int high = num.length-1;
            
            while(low < high) {
                int sum = num[i] + num[low] + num[high];
                
                if(sum-target < 0)
                    low++;
                else if(sum-target >0)
                    high--;
                else
                	return sum;

                int diff = Math.abs(sum-target);
                if(diff < gap) {
                	gap = diff;
                	ret = sum;
                }
            }
        }
        
        return ret;
    }
}