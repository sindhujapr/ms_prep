package geeksforgeeks.divide.conquer;

public class SquareRoot {
    public static void main(String[] args) {
//      System.out.println(sqrt(2));
    }
    
    public static int sqrt(int N) {
        assert N >= 0;
        
        int start = 0, end = N;
        while(start <= end) {
            int mid = (start+end) >> 1;
            long res = (long) mid * (long) mid;
            long plus = (long) (mid+1) * (long) (mid+1);
            
            if(res <= (long) N && plus > (long) N)
                return mid;
            else if(res < (long) N)
                start = mid+1;
            else
                end = mid-1;
        }
        
        return -1;
    }
}