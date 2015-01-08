package lc;

public class RomanToInteger {
    // http://se7so.blogspot.com/2014/02/how-to-prepare-for-interview-9.html
    public int romanToInt(String num) {
        Map<Character, Integer> mp = new HashMap<Character, Integer>();
        mp.put('M', 1000);
        mp.put('D', 500);
        mp.put('C', 100);
        mp.put('L', 50);
        mp.put('X', 10);
        mp.put('V', 5);
        mp.put('I', 1);
        
        char prev = 'Z';
        int res = 0;
        for(int i = 0; i < num.length(); i++) {
            res += mp.get(num.charAt(i));
            
            if(prev != 'Z' && mp.get(prev) < mp.get(num.charAt(i)))
                res = res - 2 * mp.get(prev);
     
            prev = num.charAt(i);
        }
        
        return res;
    }

    public int romanToInt(String s) {
        int result = 0;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == 'M') {
                result += 1000;
            } else if(ch == 'C') {
                if(i+1 < s.length() && s.charAt(i+1) == 'M') {
                    result += 900;
                    i++;
                } else if(i+1 < s.length() && s.charAt(i+1) == 'D') {
                    result += 400;
                    i++;
                } else {
                    result += 100;
                }
            } else if(ch == 'D') {
                result += 500;
            } else if(ch == 'X') {
                if(i+1 < s.length() && s.charAt(i+1) == 'C') {
                    result += 90;
                    i++;
                } else if(i+1 < s.length() && s.charAt(i+1) == 'L') {
                    result += 40;
                    i++;
                } else {
                    result += 10;
                }
            } else if(ch == 'L') {
                result += 50;
            } else if(ch == 'I') {
                if(i+1 < s.length() && s.charAt(i+1) == 'X') {
                    result += 9;
                    i++;
                } else if(i+1 < s.length() && s.charAt(i+1) == 'V') {
                    result += 4;
                    i++;
                } else {
                    result++;
                }
            } else if(ch == 'V') {
                result += 5;
            }
        }
        return result;
    }
}
