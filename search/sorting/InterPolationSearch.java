package search.sorting;

/*
 * http://data.linkedin.com/blog/2010/06/beating-binary-search
 * http://en.wikipedia.org/wiki/Interpolation_search
 */
public class InterPolationSearch {
    public static void main(String[] args) {
        int[] array = {1, 3, 5, 7, 9, 12, 14, 16, 18, 20};
        for(int i = 0; i < array.length; i++)
            System.out.println(interpolationSearch(array, array[i]));
    }
    
    public static int interpolationSearch(int[] array, int k) {
        assert array != null && array.length > 0;
        
        int i = 0, j = array.length-1;
        int min = array[0], max = array[array.length-1];
        
        while(i <= j) {
            int pos = i + (int) Math.ceil((k-min) * (j-i)/(double) (max-min));
            if(array[pos] == k)
                return pos;
            
            if(array[pos] > k) {
                j = pos-1;
                max = array[j];
            } else {
                i = pos+1;
                min = array[i];
            }
        }
        
        return -1;
    }
}