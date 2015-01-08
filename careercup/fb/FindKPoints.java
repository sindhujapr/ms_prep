package careercup.fb;

/*
 * http://www.careercup.com/question?id=5309537623998464
 */
public class FindKPoints {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return new StringBuilder().append(x).append(",").append(y).toString();
        }
    }
    
    public static void main(String[] args) {
        Point[] points = { new Point(1, 1), new Point(3, 4), new Point(3, 5), new Point(1, 2), new Point(2, 3), new Point(2, 2)};
        for(Point point : findKClosest(points, 3))
            System.out.println(point);
    }
    
    // Use max heap to achieve O(nlgk)
    public static Point[] findKClosest(Point[] points, int k) {
        Point[] result = new Point[k];
        for(int i = 0; i < k; i++)
            result[i] = points[i];

        buildMaxHeap(result);
        
        for(int i = k; i < points.length; i++) {
            if(compare(points[k], result[0]) < 0) {
                result[0] = points[i];
                maxHeapify(result, 0, result.length);
            }
        }
        
        return result;
    }
    
    /*
     * different from HeapSort, we actually don't need limit since we always use
     * the last element as sentinel
     */
    private static void maxHeapify(Point[] points, int start, int limit) {
        if(start >= limit)
            return;

        int left = 2 * start + 1;
        int max = (left < limit && compare(points[start], points[left]) < 0) ? left : start;
        int right = 2 * start + 2;
        max = (right < limit && compare(points[max], points[right]) < 0) ? right : max;

        if(max != start) {
            swap(points, start, max);
            // better to use iterative algorithm
            maxHeapify(points, max, limit);
        }
    }

    private static void buildMaxHeap(Point[] points) {
        int n = points.length;
        for(int i = (n-2)/2; i >= 0; i--) {
            maxHeapify(points, i, n);
        }
    }

    private static void swap(Point[] points, int i, int j) {
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
    
    private static int compare(Point p1, Point p2) {
        double d1 = Math.sqrt(p1.x * p1.x + p1.y * p1.y);
        double d2 = Math.sqrt(p2.x * p2.x + p2.y * p2.y);
        return Double.compare(d1, d2);
    }
}