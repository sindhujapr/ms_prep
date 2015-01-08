package interview.thefind;

/*
 * given a string containing blank-separated words, reverse the words. That means,
 * the last word will be put in the first, the second last word will be put in
 * the second. For example, "my name is simon" will be converted to "simon is name
 * my". You can assume there is only one blank between two words. Do this in place.
 */
public class ReverseWords {
    public static void main(String[] args) {
        String res = new ReverseWords().reverse("my name is simon");
        System.out.println(res);
    }
    
    public String reverse(String str) {
        char[] words = str.toCharArray();
        for(int n = words.length, i = n-1, start = 0, cnt = 0; i >= 0; i--) {
            char lastCh = words[n-1];
            if(lastCh == ' ') {
                shiftByOne(words, start + cnt);
                start += ++cnt;
                cnt = 0;
            } else {
                shiftByOne(words, start);
                cnt++;
            }
        }
        
        StringBuilder builder = new StringBuilder();
        for(char ch : words)
            builder.append(ch);
        return builder.toString();
    }
    
    private void shiftByOne(char[] words, int start) {
        char lastCh = words[words.length-1];
        for(int i = words.length-1; i > start; i--)
            words[i] = words[i-1];
        words[start] = lastCh;
    }
}