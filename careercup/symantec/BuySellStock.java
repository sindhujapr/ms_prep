package careercup.symantec;

/*
 * http://www.careercup.com/question?id=17029670
 */
public class BuySellStock {
    public static void main(String[] args) {
        int[] array1 = {30, 12, 15, 10, 40, 30, 60, 100};
        System.out.println(maxProfit(array1));
        
        int[] array2 = {30, 12, 15, 10, 40, 30, 60, 100, 2, 110};
        System.out.println(maxProfit(array2));
        
        int[] array3 = {30, 12, 15, 5, 40, 30, 60, 130, 2, 110};
        System.out.println(maxProfit(array3));
    }
    
    public static int maxProfit(int[] array) {
        assert array != null;
        
        int n = array.length;
        int[] right = new int[n];
        int max = array[n-1];
        for(int i = n-1; i >= 0; i--)
            right[i] = max = Math.max(array[i], max);

        int maxProfit = 0;
        for(int i = 0; i < n; i++)
            maxProfit = Math.max(maxProfit, right[i]-array[i]);
        
        return maxProfit;
    }
}