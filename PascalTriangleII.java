package lc2;

import java.util.ArrayList;

public class PascalTriangleII {
    public ArrayList<Integer> getRow(int rowIndex) {
        assert rowIndex >= 0;
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(1);
        int i = 0;
        while(i++ < rowIndex) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int j = 0; j < result.size(); j++) {
                temp.add((j > 0 ? result.get(j-1) : 0) + result.get(j));
            }
            temp.add(result.get(result.size()-1));
            result = temp;
        }
        
        return result;
    }
}