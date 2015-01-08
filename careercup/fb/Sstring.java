package careercup.fb;

/*
 * http://www.careercup.com/question?id=23869663
 */
public class Sstring {
    public static void main(String[] args) {
        System.out.println(calcSstring("bcd"));
    }

    static int calcSstring(String str) {
        int MOD = 1009;
        int n = str.length();
        char[] s = str.toCharArray();
        
        int[] factor = new int[n];
        factor[0] = 1;
        for (int i = 1; i < factor.length; i++) {
            factor[i] = (factor[i - 1] * 25) % MOD;
        }

        int result = 0;
        for (int i = 0; i < s.length; i++) {
            result += (s[i]-'a') * factor[s.length-i-1];
            
            if(i > 0 && s[i-1] < s[i])
                result -= factor[s.length-i-1];
        }

        // plus the one that lexicographically equals to the input string
        return (result + 1) % MOD;
    }
}