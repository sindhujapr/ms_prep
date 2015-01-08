/*
 * A valid number may not contain 2,3,4,5,7. Flip those numbers 180 degrees and
 * it's not a valid number. Single digit numbers is a special case. It 
 * contains 0, 1, 8. For 2N digit numbers, the first digit can be 1,6,8,9 and 
 * the next N-1 digits can be 0,1,6,8,9. For 2N+1 digit numbers, you can insert
 * 0,1,8 in the middle of any valid 2N digit numbers and it's still a valid 
 * number. 
 * 
 * 1        digit: 3
 * 2N     digit: 4 * 5 ^ (N-1)
 * 2N+1 digit: 3 * 4 * 5 ^ (N-1)
 * 
 * For example, there are 20 valid 4 digit numbers:
 * 
 * 10 => 1001, 11 => 1111, 16 => 1691, 18 => 1881, 19 => 1961
 * 60 => 6009, 61 => 6119, 66 => 6699, 68 => 6889, 69 => 6969
 * 80 => 8008, 81 => 8118, 86 => 8698, 88 => 8888, 89 => 8968
 * 90 => 9006, 91 => 9116, 96 => 9696, 98 => 9886, 99 => 9966
 * 
 * there are 60 valid 5 digit numbers
 * 
 * 1001 => 10001, 10101, 10801 etc
 *
 */
public class MirrorNumbers {
    public static void main(String[] args) {
        MirrorNumbers instance = new MirrorNumbers();
        List<String> res = instance.mirrorNum(5);
        for(String s : res)
            System.out.println(s);

        System.out.println(res.size());
    }

    // my own code

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

    public static int getNumMirrors(String limit) {
        byte[] n = limit.getBytes();
        int digits = n.length;
        for (int i = 0; i < digits; i++)
            n[i] -= '0';

        if (digits == 1)
            return getNumMirrorsSingleDigit(n[0]);
        else
            return getNumMirrorsLessThanKDigits(digits) + getNumKDigitMirrorsLessThanN(n, digits);
    }

    private static int getNumMirrorsSingleDigit(int n) {
        if (n >=8) return 3;
        if (n >=1) return 2;
        return 1;
    }

    private static int getNumMirrorsLessThanKDigits(int digits) {
        int sum = 3; // 0, 1, 8
        int base = 4; // 1, 8, 6, 9
        for (int i = 1; i < digits/2; i++) {
            sum += 4 * base;
            base *= 5;
        }

        if (digits % 2 == 1)
            sum += base;

        return sum;
    }

    private static int getNumKDigitMirrorsLessThanN(byte[] n, int digits) {
        int sum;
        int base = 1;
        boolean isNMirror;
        for (int i = 1; i < digits/2; i++)
            base *= 5;
        if (digits % 2 == 1)
            base *= 3;
        switch (n[0]) { // 1, 6, 8, 9
            // n=987654321, add all mirrors between 0 and 900000000
            case 9:  sum = 3 * base; isNMirror = n[digits-1] == 6; break;
            case 8:  sum = 2 * base; isNMirror = n[digits-1] == 8;break;
            case 7:  return 2 * base;
            case 6:  sum = base; isNMirror = n[digits-1] == 9;break;
            case 1:  sum = 0; isNMirror = n[digits-1] == 1;break;
            default: return base;
        }

        for (int i = 1; i < digits/2; i++) {
            base /= 5;
            switch (n[i]) { // 0, 1, 6, 8, 9
                // n=987654321, i=1, add all mirrors between 900000000 and 980000000
                case 9:  sum += 4 * base; isNMirror &= n[digits-i-1] == 6; break;
                case 8:  sum += 3 * base; isNMirror &= n[digits-i-1] == 8; break;
                case 7:  return 3 * base;
                case 6:  sum += 2 * base; isNMirror &= n[digits-i-1] == 9; break;
                case 1:  sum += base; isNMirror &= n[digits-i-1] == 1; break;
                case 0:  isNMirror &= n[digits-i-1] == 0; break;
                default: return sum + 2 * base;
            }
        }

        if (digits % 2 == 1) {
            switch (n[digits/2]) { // 0, 1, 8
                case 9:  return sum + 3;
                case 8:  sum += 2; break;
                case 1:  sum += 1; break;
                case 0:  break;
                default: return sum + 2;
            }
        }
    }
}
