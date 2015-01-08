package interview.java;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

class RefBase {
    private int value;
    protected RefBase() { value = 10; }
    public RefBase(int value) { this.value = value; }
    private int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}

public class ReflectionTest {
    public void f() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Class<?> cl = null;
        try {
            cl = Class.forName("interview.java.ReflectionTest");
            ReflectionTest s = (ReflectionTest) cl.newInstance();
            s.f();
            // Why it fails? Set is interface and cannot be instantiated
//          cl = Class.forName("java.util.Set");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        try {
            cl = Class.forName("interview.java.RefBase");
//          int i = ((RefBase) (cl.newInstance())).getValue();
//          System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method[] methods = cl.getMethods();
            for(Method method : methods)
                System.out.println(method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("==================================");
        try {
            Method[] methods = cl.getDeclaredMethods();
            for(Method method : methods) {
                System.out.println(method.getName());
                if(method.getName().equals("getValue")) {
                    method.setAccessible(true);
                    Object o = method.invoke(cl.newInstance());
                    System.out.println("class: " + o.getClass().getName());
                    System.out.println(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}