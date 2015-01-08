package careercup.fb;

/*
 * http://www.careercup.com/question?id=4635417542393856
 */
public class SquareRoot {
    public static void main(String[] args) {
        double root = sqrRoot(2.0);
        System.out.println(root);
        System.out.println(root * root);
    }
    
    public static double sqrRoot(double value) {
        final double EPS = 0.00001;
        assert value >= 0;
        double low = 0., high = Math.max(value, 1.0);
        
        while(Math.abs(high-low) > EPS) {
            double mid = (low+high)/2;
            double square = mid * mid;
            
            if (square > value)
                high = mid;
            else
                low = mid;
        }
        
        return low;
    }
}