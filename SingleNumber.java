package lc;

public class SingleNumber {
    public int singleNumber(int[] A) {
        assert A != null;
        
        int res = 0;
        
        for(int i = 0; i < 32; i++) {
            int cnt = 0;
            for(int j = 0; j < A.length; j++)
                cnt += (A[j] & (1 << i)) != 0 ? 1 : 0;
            
            if(cnt % 2 == 1)
                res |= (1 << i);
        }

        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(new SingleNumber().singleNumber(new int[] {-1}));
    }
}