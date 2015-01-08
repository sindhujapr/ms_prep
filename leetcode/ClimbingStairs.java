package lc;

public class ClimbingStairs {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ClimbingStairs instance = new ClimbingStairs();
            System.out.println(instance.climbStairs(i + 1));
        }

        System.out.println(new ClimbingStairs().climbStairs(2));
    }

    public int climbStairs(int n) {

        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int fn1 = 1;
        int fn2 = 2;
        int fn = 0;
        n -= 2;

        while (n > 0) {
            n--;
            fn = fn1 + fn2;
            fn1 = fn2;
            fn2 = fn;
        }

        return fn;
    }
    
    public int climbStairs2(int n) {
        assert n > 0;
        
        if(n == 1)
            return 1;
        
        int[] count = new int[n+1];
        count[0] = count[1] = 1;
        
        for(int i = 2; i <= n; i++)
            count[i] = count[i-1] + count[i-2];
            
        return count[n];
    }

    public int climbStairs3(int n) {
        if(n == 0)
            return 1;
        
        int[] hist = new int[2];
        hist[0] = hist[1] = 1;
        for(int i = 2; i <= n; i++) {
            int temp = hist[0] + hist[1];
            hist[0] = hist[1];
            hist[1] = temp;
        }
        
        return hist[1];
    }
}
