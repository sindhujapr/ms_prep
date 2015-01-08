package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingcunz on 10/25/14.
 */
public class TextJustification {
    public static void main(String[] args) {
        List<String> res = new TextJustification().fullJustify(new String[] {"a"}, 1);
        for(String str : res) {
            System.out.println(str);
        }
    }

    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<String>();
        for(int start = 0, end = 0; end < words.length; end++) {
            int len = 0;
            while(end < words.length && len + words[end].length() + 1 <= L) {
                len += words[end].length()+1;
                end++;
            }

            res.add(gen(words, start, end, L));
        }
        return res;
    }

    private String gen(String[] words, int start, int end, int L) {
        int spaces = L;
        for(int i = start; i < end; i++)
            spaces -= words[i].length();

        int count = end-start;
        int spacePerWord = count == 1 ? spaces : spaces/(count-1);
        int additionalSpace = (count == 1 ? 0 : spaces - spacePerWord * (count-1));

        StringBuilder builder = new StringBuilder();
        for(int i = start; i < end; i++) {
            builder.append(words[i]);
            if(additionalSpace-- > 0)
                builder.append(' ');
            for(int j = 0; i != end-1 && j < spacePerWord; j++)
                builder.append(' ');
        }
        return builder.toString();
    }
}
