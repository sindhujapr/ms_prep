package careercup.fb;

/*
 * see analysis:
 * http://www.careercup.com/question?id=5399897561890816
 */
public class FlipBits {
    public static void main(String[] args) {
        System.out.println(isWinner(37));
        System.out.println(isWinner(2));
        System.out.println(isWinner(12));
    }

    /*
     * count the total number of zeros before ones
     */
    public static boolean isWinner(int n) {
        int sum = 0;
        int zeros = 0;
        while (n > 0) {
            if ((n & 1) != 0)
                sum += zeros;
            else
                zeros++;

            n >>= 1;
        }

        // true - first player win, false - second player win
        return (sum & 1) != 0 ? true : false;
    }
}