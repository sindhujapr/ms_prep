package lc;

import java.util.ArrayList;
import java.util.List;

// see also http://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/
public class LargestRectangleInHistogram {
    public ArrayList<String> restoreIpAddresses(String s) {
		ArrayList<String> res = new ArrayList<String>();

        if(s.length() >= 4 && s.length() <= 12)
            restore(s, 0, new StringBuilder(), res);
        return res;
	}
	
	private void restore(String s, int start, StringBuilder builder, ArrayList<String> res) {
	    if(start == builder.length()) {
	        if(valid(builder))
	            res.add(builder.toString().substring(0, builder.length()-1));
	        return;
	    }
	    
	    for(int i = start; i < s.length(); i++) {
	        if(i-start > 0 && s.charAt(i) == '0')
	            break;
            
            int val = Integer.valueOf(s.substring(start, i+1));
            if(val > 255)
                break;
            
            builder.append(val).append('.');
            
            restore(s, i+1, builder, res);
            
            for(int j = 0; j <= i-start+1; j++)
                builder.deleteCharAt(builder.length()-1);
	    }
	}
	
	private boolean valid(StringBuilder builder) {
	    int cnt = 0;
	    for(char ch : builder.toString().toCharArray())
	        if(ch == '.')
	            cnt++;
	            
	    return cnt == 4;
	}
	
	public static void main(String[] args) {
	
		LargestRectangleInHistogram instance = new LargestRectangleInHistogram();
		System.out.println(instance.largestRectangleArea(new int[] { 1, 2, 3, 1, 2, 2}));
		System.out.println(instance.restoreIpAddresses("0000"));
	}

	/*
	 * http://fisherlei.blogspot.com/2012/12/leetcode-largest-rectangle-in-histogram.html
	 */
	int largestRectangleArea(int[] height) {
		if (height == null || height.length == 0)
			return 0;
		
		int n = height.length;
		List<Integer> stack = new ArrayList<Integer>();
		int sum = 0;
		for (int i = 0; i <= n; i++) {
			int top = stack.size()-1;
			if (stack.isEmpty() || (i < n && height[i] > height[stack.get(top)]))
				stack.add(i);
			else {
				int lastIndex = stack.remove(top);
				top = stack.size()-1;
				int width = stack.isEmpty() ? i : i - stack.get(top) - 1;
				int area = height[lastIndex] * width;
				sum = Math.max(sum, area);
				i--;
			}
		}
		return sum;
	}

	public int largestRectangleArea1(int[] height) {
		if (height == null || height.length == 0)
			return 0;

		/*
		 * left[i]: with current height[i], how far to the left can I reach with current
		 * height[i]. That means, all height[j] (left[i] <= j <= i) are greater than or
		 * equal to height[i].
		 */
		int[] left = new int[height.length];
		int[] right = new int[height.length];
		for (int i = 0; i < height.length; i++) {
			if (i == 0 || height[i] > height[i - 1])
				left[i] = i;
			else {
				int j = left[i - 1] - 1;
				while (j >= 0 && height[j] >= height[i])
					j--;
				left[i] = j + 1;
			}
		}
		for (int i = height.length - 1; i >= 0; i--) {
			if (i == height.length - 1 || height[i] > height[i + 1])
				right[i] = i;
			else {
				int j = right[i + 1] + 1;
				while (j < height.length && height[j] >= height[i])
					j++;
				right[i] = j - 1;
			}
		}

		int res = 0;
		for (int i = 0; i < height.length; i++) {
			res = Math.max(res, (right[i] - left[i] + 1) * height[i]);
		}
		return res;
	}
}
