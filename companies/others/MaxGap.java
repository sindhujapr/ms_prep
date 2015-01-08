package test;

import java.util.Arrays;

/**
 * Created by qingcunz on 12/14/14.
 */
public class MaxGap {
    public static void main(String[] args) {
        System.out.println(maximumGap(new int[]{3, 6, 9, 1}));
        System.out.println(maximumGap1(new int[]{3, 6, 9, 1}));
    }

    public static int maximumGap(int[] num) {
        if (num == null || num.length < 2)
            return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i:num) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        int gap = (int)Math.ceil((double)(max - min)/(num.length - 1));
        int[] bucketsMIN = new int[num.length - 1];
        int[] bucketsMAX = new int[num.length - 1];
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
        for (int i:num) {
            if (i == min || i == max)
                continue;
            int idx = (i - min) / gap;
            bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
            bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
        }
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i = 0; i < num.length - 1; i++) {
            if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
                continue;
            maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
            previous = bucketsMAX[i];
        }
        maxGap = Math.max(maxGap, max - previous);
        return maxGap;
    }

    public static  int maximumGap1(int[] num) {
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
