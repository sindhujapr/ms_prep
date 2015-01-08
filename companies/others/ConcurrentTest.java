package fk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by qingcunz on 10/20/14.
 */
public class ConcurrentTest {
    public static void main(String[] args) {
        f();
    }

    public static void f() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(int i = 0; i < 10; i++) {
            map.put(i, new Integer(i).toString());
        }

        Iterator<Map.Entry<Integer, String>> iter = map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Integer, String> entry = iter.next();
            System.out.println("Removing " + entry.getValue());
            iter.remove();
        }
//        for(int val : map.keySet()) {
//            map.remove(val);
//        }
    }
}
