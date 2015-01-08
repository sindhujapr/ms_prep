package lc;

import java.util.ArrayList;

public class DivideTwoIntegers {
	public static void main(String[] args) {
//		System.out.println(new DivideTwoIntegers().divide(-2147483648, -3));
		System.out.println(new DivideTwoIntegers().divide4(2147483647, 3));
	}

	public int divide(int dividend, int divisor) {
		long a = Math.abs((long) dividend), b = Math.abs((long) divisor);
		long[] res = dividePos(a, b);
		long temp = dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0 ? -res[0] : res[0];
		return (int) temp;
	}

	public long[] dividePos(long a, long b) {
		long[] res = new long[2];
		if (a < b) {
			res[0] = 0;
			res[1] = a;
		} else if (a == b) {
			res[0] = 1;
			res[1] = 0;
		} else {
			long[] temp = dividePos(a >> 1, b);
			res[0] = temp[0] << 1;
			res[1] = temp[1] << 1;
			if ((a & 1) == 1)
				res[1] += 1;
			if (res[1] >= b) {
				res[0] += 1;
				res[1] -= b;
			}
		}
		return res;
	}

	// http://www.lifeincode.net/programming/leetcode-divide-two-integers-java/
    public int divide(int dividend, int divisor) {
        boolean sign = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        
        long a = Math.abs((long) dividend), b = Math.abs((long) divisor);
        int res = 0;
        while(a >= b) {
            int cnt = 0;
            while(a >= (b << cnt))
                cnt++;
            
            res += 1 << (cnt-1);
            a -= b << (cnt-1);
        }
        
        return sign ? res : -res;
    }

	public int divide3(int dividend, int divisor) {
		if (dividend == 0)
			return 0;
		if (divisor == 0)
			return (dividend > 0) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		/*
		 * again here we MUST use long!!!!
		 */
		long v1 = Math.abs((long) dividend);
		long v2 = Math.abs((long) divisor);

		int result = 0, count = 0;
		for (; v1 >= v2 << 1; count++)
			v2 <<= 1;
		for (; count >= 0; count--, v2 >>>= 1) {
			if (v1 >= v2) {
				v1 -= v2;
				result += (1 << count);
			}
		}

		return ((dividend ^ divisor) >>> 31 == 1) ? -result : result;
	}

	public int divide1(int dividend, int divisor) {
		if (dividend == 0 || divisor == 1)
			return dividend;
		if (divisor == -1)
			return 0 - dividend;

		/*
		 * Strangely, if we don't use "long" but "int", there will be memory
		 * limit exceeded error.
		 */
		long divd = Math.abs((long) dividend), divs = Math.abs((long) divisor);
		ArrayList<Long> divisors = new ArrayList<Long>();
		long v1 = divs;
		while (v1 <= divd) {
			divisors.add(v1);
			v1 = v1 << 1;
		}

		int result = 0;
		int i = divisors.size() - 1;
		while (divd >= divs) {
			while (i >= 0 && divisors.get(i) > divd)
				i--;

			result += (1 << i);
			divd -= divisors.get(i);
		}

		return (dividend > 0) ^ (divisor > 0) ? (-result) : result;
	}
	
    public int divide4(int dividend, int divisor) {
        boolean sign = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        
        long v1 = Math.abs((long) dividend);
        long v2 = Math.abs((long) divisor);
        
        int i = 0;
        long val = v2;
        while(val <= v1) {
        	// if we use int, here may be overflow
            val <<= 1;
            i++;
        }
            
        int res = 0;
        for(int j = i; j >= 0; j--) {
            if((v2 << j) <= v1) {
                res += 1 << j;
                v1 -= v2 << j;
            }
        }
        
        return sign ? res : -res;
    }
}
