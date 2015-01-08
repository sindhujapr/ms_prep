package careercup.fb;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.careercup.com/question?id=19300678
 */
public class StringDecode {
    public static void main(String[] args) {
        String s = "110233120312";
        
        List<String> result = split(s);
        System.out.println("recursion: " + result.size());
        for(String str: result)
            System.out.println(str);
        
        List<String> res = decode(s);
        System.out.println("DP: " + res.size());
        for(String str : res)
            System.out.println(str);
        
        compare(res, result);
    }
    
    public static void compare(List<String> list1, List<String> list2) {
        for(String str : list1) {
            if(!list2.contains(str))
                System.out.println("Not equal");
        }
        System.out.println("equals");
    }

    public static List<String> split(String s) {
        List<StringBuilder> result = new ArrayList<StringBuilder>();
        StringBuilder builder = new StringBuilder();
        split(s, 0, builder, result);
        
        List<String> res = new ArrayList<String>();
        for(StringBuilder builder1 : result)
            res.add(builder1.toString());
        
        return res;
    }

    private static void split(String s, int start, StringBuilder builder, List<StringBuilder> result) {
        if (start >= s.length()) {
            result.add(new StringBuilder(builder));
            return;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            String substring = s.substring(start, i);
            int value = Integer.valueOf(substring);
            // "02" is illegal
            if (substring.startsWith("0") || value < 1 || value > 26)
                break;

            builder.append((char) ('a' + value - 1));
            split(s, i, builder, result);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
    
    public static List<String>  decode(String s) {
        assert s != null && s.length() > 0;
        
        List<StringBuilder> list0 = new ArrayList<StringBuilder>();
        List<StringBuilder> list1 = new ArrayList<StringBuilder>();
        
        for(int i = 0; i < s.length(); i++) {
            List<StringBuilder> list = new ArrayList<StringBuilder>();
            
            if(s.charAt(i) != '0') {
                int value = Integer.valueOf(s.substring(i, i+1));
                // list1 content needs to be reserved, thus we need deep copy
                for(StringBuilder builder : list1)
                    list.add(new StringBuilder(builder.toString()));
                addCharacter(list, value);
            }

            if(i > 0) {
                int value = Integer.valueOf(s.substring(i-1, i+1));
                if(s.charAt(i-1) != '0' && value >= 1 && value <= 26) {
                    // list0 content can be reused so we don't need deep copy
                    addCharacter(list0, value);
                    list.addAll(list0);
                }
            }
            
            list0 = list1;
            list1 = list;
        }
        
        List<String> result = new ArrayList<String>();
        for(StringBuilder builder : list1)
            result.add(builder.toString());
        
        return result;
    }
    
    private static void addCharacter(List<StringBuilder> list, int value) {
        char ch = (char) ('a' + value -1);
        if(list.size() == 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(ch);
            list.add(builder);
        } else {
            for(StringBuilder builder : list)
                builder.append(ch);
        }
    }
}