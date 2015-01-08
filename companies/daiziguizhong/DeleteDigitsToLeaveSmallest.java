package interview.daiziguizhong;

/*
 * http://chuansongme.com/n/287133
 */
public class DeleteDigitsToLeaveSmallest {
	public static void main(String[] args) {
		System.out.println(new DeleteDigitsToLeaveSmallest().delete(41239, 2));
	}
	
	/*
	 * k is smaller than the number of digits in the given value.
	 * Do we need to consider negative values?
	 */
	public int delete(int value, int k) {
		// convert the integer to string for each manipulation
		StringBuilder builder = new StringBuilder();
		while(value > 0) {
			int bit = value % 10;
			value /= 10;
			builder.insert(0, (char) (bit+'0'));
		}
		
		for(int i = 0; i < builder.length()-1 && k > 0; ) {
			if(builder.charAt(i) > builder.charAt(i+1)) {
				// the current digit at i is removed so i shouldn't be increased
				builder.deleteCharAt(i);
				k--;
			} else {
				i++;
			}
		}
		
		while(k-- > 0)
			builder.deleteCharAt(builder.length()-1);
		
		value = 0;
		for(char ch : builder.toString().toCharArray())
			value = value * 10 + (ch-'0');
		
		return value;
	}
}
