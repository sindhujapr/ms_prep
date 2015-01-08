package lc;

public class TrappingRainWater {
	public static void main(String[] args) {
		System.out.println(new TrappingRainWater().trap1(new int[] { 3,1,2,4,0,5,1 }));
		
		String[] array = "1,2,3,4,5,5,".split(",");
		int[] res = new int[array.length];
		for(int i = 0; i < array.length; i++)
			res[i] = Integer.valueOf(array[i]);
		
		for(int val : res)
			System.out.println(val);
	}
	
    public int trap(int[] A) {
		if (A == null || A.length < 3)
			return 0;

        int[] left = new int[A.length];
        int[] right = new int[A.length];
        for(int i = 0; i < A.length; i++)
            left[i] = i > 0 ? Math.max(A[i], left[i-1]) : A[i];
        
        for(int i = right.length-1; i >= 0; i--)
            right[i] = i == right.length-1 ? A[i] : Math.max(A[i], right[i+1]);
        
        int sum = 0;
        for(int i = 1; i < A.length-1; i++) {
            int height = Math.min(left[i], right[i]);
            if(height > A[i])
                sum += height-A[i];
        }

		return sum;
	}
    
    /*
     * Even less memory footprint:
     * http://qiang129.blogspot.com/2013/04/leetcode-trapping-rain-water.html
     */
    public int trap1(int[] A) {
        if(A.length < 3)
            return 0;
           
        int total = 0;
        int left = A[0];
        int right = A[A.length-1];
       
        int i=0, j = A.length-1;
        while(i < j){
            if(left <= right){
                i++;
                if(A[i] < left)
                    total += left - A[i];
                else   
                    left = A[i];
            } else {
                j--;
                if(A[j] < right)
                    total += right - A[j];
                else
                    right = A[j];
            }
        }
       
        return total;
    }
    
	/*
	 * A little better since memory footprint is reduced.
	 * http://gongxuns.blogspot.com/2012/12/leetcodetrapping-rain-water.html
	 */
	public int trap2(int[] A) {
		int res = 0;
		if (A.length < 3)
			return res;

		int[] left = new int[A.length - 2];
		int[] right = new int[A.length - 2];
		for (int i = 0; i < A.length - 2; i++)
			left[i] = i > 0 ? Math.max(left[i - 1], A[i]) : A[i];

		for (int i = A.length - 3; i >= 0; i--)
			right[i] = i < A.length - 3 ? Math.max(right[i + 1], A[i + 2])
					: A[i + 2];

		for (int i = 0; i < A.length - 2; i++) {
			int temp = Math.min(left[i], right[i]);
			if (temp > A[i + 1])
				res += temp - A[i + 1];
		}

		return res;
	}
}