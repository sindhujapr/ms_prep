package general;

import java.util.Arrays;

/*
 * http://blog.csdn.net/v_JULY_v/article/details/6110269
 * This is to build a matrix and then find the biggest value.
 * Alternatively, we can use trie tree and insert all suffixes of
 * one string, and then try to match suffixes of the other string
 */
public class LongestCommonSubString {
    public int[][] buildMatrix(char[] arr1, char[] arr2) {
    int[][] matrix = new int[arr1.length][];
    for (int i = 0; i < arr1.length; i++) {
        matrix[i] = new int[arr2.length];
        for (int j = 0; j < arr2.length; j++) {
        if(arr1[i] == arr2[j]) {
            if(i > 0 && j > 0)
            /*
             * this is a trick!!! Check the link above
             */
            matrix[i][j] = matrix[i-1][j-1] + 1;
            else
            matrix[i][j] = 1;
            
        } else {
            matrix[i][j] = 0;
        }
        }
    }

    System.out.println(Arrays.deepToString(matrix));
    return matrix;
    }
    
    public void visit(char[] arr1, int[][] matrix) {
    int max = 0;
    int row = -1;

    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
        if(matrix[i][j] > max) {
            max = matrix[i][j];
            row = i;
        }
        }
    }
    
    System.out.println("longest common sub string: " + max);
    for (int i = row; row-i < max; i--) {
        System.out.print(arr1[i]);
    }
    }
    
    public static void main(String[] args) {
    LongestCommonSubString instance = new LongestCommonSubString();
    char[] arr1 = "caba".toCharArray();
    char[] arr2 = "bab".toCharArray();
    int[][] matrix = instance.buildMatrix(arr1, arr2);
    instance.visit(arr1, matrix);
    }
}