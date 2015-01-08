package javautils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/*
 * http://stackoverflow.com/questions/5351510/why-character-streams
 * 
 * comments from Jon:
 * Code that deals with strings should only "think" in terms of text - for example, 
 * reading an input source line by line, you don't want to care about the nature 
 * of that source. However, storage is usually byte-oriented - so you need to create
 * a conversion between the byte-oriented view of a source (encapsulated by InputStream) 
 * and the character-oriented view of a source (encapsulated by Reader). So a method 
 * which (say) counts the lines of text in an input source should take a Reader 
 * parameter. If you want to count the lines of text in two files, one of which is 
 * encoded in UTF-8 and one of which is encoded in UTF-16, you'd create an 
 * InputStreamReader around a FileInputStream for each file, specifying the 
 * appropriate encoding each time. (Personally I would avoid FileReader 
 * completely - the fact that it doesn't let you specify an encoding makes it useless IMO.)
 */

public class IOTest {
    public static void main(String[] args) {
    try (InputStreamReader reader = new InputStreamReader(
        new FileInputStream(new File("")), StandardCharsets.UTF_8);) {
        int i = reader.read();
        System.out.println(i);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}