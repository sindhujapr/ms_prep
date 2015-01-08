
public class MinAdjustment {
    /*
     * http://www.mitbbs.com/article_t/JobHunting/32854141.html
     *
     * My solution should be correct. It prints the followings when you run the code
     * min cost = 2
     * B = 1   2    2    3    
     *
     * state: cost[i][v] - the total cost of changing A[i] to v, where v belongs to [0, max]
     * init: cost[0][v] = |A[0] - v|;
     * function: cost[i][v] = min(cost[i-1][v - target ... v + target]) + |A[i] - v|
     * where v, v - target and v + target all belong to [0, max]
     * result: min(cost[A.length - 1][v])
     */
    public int[] adjust(int[] A, int target) {
        int[] B = new int[A.length];

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i ++)
            max = Math.max(max, A[i]);
        int range = max + 1;

        int[][] cost = new int[A.length][range];
        for(int i = 0; i < range; i ++)
            cost[0][i] = Math.abs(A[0] - i);

        for(int i = 1; i < A.length; i++) {
            int currentMinCost = Integer.MAX_VALUE;
            for(int v = 0; v < range; v++) {
                // calculate the min cost for changing A[i] to v
                cost[i][v] = Integer.MAX_VALUE;
                for(int diff = -1 * target; diff <= target; diff++) {
                    int v1 = v + diff;
                    if(v1 < 0)
                        continue;
                    if(v1 > max)
                        break;

                    int tempCost = cost[i-1][v1] + Math.abs(A[i] - v);
                    if(tempCost < cost[i][v])
                        cost[i][v] = tempCost;
                    if(tempCost < currentMinCost) {
                        currentMinCost = tempCost;
                        B[i-1] = v1;
                    }
                }
            }
        }

        // resolve the value of B[A.length - 1], i.e. the last element of B
        int lastMinCost = cost[A.length - 1] [0];
        B[A.length - 1] = 0;
        for(int v = 1; v < range; v ++) {
            if(cost[A.length - 1][v] < lastMinCost) {
                lastMinCost = cost[A.length - 1][v];
                B[A.length - 1] = v;
            }
        }

        return B;
    }

    public static void main(String[] args) {
        MinAdjustment sol = new MinAdjustment();
        int[] A = {1,4,2,3};
        int[] B = sol.adjust(A, 1);
        System.out.print("B = ");
        for(int b : B)
            System.out.print(b + "\t");
    }
}
