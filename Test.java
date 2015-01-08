package lc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public void f() throws IOException {

	}

	public static void main(String[] args) {
		int[] array = {2, -1, -2, 1, 3, -2, 3, -3, 1, 2};
		
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
			if(sum < 0)
				sum = 0;
			
			if(sum > max)
				max = sum;
		}
		
		System.out.println(max);
	}
}