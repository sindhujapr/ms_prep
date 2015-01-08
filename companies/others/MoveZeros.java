package test;

/**
 * Created by qingcunz on 10/10/14.
 */
public class MoveZeros {
    public static void main(String[] args) {
        int[] A = new int[] {3, 0, -2, 1, 0, 3, 0, 4, 5 , 0};
        new MoveZeros().move(A);
        for(int val : A)
            System.out.print(val + "\t");
    }

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
