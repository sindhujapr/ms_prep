package lc2;

public class BestTimeToBuySellStock {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        
        int min = prices[0], max = 0;
        for(int price: prices) {
            max = Math.max(price-min, max);
            min = Math.min(min, price);
        }
        
        return max;
    }        
}