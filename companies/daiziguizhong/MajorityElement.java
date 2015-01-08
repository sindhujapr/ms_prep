package interview.daiziguizhong;

/*
 * http://chuansongme.com/n/95556
 */
public class MajorityElement {
    public int findMajority(int[] arr) {
        assert arr.length > 0;
        
        int index = 0;
        for(int cnt = 1, val = arr[0], i = 1; i < arr.length; i++) {
            cnt += val == arr[i] ? 1 : -1;
            
            if(cnt == 0) {
                val = arr[i];
                cnt = 1;
                index = i;
            }
        }
        
        int cnt = 0;
        for(int val : arr)
            cnt += val == arr[index] ? 1 : 0;
        
        return cnt > arr.length/2 ? arr[index] : Integer.MIN_VALUE;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 1, 2, 1, 1};
        System.out.println(new MajorityElement().findMajority(arr));
    }
}