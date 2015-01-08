package interview.skt;

import java.util.Arrays;
import java.util.Random;

public class Algorithm {
    /* There may be multiple duplicate numbers (1-100) in the array of size 99 */
    public static void findDuplicates1(int array[]) {
        // all default to false
        boolean myArray[] = new boolean[100];
        System.out.print("Duplicate number ");
        for (int i = 0; i < array.length; i++) {
            /* Here array[i] is an index to myArray[], its value is between 1 and 100 */
            if(myArray[array[i]] == true)
                System.out.print(array[i] + " ");
            else
                myArray[array[i]] = true;
        }
        System.out.println();
    }
    
    /* The size of the arry is odd and only one element is unique. All other element has a duplicat */ 
    public static void findDuplicates2(int array[]) {
        int value = 0;
        for (int i = 0; i < array.length; i++) {
            value ^= array[i];
        }
        
        System.out.println("Unique element " + value);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int array1[] = new int[99];
        for (int i = 0; i < 99; i++) {
            int value;
            while((value = rand.nextInt(100)) == 0);
            array1[i] = value;
        }
        
        findDuplicates1(array1);
        
        int array2[] = {0, 3000, 400, 29, 380, 400, 3000, 29, 0};
        System.out.println(Arrays.toString(array2));
        findDuplicates2(array2);
    }
}