package interview.ms;

/*
 * In Java, how does Unicode strings get compared?
 * What I mean is, if I have a few say, Japanese strings, when I do the following:
 * java.util.Arrays.sort(arrayOfJapaneseStrings);
 * how does those strings get compared and sorted?
*/

/*
 * By default, Strings sort lexicographically, by Unicode order. 
 * The order is by UTF-16, so might not be exactly what you want for certain characters, 
 * but Japanese characters are all in the BMP, so you shouldn't have a problem with these.
 * If you would like a different sort order, 
 * you can use the java.text.Collator classes to define a different sort order.
 */
public class Unicode {

}
