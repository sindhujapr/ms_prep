package lc;

public class FirstMissingPositive {
    public static void main(String[] args) throws Exception {
        FirstMissingPositive instance = new FirstMissingPositive();
        System.out.println(instance.firstMissingPositive1(new int[] { 0 }));
    }

    public int firstMissingPositive(int[] A) {
        int index = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0 && A[i] <= A.length)
                A[index++] = A[i];
        }

        for (int i = index--; i < A.length; i++)
            A[i] = -1;

        while (index >= 0) {
            if (A[index] == index + 1) {
                index--;
            } else if (A[index] > index + 1) {
                A[A[index] - 1] = A[index];
                A[index] = -1;
                index--;
            } else {
                /*
                 * this is the index whose value will be updated.
                 */
                int i = A[index] - 1;
                swap(A, index, i);

                if (A[index] == A[i]) {
                    A[index] = -1;
                    index--;
                }
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (A[i] == -1)
                return i + 1;
        }

        return A.length + 1;
    }

    /*
     * another similar implementation but easier to understand, adapted from
     * http://gongxuns.blogspot.com/2012/12/leetcodefirst-missing-positive.html
     */
    public int firstMissingPositive1(int[] A) {
        int index = 0;
        for(int i = 0; i < A.length; i++)
            if(A[i] > 0)
                A[index++] = A[i];

        for (int i = 0; i < index; i++) {
            int temp = Math.abs(A[i]);
            if (temp <= index && A[temp - 1] > 0)
                A[temp - 1] = -A[temp - 1];
        }
        
        for (int i = 0; i < index; i++) {
            if (A[i] > 0)
                return i + 1;
        }
        return index+1;
    }

    private void swap(int[] A, int m, int n) {
        assert A != null && m >= 0 && m < A.length && n >= 0 && n < A.length && m != n;

        A[m] ^= A[n];
        A[n] ^= A[m];
        A[m] ^= A[n];
    }
}
