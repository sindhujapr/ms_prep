package careercup.fb;

/*
 * http://www.careercup.com/question?id=5197969674469376
 */
public class RemoveSubstring {
    public static void main(String[] args) {
        System.out.println(removeSubstring("zabcaabcbcc", "abc"));
    }
    
    public static String removeSubstring(String source, String str) {
        StringBuilder builder = new StringBuilder(source);
        while(builder.indexOf(str) != -1) {
            int pos = builder.indexOf(str);
            builder.delete(pos, pos+str.length());
        }
        
        return builder.toString();
    }
}
