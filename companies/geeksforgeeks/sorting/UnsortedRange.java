// http://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/
public class UnsortedRange {
    public void range(int[] arr) {
        int start = -1, end = -1;

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] < arr[i-1]) {
                start = i-1;
                break;
            }
        }

        for(int j = arr.length-2; j >= 0; j--) {
            if(arr[j] > arr[j+1]) {
                end = j+1;
                break;
            }
        }

        if(start == -1 || end == -1) {
            System.out.println("already sorted");
            return;
        }

        // incomplete!!!
    }
}
