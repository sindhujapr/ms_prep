package careercup.google;

/*
 * http://www.careercup.com/question?id=12697664
 */
public class FindDoubleInBST {
    public static void main(String[] args) {
        double[] values = {1.2, 3.4, 4.4, 5.72, 8.44, 10.24, 13.24, 55.66, 58.92};
        findDouble(values, 12.34);
    }

    private static void findDouble(double[] values, double d) {
        int start = 0, end = values.length-1;
        double minDiff = Double.MAX_VALUE;
        double min = Double.MAX_VALUE;
        
        while(start <= end) {
            int mid = (start+end) >>> 1;
            double diff = Math.abs(values[mid]-d);
            if(diff < minDiff) {
                min = values[mid];
                minDiff = diff;
            }
            
            if(Double.compare(values[mid], d) > 0)
                end = mid-1;
            else if(Double.compare(values[mid], d) < 0)
                start = mid+1;
        }
        
        System.out.println(min);
    }
}