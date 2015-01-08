package general;

/*
 * Input:
 * a = 0, 1, 2, 3, 4
 * b = 1, 3, 5, 7, 9
 * output: {1, 3}
 */

public class InterSection {
    public static void find(int[] arr1, int[] arr2) {
    int i1 = 0;
    int i2 = 0;

    while(i1 < arr1.length && i2 < arr2.length) {
        while(i1 < arr1.length && i2 < arr2.length && arr1[i1] <= arr2[i2]) {
        if(arr1[i1] == arr2[i2])
            System.out.println(arr1[i1]);
        i1++;
        }
        
        while(i1 < arr1.length && i2 < arr2.length && arr1[i1] >= arr2[i2]) {
        if(arr1[i1] == arr2[i2])
            System.out.println(arr1[i1]);
        i2++;
        }
    }
    }
    
    public static void main(String[] args) {
    int[] arr1 = {0, 1, 2, 3, 4};
    int[] arr2 = {1, 3, 5, 7, 9};
    
    find(arr1, arr2);
    }
}