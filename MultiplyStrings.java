package lc;

public class MultiplyStrings {
	public static void main(String[] args) {
		MultiplyStrings instance = new MultiplyStrings();
		System.out.println(instance.multiply("98", "9"));
		System.out.println(123*456);
	}

	// my latest code
    public String multiply(String num1, String num2) {
        assert num1 != null && num2 != null;
        
        StringBuilder res = new StringBuilder();
        int m = num1.length(), n = num2.length();
        for(int bit = 0, carrier = 0; bit < m+n; bit++) {
            int sum = carrier;
            for(int i = 0; i <= bit; i++) {
                int b1 = m-i-1, b2 = n-(bit-i)-1;
				// must have!!!
                if(b1 < 0 || b2 < 0)
                    continue;
                    
                int v1 = num1.charAt(b1)-'0', v2 = num2.charAt(b2)-'0';
                
                sum += v1*v2;
            }
            
            carrier = sum / 10;
            res.insert(0, (char)(sum%10+'0'));
        }
        
        while(res.length() > 0 && res.charAt(0) == '0')
            res.deleteCharAt(0);
        
        return res.length() == 0 ? "0" : res.toString();
    }

	/*
	 * Adapted from:
	 * http://gongxuns.blogspot.com/2013/01/leetcode-multiply-strings.html
	 */
    public String multiply1(String num1, String num2) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        assert num1 != null && num2 != null && num1.length() > 0 && num2.length() > 0;

        boolean sign1 = num1.charAt(0) == '-' ? false : true;
        boolean sign2 = num2.charAt(0) == '-' ? false : true;
        boolean sign = (sign1 && sign2) || (!sign1 && !sign2);
        
        String v1 = num1.trim(), v2 = num2.trim();
        if(num1.charAt(0) == '+' || num1.charAt(0) == '-')
            v1 = num1.substring(1);
        if(num2.charAt(0) == '+' || num2.charAt(0) == '-')
            v2 = num2.substring(1);
        
        int[] res = new int[v1.length() + v2.length()];
        for(int i = 0; i < v1.length(); i++) {
            int n1 = v1.charAt(v1.length()-1-i)-'0';
            
            int carrier = 0;
            for(int j = 0; j < v2.length(); j++) {
                int n2 = v2.charAt(v2.length()-1-j)-'0';
                int pos = i+j;
                
                res[pos] += n1 * n2 + carrier;
                // be careful of the calculation sequence
                carrier = res[pos] / 10 ;
                res[pos] = res[pos] % 10;
            }

            /*
             * this is tricky:
             * when we do the addition, res[i+v2.length()] is actually zero.
             */
            res[i+v2.length()] += carrier;
        }

        int i = res.length-1;
        // keep at least one 0 to avoid "" result (0 * 0)
        while(i > 0 && res[i] == 0)
            i--;

        StringBuilder builder = new StringBuilder();        
        if(!sign)
            builder.append('-');
        while(i >= 0)
            builder.append((char)(res[i--]+'0'));

        // avoid -0 result
        if(builder.toString().equals("-0"))
            return "0";
        else
            return builder.toString();
    }
}
