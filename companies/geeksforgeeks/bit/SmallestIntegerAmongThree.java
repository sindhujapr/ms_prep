package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/smallest-of-three-integers-without-comparison-operators/
 */
public class SmallestIntegerAmongThree {
    public static void main(String[] args) {
        System.out.println(smallest1(-10, 3, 10));
        System.out.println(smallest2(-10, 3, 10));
        System.out.println(smallest3(-10, 3, -20));

        System.out.println(smallest4(-10, 3, -20));
    }

    // this uses comparison operation == and ?:
    public static int smallest1(int x, int y, int z) {
        if (((x - y) & (1 << 31)) == Integer.MIN_VALUE) {
            return ((x - z) & (1 << 31)) == Integer.MIN_VALUE ? x : z;
        } else {
            return ((y - z) & (1 << 31)) == Integer.MIN_VALUE ? y : z;
        }
    }

    // only supports positive integers!
    public static int smallest2(int x, int y, int z) {
        int result = 0;
        while (x != 0 && y != 0 & z != 0) {
            x--;
            y--;
            z--;
            result++;
        }

        return result;
    }

    public static int smallest3(int x, int y, int z) {
        return min(x, min(y, z));
    }

    private static int min(int x, int y) {
        return y + ((x - y) & ((x - y) >> 31));
    }

    public static String convertIntToBits(int num) {
        StringBuilder builder = new StringBuilder();
        while (num != 0) {
            if ((num & 0x1) == 1)
                builder.insert(0, '1');
            else
                builder.insert(0, '0');
            num >>>= 1;
        }
        return builder.toString();
    }

    // only support positive integers
    public static int smallest4(int x, int y, int z) {
        if (y / x == 0) // Same as "if (y < x)"
            return (y / z == 0) ? y : z;
        return (x / z == 0) ? x : z;
    }
}