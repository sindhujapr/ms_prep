package interview.a9;

import java.util.ArrayList;
import java.util.List;

public class SameNumbersFromSortedArrays {
	public static void main(String[] args) {
		 int[] arr1 = {1, 3, 7, 9, 16, 21, 36};
		 int[] arr2 = {3, 4, 6, 9, 23, 36, 43};
		 List<Integer> res = new SameNumbersFromSortedArrays().findSameNumbers(arr1, arr2);
		 System.out.println(res);
	}
	
	// O(m+n)
	public List<Integer> findSameNumbers(int[] num1, int[] num2) {
		List<Integer> res = new ArrayList<Integer>();
		
		for(int i = 0, j = 0; i < num1.length && j < num2.length; ) {
			if(num1[i] == num2[j]) {
				res.add(num1[i]);
				i++;
				j++;
				continue;
			}
			
			if(num1[i] < num2[j])
				i++;
			else
				j++;
		}
		return res;
	}
}