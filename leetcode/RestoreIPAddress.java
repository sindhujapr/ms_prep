package lc;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    public static void main(String[] args) {
        RestoreIPAddress instance = new RestoreIPAddress();
        ArrayList<String> addresses = instance.restoreIpAddresses1("");
        System.out.print(addresses.size() + ":\t");
        for (String address : addresses)
            System.out.print(address + "\t");
    }
    
    public ArrayList<String> restoreIpAddresses1(String s) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> result = new ArrayList<String>();
        //keep the indices of the char in each part of the IP address
        List<Integer> indices = new ArrayList<Integer>();
        
        if(s.length() > 12)
            return result;

        int index = 0;
        do {
            while(index < s.length() && count(sb.toString(), '.') < 4) {
                int lastIndex = indices.size() > 0 ? (indices.get(indices.size()-1) + 1) : 0;
                sb.append(s.substring(lastIndex, index+1) + ".");
                indices.add(index++);
            }
            
            if(sb.length() > 0)
                sb.deleteCharAt(sb.length()-1);
            // re-implement validIPAddress for non-recursive algorithm
            if(index == s.length() && valid(sb))
                result.add(sb.toString());
            
            for(int i = sb.length()-1; i >= 0; i--) {
                if(sb.charAt(i) == '.')
                    break;
                sb.deleteCharAt(i);
            }
            
            if(indices.size() > 0)
                index = indices.remove(indices.size()-1) + 1;
            else
                index = 0;
        } while(index < s.length() || sb.toString().length() > 0);
        
        return result;
    }
    
    private int count(String str, char ch) {
        int cnt = 0;
        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == ch)
                cnt++;
        return cnt;
    }

    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<String>();

        // for large judge
        if(s.length() >= 4 && s.length() <= 12)
            restore(s, 0, new StringBuilder(), res);
        return res;
    }
    
    private void restore(String s, int start, StringBuilder builder, ArrayList<String> res) {
        if(start == s.length()) {
            if(valid(builder))
                res.add(builder.substring(0, builder.length()-1));
            return;
        }
        
        for(int i = start+1; i <= s.length(); i++) {
            if(i-start > 1 && s.charAt(start) == '0')
                break;
            
            if(Integer.valueOf(s.substring(start, i)) > 255)
                break;
            
            builder.append(s.substring(start, i)).append('.');
            restore(s, i, builder, res);
            builder.delete(builder.length()-(i-start+1), builder.length());
        }
    }
    
    private boolean valid(StringBuilder builder) {
        int cnt = 0;
        for(char ch : builder.toString().toCharArray())
            cnt += ch == '.' ? 1 : 0;
        return cnt == 4;
    }
}