package oog;

public class LeastNumbersSumSquare {
    public static void main(String[] args) {
//        for(int i = 1; i < 100; i++)
//            System.out.println(i + ":\t" + leastNumbers(i));
        leastNumbers(100);
    }

    public static void leastNumbers(int n) {
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++)
            dp[i] = i;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= i/2; j++) {
                if (i == j * j) {
                    dp[i] = 1;
                    break;
                } else {
                    dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
                }
            }
        }

        for(int i = 1; i <= n; i++)
            System.out.println(i + ":\t" + dp[i]);
    }
}

