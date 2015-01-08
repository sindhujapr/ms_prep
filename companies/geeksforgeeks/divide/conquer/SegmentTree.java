package geeksforgeeks.divide.conquer;

/*
 * http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 * 
 * This enables query and update in O(lgn) time complexity
 * 
 * see also the Fenwick tree for another or even better approach:
 * http://en.wikipedia.org/wiki/Fenwick_tree
 * http://petr-mitrichev.blogspot.com/2013/05/fenwick-tree-range-updates.html
 */
public class SegmentTree {
    public static void main(String[] args) {
        int[] array = {10, 8, 1, 4, 6, 4, 3};
        int n = array.length;
        int[] st = buildST(array);
        System.out.println(queryST(st, 0, n-1, 2, 5, 0));
        
        
        updateST(st, array, 3, 6);
        System.out.println(queryST(st, 0, n-1, 2, 5, 0));
    }
    
    public static int[] buildST(int[] array) {
        /*
         * see reference link for how to set the array size. The below may not work for all input
         */
        int n = array.length;
        int[] st = new int[2*n-1];
        
        _buildST(array, 0, n-1, st, 0);
        return st;
    }

    private static int _buildST(int[] array, int i, int j, int[] st, int index) {
        if(i >= j) {
            st[index] = array[i];
            return st[index];
        }

        int mid = (i+j) >> 1;
        int sum = _buildST(array, i, mid, st, 2*index+1);
        sum += _buildST(array, mid+1, j, st, 2*index+2);
        st[index] = sum;
        
        return st[index];
    }
    
    public static int queryST(int[] st, int start, int end, int qs, int qe, int index) {
        if(qs <= start && qe >= end)
            return st[index];
        
        if(qs > end || qe < start)
            return 0;
        
        int mid = (start+end) >> 1;
        return queryST(st, start, mid, qs, qe, 2*index+1) + queryST(st, mid+1, end, qs, qe, 2*index+2);
    }
    
    public static void updateST(int[] st, int[] array, int i, int newVal) {
        assert i >= 0 && i < array.length;
        int diff = newVal - array[i];
        array[i] = newVal;
        
        _updateST(st, 0, array.length-1, i, 0, diff);
    }
    
    /*
     * i is the position whose value will be updated
     * index is the starting point (root at the beginning)
     */
    private static void _updateST(int[] st, int start, int end, int i, int index, int diff) {
        if(i < start || i > end)
            return;
        
        st[index] += diff;

        if(start == end)
            return;

        int mid = ( start+end) >>> 1;
        _updateST(st, start, mid, i, 2*index+1, diff);
        _updateST(st, mid+1, end, i, 2*index+2, diff);
    }
}
