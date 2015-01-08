package javautils;

import java.math.BigInteger;
import java.util.Formatter;
import java.util.Locale;

/*
 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html
 * Other options:
 * Integer.toString(value, 2);
 * Integer.toBinaryString(i);
 * String.format("%1$03o", value);
 * int val = Integer.valueOf(stringValue, 10);
 */
public class FomatterTest {
    public static void main(String[] args) {
    System.out.println(Integer.toBinaryString(30));
    System.out.println(String.format("%1$05o\n", 20));
    
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb, Locale.US);
    // Explicit argument indices may be used to re-order output.
    formatter.format("%4$-10s %3$2s %2$2s %1$2s\n", "a", "b", "c", "d");
    formatter.format(Locale.FRANCE, "e = %+10.4f\n", Math.E);

    // The '(' numeric flag may be used to format negative numbers with
    // parentheses rather than a minus sign. Group separators are
    // automatically inserted.
    formatter.format("Amount gained : $%(,.2f\n", -6217.245);
    System.out.println(sb.toString());
    }
}