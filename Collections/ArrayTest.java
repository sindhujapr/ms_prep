package Collections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class ArrayTest {
    private static int[][] arr = new int[5][];
    private static Random rand = new Random();

    public static void main(String[] args) throws IOException {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = new int[2*i+1];
            for(int j = 0; j < arr[i].length; j++)
                arr[i][j] = rand.nextInt(100);
        }
        
        System.out.println(Arrays.deepToString(arr));
    }
}