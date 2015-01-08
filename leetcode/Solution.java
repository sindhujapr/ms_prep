package leetcode;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().isNumber("-1."));
    }

    public boolean isNumber(String s) {
        assert s != null;
        String str = s.trim();
        
        if(str.length() == 0)
            return false;

        if(str.charAt(0) == '+' || str.charAt(0) == '-')
            str = str.substring(0);

        int pos = str.indexOf('.');
        
        if(pos == -1)
            return validInteger(str, false);
        else
            return str.length() > 1 && validInteger(str.substring(0, pos), true) && validInteger(str.substring(pos+1), true);
    }
    
    private boolean validInteger(String s, boolean allowEmpty) {
        if(!allowEmpty && s.length() == 0)
            return false;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == 'e') {
                if(i == 0 || i == s.length()-1)
                    return false;
                continue;
            }

            if(ch < '0' || ch > '9')
                return false;
        }
        
        return true;
    }
}