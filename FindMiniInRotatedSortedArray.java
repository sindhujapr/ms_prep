public class FindMiniInRotatedSortedArray {
    public int findMin(int[] num) {
        int start=0, end=num.length-1;
 
        while (start <= end) {
            if (num[start] <= num[end])
                return num[start];
 
            int mid = (start+end) >> 1;
 
            /*
             * if num[mid] == num[start], then mid == start and actually we can return end directly
             * if num[mid] > num[start], then the array value value is increasing for rang [start, mid]
             * and we need to find the min value in [mid+1, end].
             * if num[mid] < num[start], then num[mid] may also be the min value, thus we search in
             * range [start, mid].
             */
            if (num[mid] >= num[start]) {
                start = mid+1;
            } else {
                end = mid;
            }
        }
 
        return num[start];
    }
    
    // my own naive implementation
    public int findMin2(int[] num) {
        int start = 0, end = num.length-1;
        while(start <= end) {
            int mid = (start+end) >> 1;
            if((mid == start && mid < end && num[mid] < num[mid+1]) ||
               (mid == start && mid == end) ||
               (mid > start && mid == end && num[mid] < num[mid-1]) ||
               (mid > start && mid < end && num[mid] < num[mid-1] && num[mid] < num[mid+1]))
                return num[mid];
            
            if((num[mid] < num[start] && num[mid] < num[end]) || (num[mid] > num[start] && num[mid] < num[end]))
                end = mid-1;
            else
                start = mid+1;
        }
        return -1;
    }
}
