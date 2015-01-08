public class FindMiniInRotatedSortedArrayII {
    public int findMin(int[] num) {
        int start = 0, end = num.length-1;
        while(start <= end) {
            while(start < end && num[start] == num[end])
                start++;
            
            if(num[start] <= num[end])
                return num[start];
            
            int mid = (start+end) >> 1;
            if(num[mid] >= num[start])
                start = mid+1;
            else
                end = mid;
        }
        return -1;
    }
}
