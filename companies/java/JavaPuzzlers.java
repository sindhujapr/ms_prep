package interview.java;

import java.io.File;
import java.util.regex.Matcher;

public class JavaPuzzlers {
	public static void main(String[] args) {
		char x = 'X';
		int i = 0;
		System.out.println(true ? x : 0);
		System.out.println(false ? i : x);
//		System.out.println(false ? (char)i : x);
		
		short s = 10;
		int ii = 123233;
		s += ii;
		System.out.println(s);
		
		// Compiling error
//		s = s + ii;
		
		System.out.println("H" + "a");
		System.out.println('H' + 'a');
		
		System.out.println("2 + 2 = " + 2 + 2);
		System.out.printf("%c%c\n", 'H', 'a');
		
		char ch[] = {'a', 'b', 'c'};
		System.out.println(ch);
		System.out.println("this is not we expect " + ch);
		System.out.println("this is fine " + String.valueOf(ch));
		
		final String pig = "leng: 1000";
		final String dog = "leng: " + pig.length();
		// + operator takes precedence over == operator
		System.out.println("Animals are equal: " + pig == dog);
		
		// We need another "//" before "is"
		// Note: \u000A //is Unicode representation of LF
		char c = 0x000A;
		System.out.println(c);
		
		System.out.println(JavaPuzzlers.class.getName().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + ".class");
		
		
		System.out.print("iexplore:");
		http://www.google.com;
		System.out.println(":maximize");
	}
}
