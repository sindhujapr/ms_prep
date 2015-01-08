package geeksforgeeks.divide.conquer;

/*
 * http://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/
 */
public class LargestRectangleInHistogram {
	public static void main(String[] args) {
		int[] hist = {6, 2, 5, 4, 5, 1, 6};
		System.out.println(getMaxRectangle(hist));
	}

	public static int getMaxRectangle(int[] hist) {
		int[] st = buildRangeMinQueryTree(hist);
		int n = hist.length;
		
		return _getMaxRectangle(hist, 0, n-1, st);
	}

	private static int _getMaxRectangle(int[] hist, int start, int end, int[] st) {
		if(start >= end)
			return hist[start];
		
		int minIndex = query(st, hist.length, start, end);
		int left = _getMaxRectangle(hist, start, minIndex-1, st);
		int right = _getMaxRectangle(hist, minIndex+1, end, st);
		int max = Math.max(left, right);
		
		return Math.max(max, hist[minIndex] * (end-start+1));
	}

	public static int query(int[] st, int n, int qs, int qe) {
		return _query(st, 0, n-1, qs, qe, 0);
	}
	
	// return the index of the minimum element in the range
	private static int _query(int[] st, int start, int end, int qs, int qe, int index) {
		if(qs <= start && qe >= end)
			return st[index];
		
		if(qs > end || qe < start)
			return Integer.MAX_VALUE;
		
		int mid = (start+end) >>> 1;
		int left = _query(st, start, mid, qs, qe, 2*index+1);
		int right = _query(st, mid+1, end, qs, qe, 2*index+2);

		// This seems incorrect because we cannot compare the indices themselves!
		return Math.min(left, right);
	}

	public static int[] buildRangeMinQueryTree(int[] array) {
		int n = array.length;
		int[] st = new int[2*n+1];
		_buildRangeMinQueryTree(array, 0, n-1, st, 0);
		return st;
	}

	// Keep the index of the minimum element in the segment tree
	private static int _buildRangeMinQueryTree(int[] array, int start, int end, int[] st, int index) {
		if(start >= end) {
			st[index] = array[start];
			return start;
		}
		
		int mid = (start+end) >>> 1;
		int left = _buildRangeMinQueryTree(array, start, mid, st, 2*index+1);
		int right = _buildRangeMinQueryTree(array, mid+1, end, st, 2*index+2);
		
		// st is used to keep the index of the minimum element in a range!
		st[index] = array[left] < array[right] ? left : right;
		return st[index];
	}
}