package lc;

public class SearchRotatedSortedArrayII {
    public static void main(String[] args) {
        System.out.println(new SearchRotatedSortedArrayII().search(new int[] {1}, 0));
    }

    public boolean search(int[] A, int target) {
        if(A == null || A.length == 0)
            return false;
        
        int a = 0, b = A.length-1;
        while(a <= b) {
            while(a < b && A[a] == A[b])
                a++;
                
            int m = (a+b) >> 1;
            
            if(A[m] == target)
                return true;
            
            if(A[m] >= A[a]) {
                if(target >= A[a] && target <= A[m])
                    b = m-1;
                else
                    a = m+1;
            } else {
                if(target >= A[m] && target <= A[b])
                    a = m+1;
                else
                    b = m-1;
            }
        }
        return false;
    }

    // http://fisherlei.blogspot.com/2013/01/leetcode-search-in-rotated-sorted-array_3.html
    public boolean search(int[] A, int target) {
        if(A == null || A.length == 0)
            return false;

        int start = 0, end = A.length-1;
        while(start <= end) {
            int m = (start+end) >> 1;
            if(A[m] == target)
                return true;
            
            int val = A[m];
            if(val == A[start]) {
                start++;
            } else if(val > A[start]) {
                if(target >= A[start] && target <= A[m])
                    end = m-1;
                else
                    start = m+1;
            } else {
                if(target >= A[m] && target <= A[end])
                    start = m+1;
                else
                    end = m-1;
            }
        }
        return false;
    }


    //http://jane4532.blogspot.com/2013/07/search-in-rotated-sorted-array.html
    public boolean search(int[] A, int target) {
        if(A == null || A.length == 0)
            return false;

        int l = 0, r = A.length-1;
        
        while(l <= r) {
            int m = (l+r) >> 1;
            if(A[m] == target)
                return true;
            
            if(A[l] <= A[m]) {
                while(A[l] == A[m] && l < m)
                    m--;
                    
                if(target >= A[l] && target <= A[m])
                    r = m;
                else
                    l = m+1;
            } else if(A[l] >= A[m]) {
                while(A[l] == A[m] && m < r)
                    m++;
                
                if(target <= A[r] && target >= A[m])
                    l = m;
                else
                    r = m-1;
            }
        }
        
        return false;
    }
}
