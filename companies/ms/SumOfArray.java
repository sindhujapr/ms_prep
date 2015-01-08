package interview.ms;

/*
 * 题目：求1+2+…+n，
 * 要求不能使用乘除法、for、while、if、else、switch、case
 * 等关键字以及条件判断语句（A?B:C）。
 */
public class SumOfArray {
    public static int sum(int[] array) {
    return sum(array[array.length-1]);
    }
    
    private static int sum(int value) {
    int sum = 0;
    boolean b = (value > 0) && ((sum = value + sum(value-1)) > 0); 
    return sum;
    }
    
    
    public static void main(String[] args) {
    int[] array = new int[100];
    for (int i = 0; i < array.length; i++) {
        array[i] = i+1;
    }
    
    System.out.println(sum(array));
    }
}