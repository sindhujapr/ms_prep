
/*
 * http://www.glassdoor.com/Interview/Isomorphic-trees-QTN_368012.htm
 */
public class A3B3C3D3 {
    public static void a3b3c3d3() {
        Hashtable<Integer, ArrayList<String>> h = new Hashtable<Integer, ArrayList<String>>();
        for (int a = 0; a < 100; a++) {
            for (int b = a; b < 100; b++) {
                Integer lhs = new Integer(a * a * a + b * b * b);
                ArrayList<String> prev = h.get(lhs);
                if (null == prev) prev = new ArrayList<String>();
                for (String s : prev) {
                    System.out.println(a + " " + b + " = " + s);
                }
                prev.add(a + " " + b);
                h.put(lhs, prev);
            }
        }
    }
}
