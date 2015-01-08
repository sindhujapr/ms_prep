package careercup.fb;

/*
 * http://www.careercup.com/question?id=14118790
 * 
 * Two situations if certain word is too large to be kept in memory:
 * 1. The disk support random access, then we can reverse the first part of the
 * large word and then insert the reversed second part of the word before the 
 * first part, and so on.
 * 2. The disk doesn't support random access. Then we need to first read the last
 * part of the large word, reverse/write it, and then continue with the 2nd last
 * part (thus involving reopen the file, etc)
 */
public class ReverseWords {
    public static void main(String[] args) {
        
    }
}