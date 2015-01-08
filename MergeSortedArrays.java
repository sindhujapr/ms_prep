package lc;

public class MergeSortedArrays {
    public void merge3(int A[], int m, int B[], int n) {
        for(int i = m+n-1, j = m-1; j >= 0; i--, j--)
            A[i] = A[j];
        
        for(int i = 0, j = n, k = 0; j < m+n || k < n; i++) {
            if(j == m+n)
                A[i] = B[k++];
            else if(k == n)
                A[i] = A[j++];
            else {
                if(A[j] < B[k])
                    A[i] = A[j++];
                else
                    A[i] = B[k++];
            }
        }
    }

    public void merge2(int A[], int m, int B[], int n) {
        int i = 0;
        int j = 0;
        
        /*
         * for each element in B, find a proper position in A and place it there.
         */
        while(j < n) {
            for(; i < m; i++) {
                if(A[i] >  B[j])
                    break;
            }
            
            for(int ii = m-1; ii >= i; ii--)
                A[ii+1] = A[ii];
            A[i] = B[j];
            
            /*
             * both i and m need to be increased!!!
             * 1) there is an element inserted before original A[i]
             * 2) an element is added to A[]
             */
            i++;
            m++;
            j++;
        }        
    }
    
    public void merge(int A[], int m, int B[], int n) {
        for(int i = 0; i < n; i++) {
            int j = 0;
            while(j < m && A[j] < B[i])
                j++;
            
            int k = m-1;
            while(k >= j) {
                A[k+1] = A[k];
                k--;
            }
            A[j] = B[i];
            m++;
        }
    }
}
