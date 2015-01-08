import java.util.HashSet;
import java.util.Set;

public class DecimalToString {
    public static void main(String[] args) {
        System.out.println(get_decimal(1, 6));
        System.out.println(get_decimal(1, 3));
        System.out.println(get_decimal(1, 2));
        System.out.println(get_decimal(1, 8));
        System.out.println(get_decimal(2, 3));
        System.out.println(get_decimal(1, 9));
        System.out.println(get_decimal(1, 11));
        System.out.println(get_decimal(1, 17));
        System.out.println(get_decimal(1, 19));
        System.out.println(get_decimal(4, 9));
        System.out.println(get_decimal(7, 12));
        System.out.println(get_decimal(1, 81));
        System.out.println(get_decimal(22, 7));
        System.out.println(get_decimal(10, 5));
        System.out.println(get_decimal(0, 5));
    }

    static String get_decimal(int num, int div) {
        String ret = Integer.toString(num / div);
        num %= div;
        if (num == 0)
            return ret + ".(0)";

        ret = ret + ".";
        Set<Integer> set = new HashSet<Integer>();
        String temp = "";
        while (num != 0 && !set.contains(num)) {
            set.add(num);
            num *= 10;
            temp = temp + (num / div);
            num = num % div;
        }

        if (num != 0) {
            ret = ret + "(" + temp + ")";
        } else {
            ret = ret + temp;
        }
        return ret;
    }
}
