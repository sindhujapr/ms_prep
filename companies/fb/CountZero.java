package interview.fb;

import java.math.BigInteger;

/*
 * http://chuansongme.com/n/278671
 */
public class CountZero {
	public int count(int n) {
		assert n >= 1;
		
		int cnt = 0;
		for(int i = 1; i <= n; i++)
			cnt += countNum(i);

			return cnt;
	}
	
	private int countNum(int n) {
		int cnt = 0;
		while(n > 0) {
			if(n % 5 == 0) {
				cnt++;
				n /= 5;
			} else {
				break;
			}
		}
		return cnt;
	}
	
	public int count1(int n) {
		BigInteger bi = new BigInteger("1");
		for(int i = 1; i <= n; i++)
			bi = bi.multiply(new BigInteger(Integer.toString(i)));
		
		int cnt = 0;
		BigInteger zero = new BigInteger("0");
		BigInteger ten = new BigInteger("10");
		while(bi.compareTo(zero) > 0) {
			int bit = bi.mod(ten).intValue();
			if(bit > 0)
				break;
			else
				cnt++;
			bi = bi.divide(ten);
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		System.out.println(new CountZero().count(200));
		System.out.println(new CountZero().count1(200));
	}
}