package geeksforgeeks.dp;

public class MaximumValueContiguousSubsequence {
    public static void main(String[] args) {
        int[] values = {2, -1, 2, -4, 1, 2, -1, 2};
        System.out.println(maxValue(values));
        System.out.println(maxValue2(values));
    }
    
    public static int maxValue(int[] values) {
        int max = 0;
        int sum = 0;
        for(int i = 0; i < values.length; i++) {
            sum += values[i];
            if(sum < 0)
                sum = 0;
            max = Math.max(max, sum);
        }
        
        return max;
    }
    
    public static int maxValue2(int[] values) {
        int[] max = new int[values.length+1];
        
        for(int i = 1; i <= values.length; i++) {
            max[i] = Math.max(max[i-1] + values[i-1],  values[i-1]);
        }
        
        return max[values.length];
    }
}