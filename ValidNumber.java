package lc;

/*
 * We could also use regular expression or state machine:
 * http://n00tc0d3r.blogspot.com/2013/06/valid-number.html
 *
 */
public class ValidNumber {
	public static void main(String[] args) {
		System.out.println(new ValidNumber().isNumber("+."));
	}
	
    public boolean isNumber(String s) {
        String str = s.trim();
        
        if(str.length() == 0)
            return false;
        
        return isValidE(str) || isValidDot(str) || isValidInt(str, true, false);
    }
    
    private boolean isValidE(String str) {
        int eIndex = str.indexOf('e');
        if(eIndex < 0) {
        	eIndex = str.indexOf('E');
        	if(eIndex < 0)
        		return false;
        }
        
        String right = str.substring(eIndex+1);
        if(!isValidInt(right, true, false))
            return false;

        String left = str.substring(0, eIndex);
        return isValidInt(left, true, false) && isValidDot(left);
    }
    
    private boolean isValidDot(String str) {
    	if(str.length() > 0 && (str.charAt(0) == '+' || str.charAt(0) == '-'))
    		str = str.substring(1);

    	if(str.indexOf('+') >= 0 || str.indexOf('-') >= 0)
    		return false;
    
        int dotIndex = str.indexOf('.');
        if(dotIndex < 0)
            return false;
        if(str.length() == 1)
            return false;
        String left = str.substring(0, dotIndex);
        String right = str.substring(dotIndex+1);
            
        return isValidInt(left, true, true) && isValidInt(right, false, true);
    }

    private boolean isValidInt(String str, boolean sign, boolean allowEmpty) {
        if(sign && str.length() > 0 && (str.charAt(0) == '+' || str.charAt(0) == '-'))
            str = str.substring(1);

        if(!allowEmpty && str.length() == 0)
            return false;

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch < '0' || ch > '9') {
                return false;
            }
        }
        
        return true;
    }
}
