package interview.java;

import java.lang.reflect.Constructor;

public class Outer {
	public static void main(String[] args) throws Exception {
		new Outer().greetWorld();
	}
	
	private void greetWorld() throws Exception {
		Constructor<?> c = Inner.class.getConstructor(Outer.class);
		System.out.println(c.newInstance(Outer.this));
	}

	class Inner {
		public String toString() {
			return "Hello world";
		}
	}
}