package interview.turn;

import java.util.ArrayList;
import java.util.List;

public class LastElementAfterAlternativeRemove {
	public static void main(String[] args) {
		for(int i = 1; i <= 128; i++)
			System.out.println(i + "\t" + lastElement(init(i)));
		System.out.println(lastElement(init(2000)));
	}
	
	public static List<Integer> init(int size) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < size; i++)
			list.add(i+1);
		return list;
	}
	
	public static int lastElement(List<Integer> list) {
		int i = 0;
		while(list.size() > 1) {
			/*
			 * actually we don't need condition list.size() > 1
			 */
			while(i < list.size() && list.size() > 1) {
				list.remove(i);
				i++;
			}
			
			/*
			 * two situations:
			 * the index of the recently removed element is the last one: i-list.size() == 1
			 * the index of the recently removed element is the 2nd last one: i-list.size() == 0
			 */
			i = i == list.size() ? 0 : 1;
		}
		
		return list.get(0);
	}
	
	// simplified version that uses only one loop
	public static int lastElement1(List<Integer> list) {
		int index = 0;
		while(list.size() > 1) {
			/*
			 * we have to do rounding after each removal but not:
			 * index++;
			 * list.remove(index % list.size()); 
			 * 
			 * The reason is that if we don't do rounding (when index is larger than or equal to 
			 * the size of the array), it will inherit the (++) effect of last loops (from beginning
			 * to the end) 
			 */
			list.remove(index % list.size());
			index = (++index) % list.size();
		}
		
		return list.get(0);
	}
}