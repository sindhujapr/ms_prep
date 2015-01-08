package lc;

public class RemoveElement {
    public int removeElement(int[] A, int elem) {
        int i = 0, j  = 0;
        while(j < A.length) {
            while(j < A.length && A[j] == elem)
                j++;

            if(j != A.length)
                A[i++] = A[j++];
        }
        
        return i;
    }
    
    public int removeElement2(int[] A, int elem) {
        if(A.length == 0)
            return 0;

        int pos = -1;
        for(int i = 0; i < A.length; ) {
            while(i < A.length && A[i] != elem)
                i++;
            
            if(pos == -1)
                pos = i;
            
            i++;
            while(i < A.length && A[i] != elem) {
                A[pos++] = A[i++];
            }        
        }
        
        return pos;
    }
}