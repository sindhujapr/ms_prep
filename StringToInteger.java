package lc;

/*
 * we need to consider:
 * 1) leading blank spaces and appending blank spaces
 * 2) non-digit
 * 3) sign (+ or -)
 * 4) zeros before digits
 * 5) overflow and underflow (Integer.MIN_VALUE and Integer.MAX_VALUE)
 */
public class StringToInteger {
	public int atoi(String str) {
		if (str.length() == 0)
			return 0;

		int i;
		for (i = 0; i < str.length() && str.charAt(i) == ' '; i++)
			;
		str = str.substring(i);

		int index = 0;
		if (str.charAt(0) == '-' || str.charAt(0) == '+') {
			index = 1;
		} else if (str.charAt(0) > '9' || str.charAt(0) < '0') {
			return 0;
		}

		while (str.charAt(index) == '0')
			index++;

		long value = 0;
		for (i = index; i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9'; i++) {
			value = value * 10 + (str.charAt(i) - '0');
		}

		if (value > Integer.MAX_VALUE && str.charAt(0) == '-')
			return Integer.MIN_VALUE;

		if (value > Integer.MAX_VALUE)
			value = Integer.MAX_VALUE;

		value *= (str.charAt(0) == '-') ? -1 : 1;

		return (int) value;
	}
}
