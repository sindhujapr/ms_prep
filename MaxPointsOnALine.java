package lc;

import java.util.Map;
import java.util.TreeMap;

public class MaxPointsOnALine {
    static class Point {
        int x, y;

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    static class Slope implements Comparable<Slope> {
        int x, y;

        Slope(int a, int b) {
            x = a;
            y = b;
        }

        @Override
        public int compareTo(Slope other) {
            return this.x * other.y - this.y * other.x;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    private int numPointsCentered(Point[] points, int id) {
        /*
         * We have two choices: TreeMap and HashMap. With TreeMap, we only need
         * to implement Comparable interface, which is relatively easy (see above).
         * However, with HashMap, we have to implement hashCode() and equals().
         * Mover, new Slope(1,1).equals(new Slope(2,2)), which means we need to make
         * Slope(2,2) like Slope(1,1) by dividing. Otherwise, the hash code will be
         * different. 
         */
        Map<Slope, Integer> counts = new TreeMap<Slope, Integer>();
        int dup = 0;
        for (int i = 0; i < points.length; i++) {
            if (i == id)
                continue;

            /*
             * reason why we tree duplicate points specially:
             * if we add them (Slope(0, 0)) to the map, then according to our compareTo
             * implementation, it will equal to any node in the map, which is wrong and 
             * results in over-counting. 
             */
            if (points[i].x == points[id].x && points[i].y == points[id].y) {
                dup++;
                continue;
            }
            Slope slope = new Slope(points[i].x - points[id].x, points[i].y - points[id].y);
            counts.put(slope, counts.containsKey(slope) ? counts.get(slope) + 1 : 2);
        }

        int max = 1;
        for (Map.Entry<Slope, Integer> entry : counts.entrySet())
            max = Math.max(max, entry.getValue());

        /*
         * There seems a bug in JDK library. If we use the below code but without the if
         * statement in the Slope constructor, there will be NPE with one test case.
         * for (Slope slope : counts.keySet())
         *      max = Math.max(max, counts.get(slope));
         */
        return max + dup;
    }

    /*
     * http://zinjiggle.blogspot.com/2013/12/leetcode-max-points-on-lin.html
     */
    public int maxPoints(Point[] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++)
            res = Math.max(res, numPointsCentered(points, i));

        return res;
    }

    public static void main(String[] args) {
        Point[] points = { new Point(0, -12), new Point(5, 2), new Point(2, 5), new Point(0, -5),
                new Point(1, 5), new Point(2, -2), new Point(5, -4), new Point(3, 4), new Point(-2, 4),
                new Point(-1, 4), new Point(0, -5), new Point(0, -8), new Point(-2, -1), new Point(0, -11),
                new Point(0, -9) };
        System.out.println(new MaxPointsOnALine().maxPoints(points));
    }
}
