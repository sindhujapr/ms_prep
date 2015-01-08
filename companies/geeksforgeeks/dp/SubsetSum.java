package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
 */
public class SubsetSum {
    public static void main(String[] args) {
        int[] array = {3, 34, 4, 12, 5, 2};
        System.out.println(subsetSum(array, 9));
    }
    
    public static boolean subsetSum(int[] array, int sum) {
        boolean[][] temp = new boolean[sum+1][array.length+1];
        for(int i = 0; i <= sum; i++)
            temp[i][0] = false;
        
        for(int j = 0; j <= array.length; j++)
            temp[0][j] = true;

        for(int i = 1; i <= sum; i++) {
            for(int j = 1; j <= array.length; j++) {
                temp[i][j] = temp[i][j-1];
                
                if(i >= array[j-1])
                    temp[i][j] = temp[i][j] || temp[i-array[j-1]][j-1];
            }
        }
        
        return temp[sum][array.length];
    }
}