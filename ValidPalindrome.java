package lc2;

public class ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(new ValidPalindrome().isPalindrome("1a2"));
    }
    
    public boolean isPalindrome(String s) {
        int low = 0;
        int high = s.length()-1;
        while(low < high) {
            while(low < high && !validChar(s.charAt(low)))
                low++;
            while(low < high && !validChar(s.charAt(high)))
                high--;

            if(high <= low)
                return true;
                
            char ch1 = Character.toLowerCase(s.charAt(low));
            char ch2 = Character.toLowerCase(s.charAt(high));
            
            if(ch1 != ch2)
                return false;
            low++;
            high--;
        }
        return true;
    }
    
    private boolean validChar(char ch) {
        if(ch >= 'a' && ch <= 'z')
            return true;
        if(ch >= 'A' && ch <= 'Z')
            return true;
        if(ch >= '0' && ch <= '9')
            return true;
        return false;
    }
}