package interview.fb;

public class ClimbStairs {
    public static void main(String[] args) {
        ClimbStairs c = new ClimbStairs();
        for(int i = 5; i < 50; i++)
            if(c.climb(i) != c.climbStairs(i))
                System.out.println(i);
    }

    /*
	 * O(lgn). see also:
	 * http://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
	 * the matrix solution is tricky. The below one is awesome
	 */
    public int climbStairs(int n) {
        if(n == 1 || n == 2 || n == 3)
            return n;

        int low, high;
        if(n % 2 == 0){
            low = n / 2;
            high = n / 2;
        } else {
            low = (n-1)/2;
            high = (n+1)/2;
        }

        return climbStairs(low) * climbStairs(high) + climbStairs(low-1) * climbStairs(high-1);
    }

    // O(n);
    public int climb(int n) {
        if(n == 1 || n == 0)
            return 1;

        int hist[] = new int[2];
        hist[0] = hist[1] = 1;
        for(int i = 2; i <= n; i++) {
            int temp = hist[0] + hist[1];
            hist[0] = hist[1];
            hist[1] = temp;
        }

        return hist[1];
    }
}
