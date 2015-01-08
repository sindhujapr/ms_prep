package regex;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyRegexTest {
    private static final String regex = "(/\\w+){1,}";
    private static final Pattern pattern = Pattern.compile(regex);
    // match slashes repeated twice or above
//  private static final Pattern pattern = Pattern.compile("[/]{2}|[.]{3}");
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String words = null;
        
        while((words = br.readLine()) != null) {
            System.out.println(words.matches(regex));
            Matcher matcher = pattern.matcher(words);
            while(matcher.find())
                System.out.println(matcher.start() + "\t" + matcher.end() + "\t" + matcher.group());
        }
    }
}