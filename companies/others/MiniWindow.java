package test;

public class MiniWindow {
    public static void main(String[] args) {
        String res = new MiniWindow().minWindow("a", "b");
        System.out.println(res);
    }

    public String minWindow(String S, String T) {
        if(S == null || T == null)
            return "";

        int n = T.length(), count = 0, start = 0;
        String minStr = "";
        int[] toBeFound = new int[1<<8], hasFound = new int[1<<8];
        for(int i = 0; i < n; i++)
            toBeFound[T.charAt(i)]++;

        for(int i = 0; i < S.length(); i++) {
            char ch = T.charAt(i);
            if(toBeFound[ch] == 0)
                continue;

            if(hasFound[ch] < toBeFound[ch])
                count++;
            hasFound[ch]++;

            if(count == n) {
                char s = S.charAt(start);
                while(toBeFound[s] == 0 || hasFound[s] > toBeFound[s]) {
                    if(hasFound[s] > toBeFound[s])
                        hasFound[s]--;
                    /*
                     * if we decrease count here, there will be failed test case:
                     * Input:	"ADOBECODEBANC", "ABC"
                     * Output:	"ADOBEC"
                     * Expected:	"BANC"
                     */
                    s = S.charAt(++start);
                }

                if(minStr.length() == 0 || (i-start+1) < minStr.length())
                    minStr = S.substring(start, i+1);
            }
        }

        return minStr;
    }
}
