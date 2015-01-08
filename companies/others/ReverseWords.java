package test;

/**
 * Created by qingcunz on 9/23/14.
 */
public class ReverseWords {
    public static void main(String[] args) {
        System.out.println(new ReverseWords().reverseWords("   one.   +two three?   ~four   !five- "));
    }

    public String reverseWords(String s) {
        if(s == null || s.length() == 0)
            return s;

        s = s.trim();

        int start = 0;
        StringBuilder builder = new StringBuilder();
        int i = s.length()-1;
        while(i >= 0) {
            int len = 0;
            while(i >= 0 && s.charAt(i) != ' ') {
                builder.insert(start, s.charAt(i--));
                len++;
            }

            if(i < 0)
                break;

            start += len;

            builder.insert(start, ' ');
            while(i >= 0 && s.charAt(i) == ' ')
                i--;
            start++;
        }

        return builder.toString();
    }
}
