package lc;
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] A) {
        int index = 0;
        for(int i = 0; i < A.length; ) {
            A[index++] = A[i++];
            
            while(i < A.length && A[i] == A[index-1])
                i++;
        }
        
        return index;
    }
	
    public int removeDuplicates2(int[] A) {
        int i = 0, j = 0;
        while(j < A.length) {
            if(j > 0 && A[j] == A[j-1]) {
                j++;
                continue;
            }
            
            A[i++] = A[j++];
        }
        
        return i;
    }
}