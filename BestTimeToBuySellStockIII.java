package lc2;

/*
 * http://blog.unieagle.net/2012/12/05/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Abest-time-to-buy-and-sell-stock-iii%EF%BC%8C%E4%B8%80%E7%BB%B4%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/
 */
public class BestTimeToBuySellStockIII {
    /*
     * find a split point such that the sum of two profits can be maximum. this
     * is similar to palindrome partition II.
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        
        int[] maxProfit = new int[prices.length];
        
        int max = 0;
        int min = prices[0];
        for(int i = 1; i < prices.length; i++) {
            max = maxProfit[i] = Math.max(prices[i]-min, max);
            min = Math.min(min, prices[i]);
        }
        
        int maxSum = maxProfit[prices.length-1];
        
        max = 0;
        int maxNum = prices[prices.length-1];
        for(int i = prices.length-2; i >= 0; i--) {
            max = Math.max(max, maxNum-prices[i]);
            maxNum = Math.max(maxNum, prices[i]);
            
            maxSum = Math.max(maxSum, maxProfit[i]+max);
        }
        
        return maxSum;
    }

    // easier to understand
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        
        int n = prices.length;
        int[] left = new int[n], right = new int[n];
        
        int min = prices[0];
        for(int i = 1; i < n; i++) {
            left[i] = Math.max(prices[i]-min, left[i-1]);
            min = Math.min(prices[i], min);
        }
        
        int max = prices[n-1];
        for(int i = n-2; i >= 0; i--) {
            right[i] = Math.max(max-prices[i], right[i+1]);
            max = Math.max(prices[i], max);
        }
        
        int res = 0;
        for(int i = 0; i < n; i++)
            res = Math.max(left[i]+right[i], res);
        return res;
    }
}
