package interview.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

class AAA {
	public AAA() {
	}
}

class B extends AAA {
	public B() {
	}
}

public class ForNameTest {
	public static void main(String[] args) {
		Class<AAA> c = null;

		// 1. Does not produce exception at run-time even though I cast Class<B>
		// to Class<A>
		try {
			c = (Class<AAA>) Class.forName("interview.java.B");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		// This is ridiculous compiler behavior. We need to take care of the warning!!!
		List<Class<AAA>> list = new ArrayList<Class<AAA>>();
		list.add(c);
		
		System.out.println(c.getCanonicalName());

		// 2. Compile time error: Cannot Cast Class<B> to Class<A>
		c = (Class<AAA>) (Class<?>)B.class; // Error
		
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		try {
			Class clz = cl.loadClass("java.util.ArrayList");
			try {
				@SuppressWarnings("rawtypes")
				List alist = (ArrayList)clz.newInstance();
				System.out.println(alist.size());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			System.out.println("h");
		}
		
	}
}