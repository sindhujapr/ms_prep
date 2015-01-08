package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/find-the-element-that-appears-once/
 */
public class FindElementAppearsOnce {
    public static void main(String[] args) {
        int[] array = {3, 3, 10, 3, 2, 2, 2};
        System.out.println(find(array));
        System.out.println(find2(array));
    }
    
    public static int find(int[] array) {
        assert array.length % 3 == 1;
        
        int once = 0, moreThanOnes = 0, mask = 0;
        for(int i = 0; i < array.length; i++) {
            // keep only digits that appear twice and more
            moreThanOnes |= once & array[i];
            
            // keep digits that appear once or three times
            once ^= array[i];
            
            // mask digits that appear three times
            mask = ~(once & moreThanOnes);
            
            // keep digits that appear only twice
            moreThanOnes &= mask;
            
            // keep digits that appear only once 
            once &= mask;
        }
        
        return once;
    }
    
    public static int find2(int[] array) {
        int result = 0;
        // int value has 32 bits
        int BIT_CNT = 32;
        for(int j = 0; j < BIT_CNT; j++) {
            int temp = 0;
            for(int i = 0; i < array.length; i++)
                temp += (array[i] & (1 << j)) != 0 ? 1 : 0;
            if(temp % 3 == 1)
                result |= 1 << j;
        }
        
        return result;
    }
}
