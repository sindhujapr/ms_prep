package careercup;

import java.util.ArrayList;
import java.util.List;

/*
 * See also FindTwoElementsForSpecifiedSum
 * 
 * this problem doesn't fit into dynamic programming because
 * we're not trying to find an optimal solution. Instead, we
 * are only trying to find all possible combinations.
 */
public class KnapSack {
    public void find1(int[] weight, int T) {
        List<Integer> indices = new ArrayList<Integer>();
        int index = 0;

        do {
            while (T > 0 && index < weight.length) {
                if (T - weight[index] >= 0) {
                    indices.add(index);
                    T -= weight[index];
                }
                index++;
            }
            if (T == 0) {
                for (Integer i : indices)
                    System.out.print(weight[i] + " ");
                System.out.println();
            }

            /*
             * index is reset to the index of the element just been removed.
             * then we increase it so that we can consider the next element!!!
             */
            index = indices.remove(indices.size() - 1) + 1;
            T += weight[index - 1];

            /*
             * Among the below two conditions, if the first stands while the
             * second doesn't, it means we need to pop element from the stack.
             * Conversely, we need to push more elements to the stack.
             * indices.size() > 0 means at least we can remove one element.
             * index < weight.length means we have the next element to add
             */
        } while (indices.size() > 0 || index < weight.length);
    }

    /*
     * recursion edition
     */
    private List<Integer> result = new ArrayList<Integer>();

    public void find2(int[] weight, int T) {
        find2_i(weight, 0, T);
    }

    private void find2_i(int[] weight, int index, int T) {
        if (T == 0)
            System.out.println(result);

        while (index < weight.length) {
            if (weight[index] <= T) {
                result.add(weight[index]);
                find2_i(weight, index + 1, T - weight[index]);
                result.remove(result.size() - 1);
            }
            index++;
        }
    }

    public static void main(String[] args) {
        int[] weight = { 12, 1, 8, 4, 3, 5, 2 };

        KnapSack instance = new KnapSack();
        instance.find1(weight, 12);
        instance.find2(weight, 12);
    }
}