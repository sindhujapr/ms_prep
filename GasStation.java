package lc;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0, j = 0, sum = 0;
        while(i < n && j != i+n) {
            sum += gas[j%n] - cost[j%n];
            while(sum < 0 && i <= j && i < n) {
                sum -= gas[i] - cost[i];
                i++;
            }
            j++;
        }
        
        if(i < n)
            return i;
        else
            return -1;
    }
}