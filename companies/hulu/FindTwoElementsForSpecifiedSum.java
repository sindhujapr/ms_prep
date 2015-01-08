package interview.hulu;

import java.util.ArrayList;
import java.util.List;

public class FindTwoElementsForSpecifiedSum {
    private static final int CNT = 2;

    public static void main(String[] args) {
        int[] array = { 3, 13, 1, 4, 2, 5, 8, 9, 7, 6, 11, 12, 10 };
        findTwoElements(array, 13);
    }

    // This is brute-force. We can solve it more efficiently with hashing.
    public static void findTwoElements(int[] array, int sum) {
        List<Integer> indices = new ArrayList<Integer>();
        int index = 0;

        do {
            while (indices.size() < CNT && sum > 0 && index < array.length) {
                if (sum - array[index] >= 0) {
                    sum -= array[index];
                    indices.add(index);
                }
                index++;
            }

            /*
             * The 2nd condition is necessary. Otherwise single element may be
             * displayed.
             */
            if (sum == 0 && indices.size() == CNT) {
                for (Integer i : indices) {
                    System.out.print(array[i] + " ");
                }
                System.out.println();
            }

            index = indices.remove(indices.size() - 1) + 1;
            sum += array[index - 1];
        } while (indices.size() > 0 || index < array.length);
    }
}