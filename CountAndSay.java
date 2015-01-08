package lc;

public class CountAndSay {
    public String countAndSay(int n) {
        String res = "1";
        for(int i = 1; i < n; i++) {
            StringBuilder builder = new StringBuilder();
            for(int j = 0; j < res.length(); ) {
                int k = j+1;
                while(k < res.length() && res.charAt(k) == res.charAt(j))
                    k++;
                builder.append((char) (k-j+'0')).append(res.charAt(j));
                j = k;
            }
            
            res = builder.toString();
        }
        
        return res;
    }

    public String countAndSay(int n) {
        if(n == 1)
            return "1";

        String str = countAndSay(n-1);

        StringBuilder builder = new StringBuilder();        
        for(int i = 0; i < str.length(); ) {
            int cnt = 1;
            char ch = str.charAt(i);
            i++;
            while(i < str.length() && str.charAt(i) == ch) {
                i++;
                cnt++;
            }
            builder.append(cnt);
            builder.append(ch);
        }
        
        return builder.toString();
    }
    
    public static void main(String[] args) {
		System.out.println(new CountAndSay().countAndSay(2));
	}

    public String countAndSay1(int n) {
        String val = "1";
        // the first time "1" is read as "one" not "one one"
        while(n-- > 1) {
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < val.length(); ) {
                char ch = val.charAt(i++);
                int cnt = 1;
                while(i < val.length() && val.charAt(i) == ch) {
                    i++;
                    cnt++;
                }
                
                builder.append((char)(cnt+'0'));
                builder.append(ch);
            }
            
            val = builder.toString();
        }
        
        return val;
    }
}
