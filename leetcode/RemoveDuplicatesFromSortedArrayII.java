package lc;

public class RemoveDuplicatesFromSortedArrayII {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArrayII instance = new RemoveDuplicatesFromSortedArrayII();
        int A[] = {1, 1, 1, 1, 3, 3};
        int size = instance.removeDuplicates(A);
        for(int i = 0; i < size; i++)
            System.out.print(A[i] + ", ");
    }
    
    // my latest code
    public int removeDuplicates(int[] A) {
        if(A == null || A.length == 0)
            return 0;
        
        int len = 0;
        for(int i = 0; i < A.length; ) {
            if(i == A.length-1 || A[i+1] != A[i]) {
                A[len++] = A[i++];
            } else {
                A[len++] = A[i++];
                A[len++] = A[i++];
                while(i < A.length && A[i] == A[i-1])
                    i++;
            }
        }
        
        return len;
    }
    
    public int removeDuplicates1(int[] A) {
        int size = A.length;
        for(int i = 0; i < size; ) {
            int index = i;
            int val = A[i];
            while(index < size && A[index] == val)
                index++;
            
            int pos = index;
            if(index -i > 2) {
                for(int j = i+2; index < size; j++) {
                    A[j] = A[index++];
                }
                size -= pos-i-2;
                i += 2;
            } else {
                i++;
            }
        }
        
        return size;
    }
}