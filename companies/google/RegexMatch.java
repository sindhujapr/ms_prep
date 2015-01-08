package interview.google;

import java.util.regex.Pattern;

public class RegexMatch {
    private static Pattern pattern = Pattern.compile(".*a{2,}.*b{1,}.*c{1,}.*s{1,}.*u{1,}.*");
    
    public static void main(String[] args) {
        String str = "haaabccsbu";
        String regex = ".*a{2,}.*b{1,}.*c{1,}.*s{1,}.*u{1,}.*";
        System.out.println(str.matches(regex));
        
        str = "xxxaassbcxxxuss";
        System.out.println(str.matches(regex));
    }
}