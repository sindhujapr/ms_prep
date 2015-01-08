package leetcode;

/*
 * http://blog.unieagle.net/2012/12/05/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Abest-time-to-buy-and-sell-stock-iii%EF%BC%8C%E4%B8%80%E7%BB%B4%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/
 */
public class BestTimeToBuySellStockIII {
    /*
     * find a split point such that the sum of two profits can be maximum. this
     * is similar to palindrome partition II.
     */
    public int maxProfit(int[] prices) {
        if (prices.length < 2)
            return 0;

        int[] maxProfit = new int[prices.length];
        maxProfit[0] = 0;

        int min = prices[0];
        int max1 = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - min > max1)
                max1 = prices[i] - min;

            maxProfit[i] = max1;
            if (prices[i] < min)
                min = prices[i];
        }

        /*
         * there is situation that we can only do one transaction, thus the
         * return value would be the profit of the only transaction.
         */
        int maxSum = maxProfit[maxProfit.length - 1];

        int max = prices[prices.length - 1];
        /*
         * max2 record the max profit for [i, ..., n-1]
         */
        int max2 = 0;
        for (int i = prices.length - 2; i >= 1; i--) {
            if (max - prices[i] > max2)
                max2 = max - prices[i];

            if (maxSum < max2 + maxProfit[i - 1])
                maxSum = max2 + maxProfit[i - 1];

            if (prices[i] > max)
                max = prices[i];
        }

        return maxSum;
    }
    
    // my own implementation
    public int maxProfit2(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        
        int n = prices.length;
        
        int[] lp = new int[n];
        int min = prices[0];
        for(int i = 1; i < n; i++) {
            lp[i] = Math.max(lp[i-1], prices[i]-min);
            min = Math.min(min, prices[i]);
        }
        
        int maxProfitFromRight = 0;
        int maxPriceFromRight = prices[n-1];
        int maxProfit = 0;
        
        for(int i = n-2; i >= 0; i--) {
            maxProfitFromRight = Math.max(maxProfitFromRight, maxPriceFromRight-prices[i]);
            maxProfit = Math.max(maxProfit, lp[i] + maxProfitFromRight);
            maxPriceFromRight = Math.max(maxPriceFromRight, prices[i]);
        }
        
        return maxProfit;
    }
}