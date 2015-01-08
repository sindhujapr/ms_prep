package interview.google;

import java.math.BigInteger;

/*
 * use BigInteger to avoid overflow
 */
public class IntPalindrome {
    private boolean isPalindrome(BigInteger value) {
    String str = value.toString();
    char[] array = str.toCharArray();
    for (int i = 0; i < array.length/2; i++) {
        if(array[i] != array[array.length-i-1])
        return false;
    }
    
    return true;
    }
    
    private BigInteger palindrom(BigInteger value) {
    StringBuilder sb = new StringBuilder();
    String str = value.toString();
    int cnt = str.length()-1;
    while(cnt >= 0) {
        sb.append(str.charAt(cnt--));
    }
    return new BigInteger(sb.toString());
    }
    
    public BigInteger calculate(BigInteger value) {
    while(!isPalindrome(value)) {
        value = value.add(palindrom(value));
//      System.out.println("next: " + value.toString());
    }
    
    return value;
    }

    public static void main(String[] args) {
    IntPalindrome instance = new IntPalindrome();
    System.out.println(instance.calculate(new BigInteger("89")));
    System.out.println(instance.calculate(new BigInteger("134")));
    }
}
