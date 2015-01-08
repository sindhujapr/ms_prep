package google;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingcunz on 12/28/14.
 */
public class MirrorNumbers {
    public static void main(String[] args) {
        MirrorNumbers instance = new MirrorNumbers();
        List<String> res = instance.mirrorNum(5);
        for(String s : res)
            System.out.println(s);

        System.out.println(res.size());
    }

    private static char[] first = new char[] {'1', '6', '8', '9'};
    private static char[] second = new char[] {'0', '1', '6', '8', '9'};
    private static char[] middle = new char[] {'0', '1', '8'};

    public List<String> mirrorNum(int n) {
        List<String> res = new ArrayList<String>();
        mirrorNum(0, n, res, "");
        return res;
    }

    private void mirrorNum(int start, int n, List<String> res, String s) {
        if(s.length() == n/2) {
            // construct the right part
            StringBuilder sb = new StringBuilder();
            for(int i = s.length()-1; i >= 0; i--) {
                char ch = s.charAt(i);
                sb.append(ch == '6' ? '9' : (ch == '9' ? '6' : ch));
            }

            if((n & 1) == 0) {
                res.add(s + sb.toString());
            } else {
                // insert char in array "middle" in the middle
                for (char ch : middle) {
                    String temp = s + ch + sb.toString();
                    res.add(temp);
                }
            }

            return;
        }

        char[] arr = start == 0 ? first : second;

        for(int i = 0; i < arr.length; i++)
            mirrorNum(start+1, n, res, s+arr[i]);
    }
}
