package interview.google;

public class SearchElementInSortedArrayWithDup {
    public static void search(int[] array, int value) {
	int index = search(array, 0, array.length-1, value);
	if(index == -1)
	    System.out.println("not found");
    }
    
    private static int search(int[] array, int low, int high, int value) {
	int index = (low+high)/2;
	while(low <= high) {
	    if(array[index] == value) {
		System.out.println("find " + value + " at index " + index);
		return index;
	    } else if(array[index] > value) {
		high = index-1;
	    } else {
		low = index+1;
	    }	    
	    index = (low+high)/2;
	}
	
	return -1;
    }
    
    public static void main(String[] args) {
	int[] array = { 1, 2, 3, 4, 4, 5, 6, 7, 8, 8, 9, 10, 11, 11, 11, 12, 14, 15, 16 };
	search(array, 10);
    }
}