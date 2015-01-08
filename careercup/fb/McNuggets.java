package careercup.fb;

public class McNuggets {
    public static void main(String[] args) {
        int[] array = {6, 9, 20};
        for(int i = 1; i < 31; i++)
            System.out.println(mcNuggets(array, i) == mcNuggests_recur(array, i));
    }

    // my latest code
    public boolean McNuggets(int target) {
        boolean[] flag = new boolean[target+1];
        flag[0] = true;

        for(int i = 1; i < flag.length; i++)
            flag[i] = (i >= 6 && flag[i-6]) || (i >= 9 && flag[i-9]) || (i >= 20 && flag[i-20]);

        return flag[target];
    }
    
    // See IntegerKnapsack. Here the sizes are all one.
    public static boolean mcNuggets(int[] array, int K) {
        int n = array.length;
        boolean[] result = new boolean[K+1];
        // it's possible to buy 0
        result[0] = true;
        
        for(int i = 1; i <= K; i++) {
            for(int j = 0; j < n; j++) {
                int index = i-array[j];
                if(index < 0)
                    continue;
                
                if(result[index]) {
                    result[i] = true;
                    break;
                }
            }
        }
        
        return result[K];
    }
    
    public static boolean mcNuggests_recur(int[] array, int K) {
        if(K < 0)
            return false;

        if(K == 0)
            return true;
        
        for(int i = 0; i < array.length; i++)
            if(mcNuggests_recur(array, K-array[i]))
                return true;

        return false;
    }
}
