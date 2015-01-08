package interview.java;

public class LazyInitialization {
	private static class FieldHolder {
		static {
			System.out.println("inner class");
		}
		static volatile int  field = 10;
	}
	
	static int getField() { return FieldHolder.field; }
	
	public static void main(String[] args) {
		System.out.println("hello");
		System.out.println(getField());
		System.out.println("world");
		
		try {
			Class<?> listClazz = Class.forName("java.util.List");
			System.out.println(listClazz.getCanonicalName());
			
			Class<?> intClazz = int.class;//Class.forName("int");
//			if(intClazz.isPrimitive())
//			    intClazz = ClassUtils.primitiveToWrapper(clazz);
			Object i = intClazz.newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}