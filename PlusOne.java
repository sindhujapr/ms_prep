package lc;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int carrier = 1;
        for(int i = digits.length-1; carrier > 0 && i >= 0; i--) {
            int sum = 0;
            if(i == digits.length-1) {
                sum = digits[i]+1;
            } else {
                sum = digits[i] + carrier;
            }
            
            digits[i] = sum % 10;
            carrier = sum / 10;
        }
        
        if(carrier > 0) {
            int[] temp = new int[digits.length+1];
            temp[0] = carrier;
            for(int i = 1; i < temp.length; i++)
                temp[i] = digits[i-1];
            return temp;
        } else {
            return digits;
        }
    }
}