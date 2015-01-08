package lc;

public class FindPeakElement {
    public int findPeakElement(int[] num) {
        int a = 0, b = num.length-1;
        while(a <= b) {
            int m = (a+b) >> 1;
            
            if((m == a || num[m] > num[m-1]) && (m == b || num[m] > num[m+1]))
                return m;
            
            if(m > a && num[m] < num[m-1])
                b = m-1;
            else
                a = m+1;
        }
        return -1;
    }
}
