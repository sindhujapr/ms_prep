package geeksforgeeks.dp;

public class MakingChange {
    public static void main(String[] args) {
        int[] array = {1, 3, 4, 6, 8, 5};
        
        System.out.println(makeChange(array, 17));
    }
    
    public static int makeChange(int[] array, int C) {
        int[] result = new int[C+1];
        result[0] = 0;

        for(int i = 1; i <= C; i++) {
            result[i] = Integer.MAX_VALUE;

            for(int j = 0; j < array.length; j++) {
                if(i >= array[j])
                    result[i] = Math.min(result[i-array[j]] + 1, result[i]);
            }
        }
        return result[C];
    }
}