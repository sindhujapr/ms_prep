package lc;

public class Sqrt {
    public int sqrt(int x) {
        double low = 0;
        /*
         * for special case Integer.MAX_VALUE, whose square root is 46340
         */
        double high = 46341;
        while(low < high) {
            double mid = (low+high) / 2;
            int value = (int) (mid * mid);
            if(value == x)
                return (int) mid;
            else if(value < x)
                low = mid;
            else
                high = mid;
        }
        
        return -1;
    }
    
    /*
     * https://github.com/mengli/leetcode/blob/master/Sqrt(x).java
     */
    public int sqrt1(int x) {
    	double x0 = x / 2.0;
		double x1 = (x0 + x / x0) / 2.0;
		while (Math.abs(x1 - x0) > 0.00001) {
			x0 = x1;
			x1 = (x0 + x / x0) / 2.0;
		}
        return (int) x1;
    }
    
    public static void main(String[] args) {
    	Sqrt instance = new Sqrt();
    	System.out.println(instance.sqrt(2147483647));
    	System.out.println(46341 * 46341);
    	
    	System.out.println(Integer.MAX_VALUE);
    	System.out.println(Integer.MIN_VALUE);
    	System.out.println(Long.MAX_VALUE);
    }
}
