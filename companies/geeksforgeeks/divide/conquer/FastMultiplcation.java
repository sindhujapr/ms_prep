package geeksforgeeks.divide.conquer;

/*
 * http://www.geeksforgeeks.org/divide-and-conquer-set-2-karatsuba-algorithm-for-fast-multiplication/
 */
public class FastMultiplcation {
	public static void main(String[] args) {
		
	}
	
	public static int multiply(String x, String y) {
		StringBuilder builder = new StringBuilder();
		for(int i = Math.abs(x.length()-y.length()); i > 0; i--)
			builder.append('0');
		
		if(x.length() > y.length()) {
			builder.append(y);
			y = builder.toString();
		} else if(x.length() < y.length()) {
			builder.append(x);
			x = builder.toString();
		}
		
		String s = _multiply(x, y);
		return Integer.parseInt(s, 2);
	}

	private static String _multiply(String x, String y) {
		return null;
	}
}
