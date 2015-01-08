package lc;

public class LengthOfLastWord {
    public static void main(String[] args) {
        LengthOfLastWord instance = new LengthOfLastWord();
        System.out.println(instance.lengthOfLastWord(" fsljdf sd wolrlsdf ss "));
    }
    
    /*
     * only scan the last part of the string
     */
    public int lengthOfLastWord(String s) {
        assert s != null;
        
        int i = s.length()-1;
        while(i >= 0 && s.charAt(i) == ' ')
            i--;
            
        int j = i;
        while(j >= 0 && s.charAt(j) != ' ')
            j--;

        return i-j;
    }
}
