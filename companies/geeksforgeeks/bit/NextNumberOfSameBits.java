package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/next-higher-number-with-same-number-of-set-bits/
 */
public class NextNumberOfSameBits {
    public static void main(String[] args) {
        for(int i = 100; i < 200000; i++) {
            if(next2(i) != next3(i) || next2(i) != next1(i))
                System.out.println(i);
        }
    }
    
    public static int next1(int num) {
         assert num != 0;
         
         int rightOne;
         int nextHigherOneBit;
         int rightOnesPattern;

        // right most set bit
        rightOne = num & -num;

        // reset the pattern and set next higher bit
        // left part of x will be here
        nextHigherOneBit = num + rightOne;

        // nextHigherOneBit is now part [D] of the above explanation.
        // isolate the pattern
        rightOnesPattern = num ^ nextHigherOneBit;

        // right adjust pattern
        rightOnesPattern = (rightOnesPattern)/rightOne;

        // correction factor
        rightOnesPattern >>= 2;

        // rightOnesPattern is now part [A] of the above explanation.
        // integrate new pattern (Add [D] and [A])
        return nextHigherOneBit | rightOnesPattern;
    }

    public static int next2(int num) {
        // find the first non-zero bit from the right
        int i = 0;
        while(i < 32) {
            if((num & (1 << i)) != 0)
                break;
            i++;
        }

        if(i == 32) {
            System.out.println("no valid integer found");
            return -1;
        }
        
        int rightMost = i;
        while(i < 32 && (num & (1 << i)) != 0) {
            num ^= 1 << i;
            i++;
        }

        // set the (right) next bit of the left-most block of 1's 
        num |= 1 << i;
        for(int j = rightMost; j < i-1; j++)
            num |= 1 << (j-rightMost);

        return num;
    }

    public static String convertIntToBits(int num) {
        StringBuilder builder = new StringBuilder();
        while (num != 0) {
            if ((num & 0x1) == 1)
                builder.insert(0, '1');
            else
                builder.insert(0, '0');
            num >>>= 1;
        }
        return builder.toString();
    }
    
    // naive implementation for verification purpose
    public static int next3(int num) {
        int cnt = countBits(num);
        for(int i = num+1; i < Integer.MAX_VALUE; i++)
            if(countBits(i) == cnt)
                return i;
        
        return -1;
    }
    
    private static int countBits(int num) {
        int count = 0;
        while(num != 0) {
            num &= num-1;
            count++;
        }
        return count;
    }
}