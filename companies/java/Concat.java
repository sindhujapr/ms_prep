package interview.java;

import java.math.BigDecimal;

/*Sun introduced the StringBuilder class in J2SE 5.0, which is almost the same as StringBuffer, except it's not thread-safe.
 * Thread safety is usually not necessary with StringBuffer, since it is seldom shared between threads. 
 * When Strings are added using the + operator, the compiler in J2SE 5.0 and Java SE 6 will automatically use StringBuilder.
 * If StringBuffer is hard-coded, this optimization will not occur.
 */
public class Concat {
    public static String concat1(String s1, String s2, String s3, String s4, String s5, String s6) {
        String result = "";
        result += s1;
        result += s2;
        result += s3;
        result += s4;
        result += s5;
        result += s6;
        return result;
    }

    public static String concat2(String s1, String s2, String s3, String s4, String s5, String s6) {
        StringBuffer result = new StringBuffer();
        result.append(s1);
        result.append(s2);
        result.append(s3);
        result.append(s4);
        result.append(s5);
        result.append(s6);
        return result.toString();
    }

    public static String concat3(String s1, String s2, String s3, String s4, String s5, String s6) {
        return new StringBuffer(s1.length() + s2.length() + s3.length()
                + s4.length() + s5.length() + s6.length()).append(s1)
                .append(s2).append(s3).append(s4).append(s5).append(s6)
                .toString();
    }

    /*
     * Compiler will optimize so that StringBuilder is used for better performance.
     */
    public static String concat4(String s1, String s2, String s3, String s4, String s5, String s6) {
        return s1 + s2 + s3 + s4 + s5 + s6;
    }
    
    /*
     * Prevent future optimization by compiler. This is not recommended actually.
     */
    public static String concat5(String s1, String s2, String s3, String s4, String s5, String s6) {
        return new StringBuilder(
          s1.length() + s2.length() + s3.length() + s4.length() +
              s5.length() + s6.length()).append(s1).append(s2).
            append(s3).append(s4).append(s5).append(s6).toString();
      }
    
    public static void main(String[] args) {
        System.out.printf("4%d%n", 5/2);
        
        BigDecimal bd = new BigDecimal(.1);
        System.out.println(bd);     
    }
}
