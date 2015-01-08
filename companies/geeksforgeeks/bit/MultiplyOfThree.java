package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/write-an-efficient-method-to-check-if-a-number-is-multiple-of-3/
 */
public class MultiplyOfThree {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++)
			if(isMultiplyOf3(i))
				System.out.print(i + ", ");
	}

	public static boolean isMultiplyOf3(int num) {
		int odd = 0, even = 0;
		num = Math.abs(num);

		boolean isOdd = false;
		while (num != 0) {
			if ((num & 1) != 0) {
				if (!isOdd)
					even++;
				else
					odd++;
			}
			num >>= 1;
			isOdd = !isOdd;
		}

		return Math.abs(odd - even) % 3 == 0;
	}
}