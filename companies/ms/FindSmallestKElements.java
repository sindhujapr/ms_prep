package interview.ms;

import java.util.Arrays;

public class FindSmallestKElements {
    public static void findSmallestK(int[] array, int k) {
	int[] result = new int[k];
	for (int i = 0; i < k && i < array.length; i++) {
	    result[i] = array[i];
	}
	Arrays.sort(result);
	
	if(array.length <= k) {
	    System.out.println(Arrays.toString(result));
	    return;
	}
	
	for (int i = k; i < array.length; i++) {
	    if(array[i] < result[k-1]) {
		result[k-1] = array[i];
		Arrays.sort(result);
	    }
	}
	
	System.out.println(Arrays.toString(result));
    }
    
    public static void main(String[] args) {
	int[] array = {8, 6, 5, 1, 2, 4, 7, 3};
	findSmallestK(array, 4);
    }
}
