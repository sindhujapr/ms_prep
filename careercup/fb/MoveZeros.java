package careercup.fb;

import java.util.Arrays;

/*
 * http://www.careercup.com/question?id=12986664
 */
public class MoveZeros {
    public static void main(String[] args) {
        int[] array = {1, 2, 0, 4, 0, 0, 8};
        moveZeros(array);
        System.out.println(Arrays.toString(array));
    }
    
    // same problem as dutch flag
    public void move(int[] A) {
        for(int i = 0, j = A.length-1; i < j; ) {
            if(A[i] == 0) {
                swap(A, i, j--);
            } else {
                i++;
            }
        }
    }

    public void swap(int[] A, int i, int j) {
        if(i == j)
            return;
        A[i] ^= A[j];
        A[j] ^= A[i];
        A[i] ^= A[j];
    }
                                                        }
    public static void moveZeros(int[] array) {
        for(int i = 0, j = 0; i < array.length; i++) {
            if(array[i] == 0) {
                continue;
            } else {
                if(i != j) {
                    array[j] = array[i];
                    array[i] = 0;
                }
                j++;
            }
        }
    }
}
