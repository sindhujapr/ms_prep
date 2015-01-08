package interview.ms;

/*
 * from http://chuansongme.com/n/290160
 *  Given an array [a1, a2, ..., an, b1, b2, ..., bn], 
 *  transform it to [a1, b1, a2, b2, ..., an, bn].
 */
public class InterleaveString {
    public void transform(int[] num) {
        transform(num, 0, num.length-1);
    }
    
    public void transform(int[] num, int start, int end) {
        if(end < start+3)
            return;
        
        int mid = (start+end) >> 1;
        int start1 = ((start+mid) >> 1) + 1;
        int start2 = mid+1;
        int len = start1-start;
        shift(num, start1, start2, len);
        
        transform(num, start, mid);
//      reverse(num, )
    }
    
    private void shift(int[] num, int s1, int s2, int len) {
        
    }
}