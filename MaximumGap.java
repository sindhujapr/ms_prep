// http://zkfairytale.blogspot.ca/2014/12/maximum-gap.html
public class MaximumGap {  
    public int maximumGap(int[] num) {
        if(num == null || num.length < 2)
            return 0;
        
        int n = num.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int val : num) {
            min = Math.min(val, min);
            max = Math.max(val, max);
        }
        
        int gap = (int) Math.ceil((double) (max-min)/(n-1));
        int[] minBucket = new int[n-1], maxBucket = new int[n-1];
        
        Arrays.fill(minBucket, Integer.MAX_VALUE);
        Arrays.fill(maxBucket, Integer.MIN_VALUE);
        
        for(int val : num) {
            if(val == min || val == max)
                continue;
            
            int index = (val-min)/gap;
            minBucket[index] = Math.min(minBucket[index], val);
            maxBucket[index] = Math.max(maxBucket[index], val);
        }
        
        int prev = min, maxGap = Integer.MIN_VALUE;
        for(int i = 0; i < n-1; i++) {
            if(maxBucket[i] == Integer.MIN_VALUE && minBucket[i] == Integer.MAX_VALUE)
                continue;
            
            maxGap = Math.max(maxGap, minBucket[i]-prev);
            prev = maxBucket[i];
        }
        
        return Math.max(maxGap, max-prev);
    }
}
