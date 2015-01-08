package careercup.fb;

/*
 * http://www.careercup.com/question?id=4798365246160896
 */
public class ArithmeticProgression {
    public static void main(String[] args) {
        int[] array = {1, 3, 7, 9, 11, 13};
        System.out.println(findMissing(array));
        
        System.out.println(findMissing_binary(array));
    }
    
    /*
     * the input array must have one such one missing element
     */
    public static int findMissing(int[] array) {
        assert array != null && array.length > 2;
        
        int d = array[1] - array[0];
        for(int i = 2; i < array.length; i++) {
            if(array[i] > array[i-1] + d)
                return array[i]-d;
            else if(array[i] < array[i-1] + d)
                return (array[0] + array[1]) / 2;
        }
        
        return -1;
    }
    
    /*
     * binary search
     */
    public static int findMissing_binary(int[] array) {
        assert array != null && array.length > 2;
        
        int diff = Math.min(array[2]-array[1], array[1]-array[0]);

        int low = 0, high = array.length-1;
        while(low < high) {
            int mid = (low+high) >>> 1;

            int leftDiff = array[mid]-array[low];
            if(leftDiff > diff * (mid-low)) {
                if(mid-low == 1)
                    return (array[mid]+array[low]) / 2;
                
                high = mid;
                continue;
            }
            
            int rightDiff = array[high]-array[mid];
            if(rightDiff > diff * (high-mid)) {
                if(high-mid == 1)
                    return (array[high]+array[mid]) / 2;
                
                low = mid;
                continue;
            }
        }
        
        return -1;
    }
}