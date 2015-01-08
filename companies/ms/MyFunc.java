package interview.ms;

import java.util.Random;

/*
given a function,explain what it does.
int my_fun(int x ,int y)
{
  //rand() returns a real number between 0 and 1, inclusively. 
  return x + (int)((y-x)*rand());
}
i answered: it should return an integer between x and y. Then he asked for test function.
Follow up: how to programmatically prove my_func must return a random integer?
Follow up2: How to detect a pattern in the distribution of the random 
integers? Ex. 1,2,3,1,2,3,1,2,3
*/
public class MyFunc {
	static Random rand = new Random(1);
	public static void main(String[] args) {
		System.out.println(my_func(1, 2));
		System.out.println(my_func(1, 3));
		System.out.println(my_func(1, 4));
		System.out.println(my_func(2, 4));
		System.out.println(my_func(3, 10));
	}

	static int my_func(int x, int y) {
		double d = rand.nextDouble();
		System.out.print(d + "\t");
		return x + (int)((y - x) * d);
	}
}
