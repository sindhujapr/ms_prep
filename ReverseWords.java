package lc;

public class ReverseWords {
    public String reverseWords(String s) {
        if(s == null || s.length() == 0)
            return s;
    
        s = s.trim();
        int start = s.length()-1, end = s.length();
        StringBuilder builder = new StringBuilder();
        while(start >= 0) {
            while(start >= 0 && s.charAt(start) != ' ')
                start--;
            
            builder.append(s.substring(start+1, end)).append(' ');
            
            while(start >= 0 && s.charAt(start) == ' ')
                start--;
            
            end = start+1;
        }
        if(builder.length() > 0 && builder.charAt(builder.length()-1) == ' ')
            builder.deleteCharAt(builder.length()-1);
            
        return builder.toString();
    }

	// as of 10/29/2014, this doesn't pass large judge
    public String reverseWords(String s) {
        if(s == null || s.length() == 0)
            return s;
        
        s = s.trim();
        
        int start = 0;
        StringBuilder builder = new StringBuilder();
        int i = s.length()-1;
        while(i >= 0) {
            int len = 0;
            while(i >= 0) {
                builder.insert(start, s.charAt(i--));
                len++;
            }
            
            if(i < 0)
                break;
                
            start += len;
            
            builder.insert(start, ' ');
            while(i >= 0 && s.charAt(i) == ' ')
                i--;
			//must! move to the next insert position
            start++;
        }
        
        return builder.toString();
    }
}
