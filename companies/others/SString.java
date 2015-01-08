package review;

/**
 * Created by qingcunz on 10/11/14.
 */
public class SString {
    public static void main(String[] args) {
        System.out.println(new SString().calc("bcd"));
    }

    public int num(String S) {
        long count = 0;
        for(int i = 0; i < S.length(); i++) {

        }
        return 0;
    }

    public int calc(String str) {
        int MOD = 1009;
        int n = str.length();
        char[] s = str.toCharArray();

        int[] factor = new int[n];
        factor[0] = 1;
        for (int i = 1; i < factor.length; i++)
            factor[i] = (factor[i - 1] * 25) % MOD;

        int result = 0;
        for (int i = 0; i < s.length; i++) {
            result += (s[i]-'a') * factor[s.length-i-1];

            if(i > 0 && s[i-1] < s[i])
                result -= factor[s.length-i-1];
        }

        // plus the one that lexicographically equals to the input string
        return (result + 1) % MOD;
    }

    /**
     * Created by qingcunz on 10/9/14.
     */
    public static class FindLeastDiff {
        public static void main(String[] args) {
            int[] A = new int[]{1, 3, 7, 9, 12};
            for(int i = 0; i < 16; i++)
                System.out.println(i + "\t" + FindLeastDiff.findLeastDiff(A, i));
        }

        public static int findLeastDiff(int[] A, int target) {
            int start = 0, end = A.length-1;
            int index = -1;
            while(start <= end) {
                int mid = (start+end) >> 1;
                if(A[mid] == target) {
                    return A[mid];
                } if(A[mid] > target) {
                    if(mid == start) {
                        return A[mid];
                    } else if(A[mid-1] < target) {
                        return Math.abs(A[mid-1]-target) > Math.abs(A[mid]-target) ?
                                A[mid] : A[mid-1];
                    }
                    end = mid - 1;
                } else if(A[mid] < target) {
                    if(mid == end) {
                        return A[mid];
                    } else if(A[mid+1] > target) {
                        return Math.abs(A[mid+1]-target) > Math.abs(A[mid]-target) ?
                                A[mid] : A[mid+1];
                    }
                    start = mid + 1;
                }
            }

            return -1;
        }
    }
}
