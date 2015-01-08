package lc;

public class SearchInsertPosition {
	/*
	 * http://gongxuns.blogspot.com/2012/12/leetcode-search-insert-position.html
	 */
	public int searchInsert1(int[] A, int target) {
		if (A == null || A.length == 0)
			return 0;
		return searchInsert(A, target, 0, A.length - 1);
	}

	public int searchInsert(int[] A, int target, int start, int end) {
		int mid = (start + end) / 2;
		if (target == A[mid])
			return mid;
		else if (target < A[mid])
			return start < mid ? searchInsert(A, target, start, mid - 1)
					: start;
		else
			return end > mid ? searchInsert(A, target, mid + 1, end)
					: (end + 1);
	}

	/*
	 * http://fisherlei.blogspot.com/2013/01/leetcode-search-insert-position.html
	 */
	public int searchInsert2(int A[], int target) {
		int l = 0, r = A.length - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (A[mid] == target)
				return mid;

			/*
			 * can also be changed to: if (mid < r && A[mid] > target && A[mid +
			 * 1] < target) return mid+1;
			 */
			if (mid > l && A[mid] > target && A[mid - 1] < target)
				return mid;
			if (A[mid] > target) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return l;
	}

	// my latest. easier to understand
    public int searchInsert(int[] A, int target) {
        if(A == null || A.length == 0)
            return 0;
        
        int a = 0, b = A.length-1;
        while(a <= b) {
            int m = (a+b) >> 1;
            if(A[m] == target)
                return m;
            
            if(A[m] > target) {
                if(m == a || A[m-1] < target)
                    return m;
                else
                    b = m-1;
            } else {
                if(m == b || A[m+1] > target)
                    return m+1;
                else
                    a = m+1;
            }
        }
		// doesn't matter
        return -1;
    }

    public int searchInsert(int[] A, int target) {
        int low = 0, high = A.length-1;
        
        while(low <= high) {
            int mid = (low+high) >> 1;
            if(A[mid] == target)
                return mid;
            
            if(A[mid] > target) {
                if(mid == low)
                    return low;
                else
                    high = mid-1;
            } else {
                if(mid == high)
                    return high+1;
                else
                    low = mid+1;
            }
        }
        
        return low;
    }

	/*
	 * very difficult to handle border situations.
	 */
	public int searchInsert3(int[] A, int target) {
		int low = 0;
		int high = A.length - 1;

		if (A == null || A.length == 0)
			return 0;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (A[mid] == target)
				return mid;
			else if (A[mid] < target)
				low = mid + 1;
			else
				high = mid - 1;
		}

		int mid = (low + high) / 2;
		if (A[mid] > target)
			return mid;
		else
			return mid + 1;
	}
}
