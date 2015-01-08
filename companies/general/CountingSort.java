/*
 * http://opendatastructures.org/ods-java/11_2_Counting_Sort_Radix_So.html
 * http://www.geeksforgeeks.org/counting-sort/
 */
public class CountingSort{
    // suppose all elements in A has at most 5 digits
    public static void countSort(int[] A) {
        int n = A.length;
        int[] count = new int[10], B = new int[n];

        for(int i = 0; i < 5; i++) {
            //sort based on digit i
            Arrays.fill(count, 0);
            int power = power(i);

            for(int j = 0; j < n; j++) {
                int index = A[j] / power % 10;
                count[index]++;
            }

            for(int j = 1; j < 10; j++)
                count[j] += count[j-1];

            for(int j = n-1; j >= 0; j--) {
                int bit = A[j] / power(i) % 10;
                B[--count[bit]] = A[j];
            }

            for(int j = 0; j < n; j++)
                A[j] = B[j];
        }

        System.out.println(Arrays.toString(A));
    }

    private static int power(int i) {
        int res = 1;
        while(i-- > 0)
            res *= 10;
        return res;
    }
}
