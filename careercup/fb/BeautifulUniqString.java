package careercup.fb;

/*
 * http://www.careercup.com/question?id=14463150
 */
public class BeautifulUniqString {
    public static void main(String[] args) {
        System.out.println(beautify("babab"));
        System.out.println(beautify("nlhthgrfdnnlprjtecpdrthigjoqdejsfkasoctjijaoebqlrgaiakfsbljmpibkidjsrtkgrdnqsknbarpabgokbsrfhmeklrle"));
    }
    
    public static String beautify(String s) {
        assert s != null;
        
        // all bits will be turned off automatically
        int[] bits = new int[8];
        
        for(int i = 0; i < s.length(); i++)
            setbit(bits, s.charAt(i));

        StringBuilder builder = new StringBuilder();
        // reverse order!!
        for(int i = bits.length-1; i >= 0; i--) {
            for(int j = 31; j >= 0; j--) {
                if(isBitOn(bits[i], j))
                    builder.append((char) (32 * i + j));
            }
        }
        return builder.toString();
    }

    private static void setbit(int[] bits, char ch) {
        int m = ch / 32;
        int n = ch % 32;
        bits[m] |= 1 << n;
    }
    
    private static boolean isBitOn(int v, int n) {
        if((v & (1 << n)) > 0)
            return true;
        return false;
    }
}