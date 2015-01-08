package geeksforgeeks.divide.conquer;

import java.io.IOException;

/*
 * http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
 * http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=lowestCommonAncestor#Range_Minimum_Query_(RMQ)
 * 
 * use segment tree to perform minimum query efficiently
 */
public class RangeMinimumQuery {
    public static void main(String[] args) throws IOException {
        int[] array = {3, 1, 5, 4, 8, 10};
        int[] st = buildST(array);
        System.out.println(query(st, array.length, 2, 5));
        System.out.println(query(st, array.length, 0, 4));
    }
    
    public static int query(int[] st, int n, int qs, int qe) {
        return _query(st, 0, n-1, qs, qe, 0);
    }
    
    private static int _query(int[] st, int start, int end, int qs, int qe, int index) {
        if(qs <= start && qe >= end)
            return st[index];

        if(qs > end || qe < start)
            return Integer.MAX_VALUE;

        int mid = (start+end) >> 1;
        int left = _query(st, start, mid, qs, qe, 2*index+1);
        int right = _query(st, mid+1, end, qs, qe, 2*index+2);
        return Math.min(left, right);
    }
    
    public static int[] buildST(int[] array) {
        assert array != null && array.length > 0;

        /*
         * See reference about how to calculate the size of the array
         */
        int n = array.length;
        int[] st = new int[2*n+1];
        _buildST(array, 0, n-1, st, 0);
        return st;
    }
    
    private static int _buildST(int[] array, int start, int end, int[] st, int index) {
        if(start == end) {
            st[index] = array[start];
            return array[start];
        }
        
        int mid = (start+end) >>> 1;
        // should it be better to be mid-1 thus the tree is balanced?
        int left = _buildST(array, start, mid, st, 2*index+1);
        int right = _buildST(array, mid+1, end, st, 2*index+2);
        st[index] = Math.min(left, right);
        return st[index];
    }
}
