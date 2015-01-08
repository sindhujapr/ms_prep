package interview.daiziguizhong;

import cern.colt.Arrays;

/*
 * http://chuansongme.com/n/143952
 * 
 * See UglyNumbers
 */
public class NumberGenerator {
    public void generate(int m, int n, int cnt) {
        int res[] = new int[cnt];
        res[0] = 1;
        
        for(int i = 1, i1 = 0, i2 = 0; i < cnt; i++) {
            int v1 = m * res[i1], v2 = n * res[i2];
            res[i] = Math.min(v1, v2);
            
            // we shouldn't use if-else if. otherwise there will be duplicate
            if(res[i] == v1)
                ++i1;
            
            if(res[i] == v2)
                ++i2;
        }
        
        System.out.println(Arrays.toString(res));
    }
    
    public static void main(String[] args) {
        new NumberGenerator().generate(2, 5, 20);
    }
}