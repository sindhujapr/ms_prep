package test;

/**
 * Created by qingcunz on 9/28/14.
 */
public class CountAndSay {
    public static void main(String[] args) {
        System.out.println(new CountAndSay().countAndSay(2));
    }

    public String countAndSay(int n) {
        String res = "1";
        for(int i = 1; i <= n; i++) {
            StringBuilder builder = new StringBuilder();
            for(int j = 0; j < res.length(); ) {
                int k = j+1;
                while(k < res.length() && res.charAt(k) == res.charAt(j))
                    k++;
                builder.append(k-j+'0').append(res.charAt(j));
                j = k;
            }

            res = builder.toString();
        }

        return res;
    }

    public String countAndSay1(int n) {
        String res = "1";

        while(n-- > 1) {
            StringBuilder temp = new StringBuilder();
            for(int i = 0; i < res.length(); ) {
                int j = i;
                while(j < res.length() && res.charAt(j) == res.charAt(i))
                    j++;

                int len = j-i;
                temp.insert(0, res.charAt(i));
                temp.insert(0, (char)(len + '0'));

                i = j;
            }

            res = temp.toString();
        }

        return res;
    }
}
