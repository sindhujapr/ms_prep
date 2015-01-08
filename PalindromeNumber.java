package lc2;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(x < 0)
            return false;
        int size = 0;
        int z = x;
        while(z > 0) {
            z /= 10;
            size++;
        }
        
        int index = 0;
        while(index < size/2) {
            int i = size-index-1;
            int v1 = x / power(index) % 10;
            int v2 = x / power(i) % 10;
            if(v1 != v2)
                return false;
            index++;
        }
        return true;
    }
    
    private int power(int x) {
        int res = 1;
        while(x-- > 0)
            res *= 10;
        return res;
    }
}