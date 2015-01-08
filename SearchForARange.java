package lc;

import java.util.Arrays;

public class SearchForARange {
	// my latest code as of sep/14
    public int[] searchRange(int[] A, int target) {
        int[] res = new int[2];
        if(A == null || A.length == 0) {
            res[0] = res[1] = -1;
            return res;
        }
        
        res[0] = left(A, target);
        res[1] = right(A, target);
        return res;
    }
    
    private int left(int[] A, int target) {
        int left = 0, right = A.length-1;
        while(left <= right) {
            int mid = (left+right) >> 1;
            if(A[mid] == target && (mid == left || A[mid-1] != target))
                return mid;
            else if(A[mid] >= target)
                right = mid-1;
            else
                left = mid+1;
        }
        
        return -1;
    }
    
    private int right(int[] A, int target) {
        int left = 0, right = A.length-1;
        while(left <= right) {
            int mid = (left+right) >> 1;
            if(A[mid] == target && (mid == right || A[mid+1] != target))
                return mid;
            else if(A[mid] <= target)
                left = mid+1;
            else
                right = mid-1;
        }
        return -1;
    }

	public static void main(String[] args) {
		SearchForARange instance = new SearchForARange();
		int[] result = instance.searchRange(new int[]{0,0,0,0,1,2,3,3,4,5,6,6,7,8,8,8,9,9,10,10,11,11}, 0);
		System.out.println(Arrays.toString(result));
	}

	/*
	 * http://gongxuns.blogspot.com/2012/12/leetcode-search-for-range.html
	 */
    public int[] searchRange(int[] A, int target) {
        if(A==null || A.length==0)return null;
        int[] res = new int[2];
        res[0]=searchMaxLessThan(A,target,0,A.length-1);
        res[1]=searchMaxLessThan(A,target+1,0,A.length-1);
        if(res[0]==res[1]){
            res[0]=-1;
            res[1]=-1;
        }else{
            res[0]++;
        }
        return res;
    }
    
    public int searchMaxLessThan(int[] A, int target, int start, int end){
        
        if(start==end) return A[start]<target?start:start-1;
        if(start==end-1) return A[end]<target?end:(A[start]<target?start:start-1);
        int mid = (start+end)/2;
        if(A[mid]>=target){
            end=mid-1;
        }else{
            start=mid;
        }
        return searchMaxLessThan(A,target,start,end);
    }

	/*
	 * my own implementation, a little complex
	 */
    public int[] searchRange1(int[] A, int target) {
        int[] result = new int[2];
        
        int low = 0;
        int high = A.length-1;
        int mid = -1;
        while(low <= high) {
            int midIndex = (low+high) >>> 1;
            if(A[midIndex] == target) {
                mid = midIndex;
                break;
            } else if(A[midIndex] < target) {
                low = midIndex+1;
            } else {
                high = midIndex-1;
            }
        }
        
        if(mid == -1) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        
        result[0] = mid;
        while(low <= mid) {
            int midIndex = (low+mid) >>> 1;
            if(A[midIndex] == target) {
                if(midIndex == 0 || (midIndex > 0 && A[midIndex-1] < target)) {
                    result[0] = midIndex;
                    break;
                }
                mid = midIndex;
            }
            if(A[midIndex] < target) {
                low = midIndex+1;
            }
        }
        
        result[1] = mid = result[0];
        while(mid <= high) {
            int midIndex = (mid+high) >>> 1;
            if(A[midIndex] == target) {
                if(midIndex == A.length-1 || (midIndex < A.length-1 && A[midIndex+1] > target)) {
                    result[1] = midIndex;
                    break;
                }
                mid = midIndex+1;
            }
            if(A[midIndex] > target) {
                high = midIndex-1;
            }
        }
        
        return result;
    }
}
