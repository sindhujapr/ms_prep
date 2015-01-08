/* http://blog.csdn.net/sigh1988/article/details/9841493
 * 输入一个n，代表二进制数有几位，然后要求打印所有n位的二进制数，但打印有个规则，
 * 就是相邻两个数之间只能有一位不一样。比如n为2时，一个打印方法可以是：00 01 11 10。
 * 而00 01 10 11就不可以一位01和10有两位不一样。
 */
public class PrintNBits {
    public static List<String> printBits(int n) {
        Set<String> used = new HashSet<String>();

        List<String> res = new ArrayList<String>();

        StringBuilder bits = new StringBuilder();
        for(int i = 0; i < n; i++)
            bits.append('0');

        String str = bits.toString();
        do {
            if(res.size() > 1 && distance(str, res.get(res.size()-1)))
                System.out.println(str + "\t" + res.get(res.size()-1));

            res.add(str);
            used.add(str);

            str = nextBits(bits, used);

        } while(str != null);

        return res;
    }

    private static String nextBits(StringBuilder bits, Set<String> used) {
        for(int i = 0; i < bits.length(); i++) {
            bits.setCharAt(i, bits.charAt(i) == '0' ? '1' : '0');
            if(!used.contains(bits.toString())) {
                String res = bits.toString();
                return res;
            }

            bits.setCharAt(i, bits.charAt(i) == '0' ? '1' : '0');
        }
        return null;
    }

    private static boolean distance(String str1, String str2) {
        int cnt = 0;
        for(int i = 0; i < str1.length(); i++)
            cnt += str1.charAt(i) != str2.charAt(i) ? 1 : 0;
        return cnt > 1;
    }
}
