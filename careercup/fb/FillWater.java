package careercup.fb;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.careercup.com/question?id=9820788
 */
public class FillWater {
    public static void main(String[] args) {
        System.out.println(fill(20, 100, 6));
    }
    
    public static double fill(double C, double L, int M) {
        assert M > 0;

        if(M == 1)
            return Math.min(C, L);

        List<Double> list = new ArrayList<Double>();
        list.add(L);

        int n = M-1;
        while(n > 0) {
            List<Double> temp = new ArrayList<Double>();

            // allocate water 
            temp.add((list.get(0) > C ? list.get(0)-C : 0.)/2);
            for(int i = 0; i < list.size()-1; i++) {
                double left = Math.max(list.get(i)-C, 0.)/2;
                double right = Math.max(list.get(i+1)-C, 0.)/2;
                temp.add(left + right);
            }
            temp.add((list.get(list.size()-1) > C ? list.get(list.size()-1)-C : 0.)/2);
            
            n -= temp.size();
            list = temp;
        }
        
        return Math.min(C, list.get(n+list.size()-1));
    }
}