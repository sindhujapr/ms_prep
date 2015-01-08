package general;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import miscellaneous.util.concurrent.ThreadLocalRandom;

public class BigNumberAdd {
    /*
     * incomplete!!!
     */
    private static void add(char[] a, char[] b, int[] result) {
    /*
     * assume that each character is a digit
     */
    int l1 = a.length;
    int l2 = b.length;
    int length = l1 > l2 ? l1 + 1 : l2 + 1;
    Arrays.fill(result, -1);

    int carrier = 0;
    for (int i = l1 - 1, j = l2 - 1, k = length - 1; i >= 0 || j >= 0; i--, j--, k--) {
        if(a[i] == '.')
        continue;

        int bit1, bit2;
        if (i >= 0)
        bit1 = a[i] - '0';
        else
        bit1 = 0; // actually default value is 0.

        if (j >= 0)
        bit2 = b[j] - '0';
        else
        bit2 = 0;

        int sum = bit1 + bit2 + carrier;
        carrier = sum / 10;
        result[k] = sum % 10;
    }

    System.out.println(Arrays.toString(result));

    if (verify(a, b, result))
        System.out.println("correct");
    else
        System.out.println("incorrect");
    }

    public static int[] add(char[] a, char[] b) {
    int dotPos1 = indexOf(a, '.');
    int dotPos2 = indexOf(b, '.');

    assert dotPos1 != -1;
    assert dotPos2 != -1;

    /*
     * length of the parts before dot
     */
    int ll1 = dotPos1-1;
    int ll2 = dotPos2-1;
    int lLength = (ll1 > ll2 ? ll1 : ll2) + 1;
    
    /*
     * length of the parts after dot
     */
    int rl1 = a.length - dotPos1 - 1;
    int rl2 = b.length - dotPos2 - 1;
    int rLength = rl1 > rl2 ? rl1 : rl2;
    
    // one additional char for sign
    int[] result = new int[lLength+rLength+1];
    if(a[0] == b[0]) {
        result[0] = a[0];
        add(a, b, result);
    } else {
        if(compare(a, b) > 0){
        result[0] = a[0];
        substract(a, b, result);
        } else if (compare(a, b) < 0) {
        result[0] = b[0];
        substract(b, a, result);
        } else {
        result[0] = '0';
        }
    }
    
    return result;
    }
    
    private static int indexOf(char[] a, char ch) {
    for (int i = 0; i < a.length; i++) {
        if (a[i] == '.')
        return i;
    }
    return -1;
    }

    public static void substract(char[] a, char[] b, int[] result) {

    }

    /*
     * compare the digit part of the number, thus we will ignore the sign
     */
    public static int compare(char[] a, char[] b) {
    int dotPos1 = indexOf(a, '.');
    int dotPos2 = indexOf(b, '.');

    assert dotPos1 != -1;
    assert dotPos2 != -1;

    /*
     * Dot separates the number into two parts. compare the two left parts
     * first
     */
    if (dotPos1 > dotPos2)
        return 1;
    else if (dotPos1 < dotPos2)
        return -1;

    /*
     * here the two numbers have the same number of digits in the left part.
     */
    for (int i = dotPos1; i > 0; i--) {
        if (a[i] > b[i])
        return 1;
        else if (a[i] < b[i])
        return -1;
        // if a[i] == b[i], continue
    }

    /*
     * here the left parts are equal and we need to compare the right part
     */
    for (int i = dotPos1 + 1, j = dotPos2 + 1; i < a.length || j < b.length; i++, j++) {
        if (i < a.length && j == b.length)
        return 1;
        else if (i == a.length && j < b.length)
        return -1;
    }

    return 0;
    }

    /*
     * verify the result by using BigDecimal
     */
    private static boolean verify(char[] a, char[] b, int[] result) {
    StringBuilder sb1 = new StringBuilder();
    for (char c : a)
        sb1.append(c - '0');
    BigInteger v1 = new BigInteger(sb1.toString());

    StringBuilder sb2 = new StringBuilder();
    for (char c : b)
        sb2.append(c - '0');
    BigInteger v2 = new BigInteger(sb2.toString());

    BigInteger v = v1.add(v2);
    String value = v.toString();
    for (int i = 0, j = 0; i < result.length; i++) {
        /*
         * the result array may contain redundant leading characters
         */
        if (result[i] == -1)
        continue;

        if (result[i] != value.charAt(j) - '0') {
        return false;
        }
        j++;
    }
    return true;
    }

    private static ThreadLocalRandom rand = ThreadLocalRandom.current();

    public static char[] generateNum(int lengthLimit) {
    /*
     * the array must at least hold four characters, +[0-9]+\.[0-9]+
     */
    int length = rand.nextInt(4, lengthLimit);
    char[] result = new char[length];

    result[0] = rand.nextBoolean() ? '+' : '-';

    /* there must be at least one digit after the dot */
    int dotPos = rand.nextInt(2, length - 1);
    result[dotPos] = '.';
    /*
     * start from 1 since the first character is the sign (+ or -)
     */
    for (int i = 1; i < length; i++) {
        if (i == dotPos)
        continue;

        result[i] = (char) rand.nextInt('0', '9' + 1);
    }

    print(result);
    return result;
    }

    public static void print(char[] a) {
    for (int i = 0; i < a.length; i++) {
        System.out.print(a[i]);
    }
    System.out.println();
    }

    public static BigDecimal generateBigDecimal(int lengthLimit) {
    int length = rand.nextInt(4, 100);
    StringBuilder sb = new StringBuilder(length);
    sb.append(rand.nextBoolean() ? '+' : '-');
    
    for (int i = 1; i < length; i++)
        sb.append((char) rand.nextInt('0', '9' + 1));
    
    int dotPos = rand.nextInt(2, length-1);
    sb.setCharAt(dotPos, '.');
    System.out.println(sb.toString());
    return new BigDecimal(sb.toString());
    }

    public static void main(String[] args) {
//  char[] a = generateNum(100);
//  char[] b = generateNum(100);

    // add(a, b);

    BigDecimal v1 = generateBigDecimal(100);
    BigDecimal v2 = generateBigDecimal(100);
    System.out.println("add\t" + v1.add(v2));
    System.out.println("sub\t" + v1.subtract(v2));
    System.out.println("multi\t" + v1.multiply(v2));
    }
}