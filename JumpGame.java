package lc;

public class JumpGame {
	// efficient regarding space complexity
    public boolean canJump(int[] A) {
        assert A != null && A.length >= 1;

        int low = 0, high = 0;
        while(low <= high) {
            int next = high+1;
            for(int i = low; i <= high; i++) {
                if(i+A[i] >= A.length-1)
                    return true;
                
                if(i+A[i] > high)
                    high = i+A[i];
            }
            
            low = next;
        }
        
        return false;
    }
    
    public boolean canJump2(int[] A) {
        assert A != null && A.length >= 1;

        boolean[] reachable = new boolean[A.length];
        reachable[0] = true;
        
        int max = 0;
        for(int i = 0; i < A.length; i++) {
            if(!reachable[i])
                return false;

            if(i+A[i] >= max) {
                if(i+A[i] >= A.length-1)
                    return true;
        
                for(int j = max+1; j <= i+A[i]; j++)
                    reachable[j] = true;

                max = i+A[i];
            }
        }

        return false;
    }
}