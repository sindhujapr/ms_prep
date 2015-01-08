package interview.a9;

public class TopK {
    public static void main(String[] args) {
        int[] num = {3, 9, 15, 2, 11, 23, 3, 5, 18};
        new TopK().findTopK(num, 4);
    }
    
    public void findTopK(int[] num, int K) {
        assert num != null && K >= 1 && K <= num.length;
        
        int low = 0, high = num.length-1;
        while(low <= high) {
            int j = low;
            for(int i = low+1; i <= high; i++)
                if(num[i] > num[low])
                    swap(num, ++j, i);
            
            swap(num, low, j);
            if(j == K)
                break;
            else if(j < K)
                low = j+1;
            else
                high = j-1;   
        }
        
        for(int i = 0; i < K; i++)
            System.out.println(num[i]);
    }

    private void swap(int[] num, int i, int j) {
        if(i == j)
            return;
        
        num[i] ^= num[j];
        num[j] ^= num[i];
        num[i] ^= num[j];
    }
}