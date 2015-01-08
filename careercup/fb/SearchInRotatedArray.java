package careercup.fb;

/*
 * http://www.careercup.com/question?id=4852029923000320
 */
public class SearchInRotatedArray {
    public static void main(String[] args) {
        // duplicate is fine
//      int[] array = { 4, 5, 6, 7, 9, 9, 1, 2, 3};
        int[] array = { 1, 3, 1, 1, 1};
        for(int i = 0; i < array.length; i++)
            System.out.println(search(array, array[i]));
    }
    
    public static int search1(int[] A, int target) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        return search(A, 0, A.length-1, target);
    }
    
    private static int search(int[] A, int start, int end, int target) {
        if(start > end)
            return -1;
        
        int mid = (start+end)/2;
        if(A[mid] == target)
            return mid;

        if(A[mid] >= A[start]) {
            if(target >= A[start] && target <= A[mid])
                return search(A, start, mid-1, target);
            else
                return search(A, mid+1, end, target);
        } else {
            if(target <= A[end] && target >= A[mid])
                return search(A, mid+1, end, target);
            else
                return search(A, start, mid-1, target);
        }
    }

    public static int search(int[] array, int key) {
        int low = 0, high = array.length-1;
        while(low <= high) {
            int mid = (low+high) >>> 1;
            if(array[mid] == key)
                return mid;
            
            // left half is sorted
            if(array[mid] >= array[low]) {
                // only when the key falls into the sorted part
                if(key >= array[low] && key <= array[mid])
                    high = mid-1;
                else
                    low = mid+1;
            } else {
                if(array[mid] <= key && key <= array[high])
                    low = mid+1;
                else
                    high = mid-1;
            }
        }
        
        return -1;
    }
}