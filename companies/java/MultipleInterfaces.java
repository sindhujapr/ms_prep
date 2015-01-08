package interview.java;

import java.io.IOException;
import java.lang.reflect.Method;

interface Type1 {
    void f() throws CloneNotSupportedException;
}

interface Type2 {
    void f() throws InterruptedException;
}

interface Type33 extends Type1, Type2 {
    /*
     * For multiple implementation with the same method declaration, the thrown exception
     * should be the intersection of the exception thrown by methods declared in the parent interfaces.
     */
//  void f() throws IOException;
}

public class MultipleInterfaces implements Type33 {
    public void f() {
        System.out.println("hello world");
    }
    
    public static void main(String[] args) {
        try {
            
        } catch (Throwable e) {
            
        }
        
        Type33 t3 = new MultipleInterfaces();
        t3.f();

        Class<?> clz = t3.getClass();
        printMethods(clz);
        printDeclaredMethods(clz);
        
        System.out.println("==================interfaces==============");
        printInterfaces(clz);
    }
    
    // Recursively
    public static void printInterfaces(Class<?> clazz) {
        Class[] interfaces = clazz.getInterfaces();
        for(Class clz : interfaces) {
            Method[] mth = clz.getDeclaredMethods();
            System.out.print(clz.getName() + "\t");
            for(Method method : mth)
                System.out.println(method.getName() + "\t");
            System.out.println();
            
            printInterfaces(clz);
        }
    }
    
    public static void printMethods(Class<?> clazz) {
        System.out.println("===========methods=====================");
        Method[] methods = clazz.getMethods();
        for(Method method : methods)
            System.out.println(method.getName());
    }
    
    public static void printDeclaredMethods(Class<?> clazz) {
        System.out.println("===========declared methods=====================");
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods)
            System.out.println(method.getName());
    }
}