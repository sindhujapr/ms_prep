package lc2;

import java.util.ArrayList;

public class PascalTriangle {
	/*
	 * the rule is different from Pascal's Triangle II, which requires output <1, 3, 3, 1> for 3.
	 * but for this problem, it requires output <1, 3, 3, 1> for 4.
	 */
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        assert numRows >= 0;
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(numRows == 0)
            return result;

        ArrayList<Integer> one = new ArrayList<Integer>();
        one.add(1);
        result.add(one);
        
        int i = 1;
        while(i++ < numRows) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            ArrayList<Integer> last = result.get(result.size()-1);
            for(int j = 0; j < last.size(); j++) {
                temp.add((j > 0 ? last.get(j-1) : 0) + last.get(j));
            }
            temp.add(last.get(last.size()-1));
            result.add(temp);
        }
        return result;
    }
}