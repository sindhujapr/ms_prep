package javautils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    /*
     * we can use reflection to create instance even
     * the default constructor is private!
     */
    private Reflection(){}

    public static void f(int i) {
    System.out.println(i);
    }
    
    public void g(char c) {
    System.out.println(c);
    }
    
    public static void h(int...array) {
    for(int i : array) {
        System.out.println(i);
    }
    }

    public static void main(String[] args) {
    h(1, 2, 3);
    
    try {
        Class<?> clazz = Class.forName("javautils.Reflection");
        Reflection instance = (Reflection) clazz.newInstance();

        Method[] methods = clazz.getMethods();
        for(Method method : methods) {
        if(method.getName().equals("f")) {
            method.invoke(null, 10);
        }
        
        if(method.getName().equals("g")) {
            method.invoke(instance, '5');
        }
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (SecurityException e) {
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    }
    }
}