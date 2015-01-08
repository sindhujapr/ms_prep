
// http://www.glassdoor.com/Interview/-a-first-write-a-function-to-calculate-the-hamming-distance-between-two-binary-numbers-b-write-a-function-that-takes-QTN_450885.htm
public class HammingCode {
    public int hammingDistance(String a, String b) {
        int res = 0;
        for(int i = 0; i < Math.max(a.length(), b.length()); i++) {
            char ch1 = i >= a.length() ? 0 : a.charAt(a.length()-i-1);
            char ch2 = i >= b.length() ? 0 : b.charAt(b.length()-i-1);
            res += ch1 == ch2 ? 0 : 1;
        }
        return res;
    }

    /*
     * if all strings are integers, we only need to sort out for each
     * bit, how many 0 we have and how many 1 we have. Suppose they
     * are m and n. Then res += m * n; Do this for each bit.
     */
    public int distance(List<String> list) {
        int res = 0;
        return res;
    }
}
