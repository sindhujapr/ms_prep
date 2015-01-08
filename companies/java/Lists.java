package interview.java;

import java.util.ArrayList;
import java.util.List;

public class Lists {
	public static <T> List<T> toList(T... arr) {
		List<T> list = new ArrayList<T>();
		for(T t : arr) list.add(t);
		return list;
	}
	
	public static void main(String[] args) {
		List<Integer> ints = Lists.toList(1, 2, 3);
		System.out.println(ints);
		List<String> words = Lists.toList("hello", "world");
		System.out.println(words);
		
		List<?> something = Lists.toList("one", 2, 3.0);
		System.out.println(something);
		
		// Doesn't compile
//		List<Object> objects = Lists.toList("one", 2, 3.0);
		List<Object> objects = Lists.<Object>toList("one", 2, 3.0);
		System.out.println(objects);
	}
}