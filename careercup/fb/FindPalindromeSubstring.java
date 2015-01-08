package careercup.fb;

/*
 * http://www.careercup.com/question?id=14260662
 */
public class FindPalindromeSubstring {
    public static void main(String[] args) {
        System.out.println(count("ab"));
        System.out.println(count("aba"));
        System.out.println(count("abba"));
    }
    
    public static int count(String str) {
        assert str != null;
        
        int sum = 0;
        int n = str.length();
        char[] s = str.toCharArray();
        for(int i = 0; i < n; i++) {
            sum++;
            // s[i] is the center
            int j = i-1, k = i+1;
            while(j >= 0 && k < n && s[j] == s[k]) {
                sum++;
                j--;
                k++;
            }
            
            j = i-1;
            k = i;
            while(j >= 0 && k < n && s[j] == s[k]) {
                sum++;
                j--;
                k++;
            }
        }
        
        return sum;
    }
}