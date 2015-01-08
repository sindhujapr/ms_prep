package lc;

public class LongestSubstringWithouRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithouRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
    }

    // my latest code
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        
        boolean[] exist = new boolean[256];
        for(int start = 0, end = 0; end < s.length(); end++) {
            while(end < s.length() && !exist[s.charAt(end)]) {
                exist[s.charAt(end)] = true;
                end++;
            }
            
            max = Math.max(max, end-start);
            if(end == s.length())
                break;
            
            while(s.charAt(start) != s.charAt(end)) {
                exist[s.charAt(start)] = false;
                start++;
            }
            start++;
        }
        
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0)
            return 0;
        int[] pos = new int[26];
        for(int i = 0; i < pos.length; i++)
            pos[i] = -1;

        char[] chs = s.toCharArray();
        int start = 0;
        int max = 1;
        int i = 0;
        for(; i < chs.length; i++) {
            if(pos[chs[i]-'a'] != -1) {
                max = Math.max(max, i-start);
                for(int j = start; j < pos[chs[i]-'a']; j++)
                    pos[chs[j]-'a'] = -1;

                start = pos[chs[i]-'a']+1;
            }

            pos[chs[i]-'a'] = i;
        }
        
        return Math.max(max, chs.length-start);
    }
}
