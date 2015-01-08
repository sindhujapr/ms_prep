package interview.commvault.PrimeNumber;

import java.math.BigInteger;

/*
 * Generate one prime number each time but doesn't keep generated ones
 */
public class PrimeNumberGenerator implements INumberGenerator<BigInteger> {
	private BigInteger current;
	private final static BigInteger TWO = new BigInteger("2");

	public PrimeNumberGenerator() {
		current = BigInteger.ONE;
	}

	/*
	 * Algorithm is not a focus here, so I use BigInteger.isProbablePrime(certainty) to judge whether
	 * a BigInteger is prime. If I pass 20 as parameter "certainty", the possibility the number
	 * is prime will exceed 1-2^20 (around 99.9999%). I also have a dumb implementation below.
	 */
	@Override
	public BigInteger next() {
		/* Suppose there is unlimited number of prime numbers and thus there is always a next one */
		for (current = current.add(BigInteger.ONE); ; current = current.add(BigInteger.ONE)) {
			if(current.isProbablePrime(20)) {//isPrime(bInt)) {
				return current;
			}
		}
	}

	/* Performance is not good */
	@SuppressWarnings("unused")
	private boolean isPrime(BigInteger number) {
		for(BigInteger bInt = number.divide(TWO);
				bInt.compareTo(TWO) > 0;
				bInt = bInt.subtract(BigInteger.ONE)) {
			BigInteger result = number.divide(bInt);
			if((number.subtract(result.multiply(bInt))).equals(BigInteger.ZERO))
				return false;
		}

		return true;
	}
}