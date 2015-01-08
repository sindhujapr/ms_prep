package interview.java;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

class A1 {}
class B1 extends A1 {}
class C1 extends A1 {}

public class Test {
	public static void main(String[] args) {
		ConcurrentSkipListSet<String> myset = new ConcurrentSkipListSet<String>();
		myset.add("me");
		myset.add("you");
		myset.add("your");
		myset.add("they");
		myset.add("she");
		NavigableSet<String> myset1 = myset.headSet("m");
		System.out.println(myset1.size());
		
//		System.out.println(10%1);
//		assert (10%3 > 0) : 2;
		int i = 0;
		try {
			for(; true; i++) {
				if(i/i++ > 0)
					break;
			}
		} catch(RuntimeException e) {
			System.out.println("runtime");
//		} catch(ArithmeticException e) {
//			System.out.println("arite");
		} catch (Exception e) {
			System.out.println("exception");
		} finally {
			System.out.println("finally");
		}

		boolean assertEnabled = false;
		assert assertEnabled = true;
		System.out.println(assertEnabled ? "enabled" : "disabled");
	}
}
