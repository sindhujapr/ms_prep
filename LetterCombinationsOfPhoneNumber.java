package lc;

import java.util.ArrayList;

public class LetterCombinationsOfPhoneNumber {
    public static void main(String[] args) {
        ArrayList<String> rst = new LetterCombinationsOfPhoneNumber().letterCombinations("2");
        System.out.println(rst);
    }
    
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        combine(digits, 0, new StringBuilder(), res);
        return res;
    }
    
    private void combine(String digits, int start, StringBuilder sb, ArrayList<String> res) {
        if(start == digits.length()) {
            res.add(sb.toString());
            return;
        }
        
        int val = digits.charAt(start) - '0';
        for(int i = lower(val); i <= upper(val); i++) {
            sb.append((char) i);
            combine(digits, start+1, sb, res);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    
    private int lower(int i) {
        if(i <= 7)
            return 'a' + (i-2) * 3;
        else if(i == 8)
            return 't';
        else if(i == 9)
            return 'w';
        else
            return 'z'+1;
    }
    
    private int upper(int i) {
        return lower(i+1) - 1;
    }
}