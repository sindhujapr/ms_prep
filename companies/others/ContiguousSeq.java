package itv;

import java.util.*;

/**
 * Created by qingcunz on 11/26/14.
 * http://www.mitbbs.com/article_t/JobHunting/32838067.html
 */
public class ContiguousSeq {
    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);
        ContiguousSeq ins = new ContiguousSeq();
//        int[] num = new int[]{ -1, 4, 1, 0, -2, -3, 7};
//        ins.exist(num, 2);
//        ins.hasSum(num, 2);
//
//        int[] res = ins.f(20);
//        for(int val : res) {
//            System.out.print(val + " ");
//        }

//        System.out.println(ins.longestSubstring("aaabcbbcb"));
//        System.out.println(ins.lengthOfLongestSubstringTwoDistinct("abaabbacbbcb"));
        System.out.print(ins.gcd(18, 12));
    }

    public int gcd(int a, int b) {
        return a == 0 ? b : (b == 0 ? a : gcd(b, a%b));
    }

    public int longestSubstring(String s) {
        int res = 1;
        char ch = s.charAt(0);

        for(int i = 1, j = 0; i < s.length(); ) {
            while(i < s.length() && (s.charAt(i) == ch || s.charAt(i) == s.charAt(i-1)))
                i++;

            res = Math.max(res, i-j);

            j = i-1;
            while(j >= 0 && s.charAt(j) == s.charAt(i-1))
                j--;

            j++;
            ch = s.charAt(i-1);
        }
        return res;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int i = 0, j = -1, maxLen = 0;
        for (int k = 1; k < s.length(); k++) {
            if (s.charAt(k) == s.charAt(k - 1))
                continue;

            if (j >= 0 && s.charAt(j) != s.charAt(k)) {
                maxLen = Math.max(k - i, maxLen);
                i = j + 1;
            }
            j = k - 1;
        }
        return Math.max(s.length() - i, maxLen);
    }

    // read len chars to buf, which is supposed to be large enough
    public int read(char[] buf, int len) {
        int readBytes = 0;
        char[] chs = new char[4];
        while(readBytes < len) {
            int size = read4(chs);

            /*
             * if size == 4 and len-readBytes >= 4, we copy 4 chars
             * else we copy Math.min(size, len-readBytes) chars
             */
            int bytes = size == 4 && len-readBytes >= 4 ? 4 : Math.min(size, len-readBytes);
            System.arraycopy(chs, 0, buf, readBytes, bytes);
            readBytes += bytes;

            if(size < 4)
                break;
        }

        return readBytes;
    }

    private int read4(char[] chs) {
        return 4;
    }

    public int[] f(int n) {
        int[] res = new int[n];
        res[0] = 1;

        int i = 0, j = 0;
        int x = 2*res[0], y = 5*res[0];

        for(int k = 1; k < n; k++) {
            int val = Math.min(x, y);
            res[k] = val;

            if(x == val) {
                ++i;
                x = 2*res[i];
            }

            if(y == val) {
                ++j;
                y = 5*res[j];
            }
        }
        return res;
    }

    // assume target is not 0
    public boolean hasSum(int[] num, int target) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        list.add(-1);
        map.put(0, list);

        int sum = 0;
        for(int i = 0; i < num.length; i++) {
            sum += num[i];
            if(!map.containsKey(sum))
                map.put(sum, new ArrayList<Integer>());
            map.get(sum).add(i);
        }

        for(int val : map.keySet()) {
            if(map.containsKey(target-val)) {
//                int i = map.get(val), j = map.get(target-val);
//                for(int k = i+1; k <= j; k++)
//                    System.out.print(num[k] + " ");
            }
        }
        return false;
    }
    /*
     * this is inefficient, O(n^2)
     */
    public void exist(int[] num, int target) {
        int i = 0, j = 0;
        while(j < num.length) {
            while(j < num.length && target > 0) {
                target -= num[j];
                j++;
            }

            for(int k = i; target == 0 && k < j; k++)
                System.out.print(num[k] + "\t");

            while(i <= j && target < 0) {
                target += num[i];
                i++;
            }

            j++;
        }
    }
}
