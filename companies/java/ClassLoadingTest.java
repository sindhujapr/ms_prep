package interview.java;

class AAAA {
	static {
		System.out.println("time = " + System.currentTimeMillis());
	}
}

class Main1 {
	public static void main(String... args) throws Throwable {
		final Class<?> c = Class.forName("interview.java.AAAA");
	}
}

class Main2 {
	public static void main(String... args) throws Throwable {
		ClassLoader.getSystemClassLoader().loadClass("interview.java.AAAA");
	}
}

public class ClassLoadingTest {
	public static void main(String[] args) throws Exception {
		/*
		 * CloassLoader.loadClass() doesn't perform initialization for the loaded class.
		 * In another words, <clinit> won't be called.
		 */
//		ClassLoader.getSystemClassLoader().loadClass("interview.java.AAAA");
		final Class<?> c = Class.forName("interview.java.AAAA");
	}
}
