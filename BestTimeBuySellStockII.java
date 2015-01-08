package lc2;

public class BestTimeBuySellStockII {
    // my latest code
    public int maxProfit1(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        
        int profit = 0;
        for(int i = 0, j = 0; i < prices.length; i = j+1, j = i) {
            while(j+1 < prices.length && prices[j+1] > prices[j])
                j++;
            
            profit += prices[j]-prices[i];
        }
        
        return profit;
    }
    
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
            
        int profit = 0;
        int prev = prices[0];
        int min = prices[0];
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < prev) {
                profit += prev-min;
                min = prev = prices[i];
                continue;
            }
            
            prev = prices[i];
        }
        
        profit += prev-min;
        return profit;
    }
}