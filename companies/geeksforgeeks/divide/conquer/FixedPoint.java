package geeksforgeeks.divide.conquer;

/*
 * http://www.geeksforgeeks.org/find-a-fixed-point-in-a-given-array/
 */
public class FixedPoint {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, 4};
        System.out.println(findFixedPoint(arr, 0, arr.length-1));
        
        int[] array = {1, 2, 3, 4};
        System.out.println(findFixedPoint(array, 0, array.length-1));
        
        int arr1[] = {-10, -1, 0, 3, 10, 11, 30, 50, 100};
        System.out.println(findFixedPoint(arr1, 0, arr1.length-1));

    }
    
    public static int findFixedPoint(int[] array, int start, int end) {
        if(start <= end) {
            int mid = (start+end) >>> 1;
            if(array[mid] == mid)
                return mid;
            else if(mid > array[mid])
                return findFixedPoint(array, mid+1, end);
            else 
                return findFixedPoint(array, start, mid-1);
        }
        
        return -1;
    }
}