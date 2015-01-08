package lc;

import java.util.ArrayList;
import java.util.List;

public class JumpGameII {
    public static void main(String[] args) {
        JumpGameII instance = new JumpGameII();
        System.out.println(instance.jump(new int[] {1, 2}));
    }

    /*
     * http://blog.unieagle.net/2012/09/29/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Ajump-game-ii%EF%BC%8C%E4%B8%80%E7%BB%B4%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/
     * see also http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
     */
    public int jump(int[] A) {
        assert A != null && A.length > 0;
        
        int min = 0;
        int low = 0, high = 0;
        if(A.length == 1)
            return 0;
        while(low <= high) {
            min++;
            int next = high;
            for(int i = low; i <= next; i++) {
                int dist = i + A[i];
                if(dist >= A.length-1)
                    return min;
                
                if(dist > high)
                    high = dist;
            }
            low = next+1;
        }
        
        return min;
    }
    
    /*
     * doesn't pass large judge
     * http://gongxuns.blogspot.com/2012/12/leetcodejump-game-ii.html
     */
    public int jump1(int[] A) {
        assert A != null && A.length > 0;
        
        int res = 0;
        boolean[] used = new boolean[A.length];
        List<Integer> toVisit = new ArrayList<Integer>();
        toVisit.add(A.length-1);
        
        while(toVisit.size() > 0) {
            List<Integer> list = new ArrayList<Integer>();
            
            for(Integer index : toVisit) {
                if(index == 0)
                    return res;
                
                for(int i = 0; i < A.length; i++) {
                    if(i != index && !used[i] && i+A[i] >= index)
                        list.add(i);
                }
                used[index] = true;
            }
            
            toVisit = list;
            res++;
        }
        
        return res;
    }
}
