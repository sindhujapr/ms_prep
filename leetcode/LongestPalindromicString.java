package lc;

public class LongestPalindromicString {
    public static void main(String[] args) {
        System.out.println(new LongestPalindromicString().longestPalindrome2("abbacd"));
    }
    
    // my latest code as of sep/2014
    public String longestPalindrome(String s) {
        String res = ""; 
        if(s == null || s.length() == 0)
            return res;
                                        
        for(int i = 0; i < s.length(); i++) {
            int j = i-1, k = i+1;
            while(j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                j--;
                k++;
            }   
            
            if(k-j-1 > res.length())
                res = s.substring(j+1, k); 
            
            j = i;
            k = i+1;
            while(j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                j--;
                k++;
            }   
        
            if(k-j-1 > res.length())
                res = s.substring(j+1, k); 
        }   
            
        return res;
    } 

    /*
     * http://gongxuns.blogspot.com/2012/12/solution1-on2-on2-public-class-solution
     * .html
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        int[] p = new int[2 * n - 1];

        int center = 0, right = 0, cur = 0;
        while (cur < 2 * n - 1) {
            while (2 * center - right >= 0 && right < 2 * n - 1
                    && (right % 2 == 1 || s.charAt((2 * center - right) / 2) == s.charAt(right / 2))) {
                right++;
                p[center]++;
            }
            right = Math.max(right - 1, center);
            p[center] = Math.max(p[center] - 1, 0);
            cur = center + 1;
            while (2 * center - cur >= 0 && cur + p[2 * center - cur] < right) {
                p[cur] = p[2 * center - cur];
                cur++;
            }
            if (cur < 2 * n - 1) {
                center = cur;
                if (right < center)
                    right = center;
                p[center] = right - cur;
            }
        }

        String res = "";
        for (int i = 0; i < 2 * n - 1; i++) {
            int start = 0, end = 0;
            if (i % 2 == 1) {
                start = i / 2 - (p[i] + 1) / 2 + 1;
                end = i / 2 + (1 + p[i]) / 2;
            } else {
                start = i / 2 - p[i] / 2;
                end = i / 2 + p[i] / 2;
            }
            if (end - start + 1 > res.length())
                res = s.substring(start, end + 1);
        }
        return res;
    }

    public String longestPalindrome2(String s) {
        int n = s.length();
        String res = "";
        for (int i = 0; i < 2 * n - 1; i++) {
            int left = i % 2 == 0 ? i / 2 -1 : i / 2;
            int right = i / 2 + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (right - left - 1 > res.length()) {
                res = s.substring(left + 1, right);
            }
        }

        return res;
    }
    
    /*
     * same as longestPalindrome2 but with more code.
     */
    public String longestPalindrome5(String s) {
        if (s == null)
            return null;

        String res = "";
        for (int i = 0; i < s.length(); i++) {
            int left = i;
            int right = i;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            
            if(right-left > res.length())
                res = s.substring(left+1, right);
            
            if(i > s.length()-2 || s.charAt(i) != s.charAt(i+1))
                continue;
            left = i;
            right = i+1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            
            if(right-left > res.length())
                res = s.substring(left+1, right);
        }
        return res;
    }
}
