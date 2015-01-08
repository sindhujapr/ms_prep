package careercup.fb;

/*
 * The special case is that, this problem allows duplicate elements
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(new SearchInRotatedSortedArray().search(new int[]{1,1,3,1}, 3));
    }
    
    public boolean search(int[] A, int target) {
        if(A == null || A.length == 0)
            return false;
        
        return search(A, 0, A.length-1, target);
    }
    
    private boolean search(int[] A, int start, int end, int target) {
        if(start > end)
            return false;
        
        int mid = (start+end) >> 1;
        if(A[mid] == target)
            return true;

        if(A[mid] > A[start]) {
            if(target >= A[start] && target < A[mid])
                return search(A, start, mid-1, target);
            else
                return search(A, mid+1, end, target);
        } else if(A[mid] < A[start]) {
            if(target > A[mid] && target <= A[end])
                return search(A, mid+1, end, target);
            else
                return search(A, start, mid-1, target);
        } else {
            /*
             * here are two situations:
             * 1. [2 3 1 2 2] and mid points to the second last 2 and we should
             * search in range [2, 3, 1].
             * 2. [2 2 3 1 2] and mid points to the second 2 and we should
             * search in range [3, 1, 2].
             * The difference is, in the first scenario, there are only 2s after mid
             */
            int i = mid+1;
            while(i <= end && A[i] == A[mid])
                i++;
            if(i > end)
                return search(A, start, mid-1, target);
            else
                return search(A, mid+1, end, target);
        }
    }
}