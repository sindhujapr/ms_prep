package geeksforgeeks.dp;

import java.util.Arrays;
import java.util.Comparator;

/*
 * allow duplicate items to be selected. 0-1 knapsack doesn't allow.
 * 
 * My current thought:
 * Similar like Dijkstra's shortest path algorithm, the greedy version below doesn't 
 * allow negative values.
 */
public class IntegerKnapsack {
    public static void main(String[] args) {
        int[] sizes = {2, 2, 2, 3, 2};
        int[] values = {2, 6, 4, 9, 8};
        
        System.out.println(knapsack_dp(sizes, values, 7));
        System.out.println(knapsack_greedy(sizes, values, 7));
    }
    
    public static int knapsack_dp(int[] sizes, int[] values, int C) {
        assert sizes.length == values.length;
        
        int result[] = new int[C+1];
        for(int i = 1; i <= C; i++) {
            result[i] = result[i-1];
            for(int j = 0; j < values.length; j++) {
                if(sizes[j] <= i)
                    result[i] = Math.max(result[i], result[i-sizes[j]] + values[j]);
            }
        }
        
        return result[C];
    }
    
    /*
     * The problem with greedy algorithm is that, it doesn't always yield optimal solution.
     * The below code doesn't work for input because the output is 24, while the optimal result is 25,
     * which is obtained from dynamic programming.
     *  int[] sizes = {2, 2, 2, 3, 2};
        int[] values = {2, 6, 4, 9, 8};
     * Maybe this is caused by a bug. See below comment.
     */
    public static int knapsack_greedy(int[] sizes, int[] values, int C) {
        int n = sizes.length;
        
        class Item {
            int size;
            int value;
            int rate;
        }

        class comparator implements Comparator<Item> {
            @Override
            public int compare(Item i1, Item i2) {
                int r1 = i1.rate;
                int r2 = i2.rate;
                // to implement compare easier, i made the rate type as int
                return r1 > r2 ? 1 : (r1 == r2 ? 0 : -1);
            }
        }
        
        Item[] items = new Item[n];
        for(int i = 0; i < n; i++) {
            items[i] = new Item();
            items[i].size = sizes[i];
            items[i].value = values[i];
            items[i].rate = values[i] / sizes[i];
        }
        
        Arrays.sort(items, new comparator());
        
        int maxValue = 0;
        for(int capacity = C; capacity >= 0; ) {
             // find an item with highest rate and reasonable size
            int index = -1;
            for(int i = items.length-1; i >= 0; i--) {
                /*
                 * Here we stopped immediately after finding an item with maximum rate, 
                 * regardless of its total value and size. When the last two rates are
                 * 4 and 3, with their sizes are 2 and 3, respectively, and the remaining
                 * capacity is 3, we should choose the 2nd last item, since it can make use
                 * of all capacity and yield larger total value. 
                 * 
                 * Though the below code works for the use scenario in the comment for this method,
                 * I'm not sure whether it's correct.
                 */
                if(items[i].size <= capacity) {
                    if(index == -1 || (items[i].size == capacity && items[i].value > items[index].value))
                        index = i;
                    // this means we can still choose this item next time
                    if(capacity >= 2 * items[i].size)
                        break;
                }
            }
            
            if(index == -1)
                break;
            
            capacity -= items[index].size;
            maxValue += items[index].value;
        }
        
        return maxValue;
    }
}