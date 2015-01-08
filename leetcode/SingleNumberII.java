package lc;

public class SingleNumberII {
    public int singleNumber(int[] A) {
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int cnt = 0;
            for(int value : A)
                cnt += (value & (1 << i)) != 0 ? 1 : 0;
                    
            if(cnt % 3 == 1)
                res |= (1 << i);
        }

        return res;        
    }
    
    public int singleNumber2(int[] A) {
        int one = 0, two = 0;
        for(int value : A) {
            two |= one & value;
            one ^= value;
            
            int mask = ~(one & two);
            one &= mask;
            two &= mask;
        }
        
        return one;
    }
}