package lc;

import java.util.ArrayList;

public class TextJustification {
    /*
     * another version:
     * http://gongxuns.blogspot.com/2012/12/leetcodetext-justification.html
     */
    public ArrayList<String> fullJustify(String[] words, int L) {
        ArrayList<String> result = new ArrayList<String>();
        if(words == null || words.length == 0)
            return result;
        
        for(int i = 0; i < words.length; ) {
            int wordsLength = 0;
            int origin = i;
            
            while(i < words.length) {
                int wordCnt = i-origin;
                if(wordCnt+wordsLength+ words[i].length() <= L) {
                    wordsLength +=words[i].length();
                    i++;
                } else {
                    break;
                }
            }
            
            int wordCnt = i-origin;
            int spaceCnt = L-wordsLength;
            if(wordCnt == 0)
                break;
            else if(wordCnt == 1) {
                StringBuilder sb = new StringBuilder();
                sb.append(words[origin]);
                while(spaceCnt-- > 0)
                    sb.append(' ');
                result.add(sb.toString());
                continue;
            }
                
            int avg = spaceCnt / (wordCnt-1);
            int left = spaceCnt % (wordCnt-1);
            StringBuilder sb = new StringBuilder();
            for(int j = origin; j < i; j++) {
                sb.append(words[j]);
                if(j < i-1) {
                    int cnt = avg;
                    while(cnt-- > 0)
                        sb.append(' ');
                    if(j-origin+1 <= left)
                        sb.append(' ');
                }
            }
            result.add(sb.toString());
        }
        if(result.size() > 0) {
            String last = result.remove(result.size()-1);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < last.length(); ) {
                if(last.charAt(i) == ' ') {
                    sb.append(' ');
                    while(i < last.length() && last.charAt(i) == ' ')
                        i++;
                    continue;
                }
                sb.append(last.charAt(i));
                i++;
            }
            while(sb.length() < L)
                sb.append(' ');
            result.add(sb.toString());
        }
        return result;
    }
}