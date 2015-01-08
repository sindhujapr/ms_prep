package lc;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    public static void main(String[] args) {
        System.out.println(new PermutationSequence().getPermutation(4, 3));
    }
    
    public String getPermutation(int n, int k) {
        assert k > 0 && k <= f(n);
 
        k--;
        List<Character> list = new ArrayList<Character>();
        for(int i = 0; i < n; i++)
            list.add((char)(i+1 + '0'));

        StringBuilder sb = new StringBuilder();
        for(int i = n; i > 0; i--) {
            int cnt = f(i-1);
            int index = k / cnt;
            k -= index * cnt;
            sb.append(list.remove(index));
        }
        return sb.toString();
    }
    
    private int f(int n) {
        int result = 1;
        for(int i = 1; i <= n; i++)
            result *= i;
        return result;
    }
}