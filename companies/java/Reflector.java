package interview.java;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Reflector {
    public static void main(String[] args) throws Exception {
        Set<String> s = new HashSet<String>();
        s.add("foo");
        
        Iterator<String> it = s.iterator();
        // Illegal
//      Method m = it.getClass().getMethod("hasNext");
        Method m = Iterator.class.getMethod("hasNext");
        System.out.println(m.invoke(it));
    }
}