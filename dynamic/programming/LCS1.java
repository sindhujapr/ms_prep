package dynamic.programming;

import java.util.Random;

/*
 * http://blog.csdn.net/v_july_v/article/details/6695482
 */
public class LCS1 {
    public static void main(String[] args) {
    int substringLength1 = 20;
    int substringLength2 = 20;

    String x = GetRandomStrings(substringLength1);
    String y = GetRandomStrings(substringLength2);

    /*
     * construct two-dimension array to record the length of subproblems
     * x[i] and y[i]
     */
    int[][] opt = new int[substringLength1 + 1][substringLength2 + 1];

    // DP
    for (int i = substringLength1 - 1; i >= 0; i--) {
        for (int j = substringLength2 - 1; j >= 0; j--) {
        if (x.charAt(i) == y.charAt(j))
            opt[i][j] = opt[i + 1][j + 1] + 1;
        else
            opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
        }
    }

    System.out.println("substring1:" + x);
    System.out.println("substring2:" + y);
    System.out.print("LCS:");

    int i = 0, j = 0;
    while (i < substringLength1 && j < substringLength2) {
        if (x.charAt(i) == y.charAt(j)) {
        System.out.print(x.charAt(i));
        i++;
        j++;
        } else if (opt[i + 1][j] >= opt[i][j + 1])
        i++;
        else
        j++;
    }
    }

    public static String GetRandomStrings(int length) {
    StringBuffer buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
    StringBuffer sb = new StringBuffer();
    Random r = new Random();
    int range = buffer.length();
    for (int i = 0; i < length; i++) {
        sb.append(buffer.charAt(r.nextInt(range)));
    }
    return sb.toString();
    }
}