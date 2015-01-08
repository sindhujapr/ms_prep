package interview.java;


public class ToHex {
    public static String formatRGB ( int r, int g, int b ) {
        return (toHex(r) + toHex(g) + toHex(b)).toUpperCase();
    }

    public static String toHex ( int c ) {
        String s = Integer.toHexString ( c );
        return ( s.length() == 1 ) ? "0" + s : s;
    }
    
	public static void main(String[] args) throws Exception {
		char chs[] = {'a', 'b', 'c' };
		System.out.println(formatRGB(chs[0], chs[1], chs[2]));
	}
}