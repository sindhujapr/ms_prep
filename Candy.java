package lc;

public class Candy {
    /*
     * may be easier to understand:
     * http://yucoding.blogspot.com/2014/02/leetcode-question-candy.html
     */
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0)
            return 0;
        
        int n = ratings.length;
        int[] left = new int[n], right = new int[n];
        for(int i = 0; i < n; i++)
            if(i == 0 || ratings[i] <= ratings[i-1])
                left[i] = 1;
            else
                left[i]= left[i-1]+1;
        
        for(int i = n-1; i >= 0; i--)
            if(i == n-1 || ratings[i] <= ratings[i+1])
                right[i] = 1;
            else
                right[i] = right[i+1]+1;
        
        int res = 0;
        for(int i = 0; i < n; i++)
            res += Math.max(left[i], right[i]);
        return res;
    }

    public int candy(int[] ratings) {
        int n = ratings.length;
        if(n == 0)
            return 0;

        int[] candies = new int[n];
        for(int i = 1; i < n; i++)
            if(ratings[i] > ratings[i-1])
                candies[i] = candies[i-1] + 1;
        
        /*
         * 1. each child has at least one candy.
         * 2. the least number of candies for the last child is sure to be candies[n-1]. 
         */
        int res = n + candies[n-1];
        for(int i = n-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1] && candies[i] < candies[i+1] + 1)
                candies[i] = candies[i+1] + 1;
            res += candies[i];
        }
        
        return res;
    }
    
    public static void main(String[] args) {
		System.out.println(new Candy().candy(new int[] {3, 2, 4, 5}));
		System.out.println(new Candy().candy(new int[] {7, 6, 5, 1}));
	}
}
