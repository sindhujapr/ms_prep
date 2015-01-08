package lc;

/*
 * see conclusion here:
 * http://discuss.leetcode.com/questions/193/container-with-most-water
 */
public class ContainerWithMostWater {
	public int maxArea(int[] height) {
		int len = height.length, low = 0, high = len - 1;
		int maxArea = 0;
		while (low < high) {
			int area = (high - low) * Math.min(height[low], height[high]);
			if(area > maxArea)
				maxArea = area;

			if (height[low] < height[high]) {
				low++;
			} else {
				high--;
			}
		}
		return maxArea;
	}
}