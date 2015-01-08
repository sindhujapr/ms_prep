package interview.daiziguizhong;

/**
 * http://chuansongme.com/n/673396
 */
public class ParenthsisMatch {
    public static void main(String[] args) {
        System.out.println(match("()", 0));
        System.out.println(match(")(", 0));
        System.out.println(match("(abcd(e)", 0));
        System.out.println(match("(a)(b)", 0));
    }

    public static boolean match(String s, int left) {
        if(s == null || s.length() == 0) {
            return left == 0;
        }

        char ch = s.charAt(0);
        if(ch == '(') {
            return match(s.substring(1), left+1);
        } else if(ch == ')') {
            if(left == 0)
                return false;

            return match(s.substring(1), left-1);
        } else {
            return match(s.substring(1), left);
        }
    }
}
