package lc2;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
	public static void main(String[] args) {
		ArrayList<ArrayList<String>> result = new PalindromePartitioning().partition("ab");
		for(ArrayList<String> one : result)
			System.out.println(one);
	}
	
    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> one = new ArrayList<String>();
        List<Integer> indices = new ArrayList<Integer>();
        
        int index = 1, len = 0;
        do {
            int i = indices.size() > 0 ? indices.get(indices.size()-1) : 0;
            while(index <= s.length()) {
                if(isPalin(s, i, index-1)) {
                    indices.add(index);
                    one.add(s.substring(i, index));
                    len += index-i;
                    i = index;
                }
                index++;
            }
            
            if(len == s.length())
                result.add(new ArrayList<String>(one));
            
            if(indices.size() > 0) {
            	index = indices.remove(indices.size()-1) + 1;
            	len -= one.remove(one.size()-1).length();
            }
        } while(index <= s.length() || one.size() > 0);
        
        return result;
    }
    
    public ArrayList<ArrayList<String>> partition1(String s) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        partition(s, 0, new ArrayList<String>(), res);
        return res;
    }
    
    private void partition(String s, int start, ArrayList<String> list, ArrayList<ArrayList<String>> res) {
        if(start == s.length()) {
            res.add(new ArrayList<String>(list));
            return;
        }
        
        for(int i = start; i < s.length(); i++) {
            if(isPalin(s, start, i)) {
                list.add(s.substring(start, i+1));
                partition(s, i+1, list, res);
                list.remove(list.size()-1);
            }
        }
    }
    
	/*
	 * One improvement is to caculate an ispalin[][] array in advance to save redundant work
	 */
    private boolean isPalin(String s, int start, int end) {
        while(start <= end)
            if(s.charAt(start++) != s.charAt(end--))
                return false;
        return true;
    }
}
