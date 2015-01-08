package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/find-whether-a-given-number-is-a-power-of-4-or-not/
 */
public class PowerOf4 {
    public static void main(String[] args) {
        System.out.println(isPowerofFour(64) + "\t" + isPowerofFour2(64));
    }
    
    public static boolean isPowerofFour(int num) {
        while(Math.abs(num) >= 4)
            num >>= 2;
            
        return num == 1;
    }
    
    public static boolean isPowerofFour2(int num) {
        int count = 0;
        if(num != 0 && (num & (num-1)) == 0) {
            while(num > 1) {
                num >>= 1;
                count++;
            }
            
            return (count%2 == 0) ? true : false;
        }
        
        return false;
    }
}