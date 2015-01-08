package search.sorting;

/*
 * http://blog.teamleadnet.com/2012/07/quick-select-algorithm-find-kth-element.html
 * http://11011110.livejournal.com/119720.html
 */
public class QuickSelection {
    public static void main(String[] args) {
        int[] array = {3, 2, 5, 7, 1, 6, 0, 4};
        for(int i = 0; i < 7; i++) {
            System.out.println(i + ":\t" + quickSelect_iter(array, i) + "\t" + quickSelect(array, i));
        }
    }

    public static int quickSelect(int[] array, int K) {
        return quickSelect(array, 0, array.length-1, K);
    }
    
    private static int quickSelect(int[] array, int start, int end, int K) {
        if(K < start || K > end)
            return -1;

        // Do we need this?
        if(start > end)
            return -1;

        int j = start;
        for(int i = start+1; i <= end; i++)
            if(array[i] < array[start])
                swap(array, ++j, i);

        if(j == K)
            return array[j];

        swap(array, start, j);
        
        if(j < K)
            return quickSelect(array, j+1, end, K);
        else
            return quickSelect(array, start, j-1, K);
    }

    public static int quickSelect_iter(int[] array, int K) {
        assert array != null && K >= 0 && K < array.length;
        
        int start = 0, end = array.length-1;
        while(start <= end) {
            int j = start;
            for(int i = start+1; i <= end; i++)
                if(array[i] < array[start])
                    swap(array, ++j, i);

            swap(array, start, j);

            if(j == K)
                return array[j];
            
            if(j < K)
                start = j+1;
            else
                end = j-1;
        }

        return -1;
    }

    private static void swap(int[] array, int i, int j) {
        if(i == j)
            return;

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
