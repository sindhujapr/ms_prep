package dynamic.programming;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

class Matrix {
    private final int row;
    private final int column;
    public Matrix(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public int getRow() { return row; }
    public int getColumn() { return column; }
    @Override public String toString() {
        return "[" + row + ", " + column + "]";
    }
}

class Pair implements Comparable<Pair> {
    Integer index1;
    Integer index2;
    public Pair(Integer index1, Integer index2) {
        this.index1 = index1;
        this.index2 = index2;
    }
    
    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Pair))
            return false;
        
        Pair pair = (Pair) object;
        return pair.index1 == this.index1 && pair.index2 == this.index2;
    }
    
    @Override
    public int hashCode() {
        int value = 17;
        value = 31 * value + index1 + index2;
        
        return value;
    }

    @Override
    public String toString() {
        return "(" + index1 + ", " + index2 + ")";
    }

    @Override
    public int compareTo(Pair o) {
        if(index1 < o.index1)
            return -1;
        else if(index1 > o.index1)
            return 1;
        else {
            if(index2 < o.index2)
                return -1;
            else if(index2 > o.index2)
                return 1;
            else
                return 0;
        }
    }
}

public class MatrixChainMultiplication {
    private static final int count = 6;

    public static void main(String[] args) {
        Matrix[] matrices = new Matrix[count];
        // P0 = Matrix[0].getRow()
        // P1 = Matrix[0].getColumn() or P1 = Matrix[1].getRow()
        // ...
        // Pn = Matrix[n].getRow()
        // Pn+1 = Matrix[n].getColumn()
        
        Random rand = new Random();
        int prevColumn = 0;
        for (int i = 0; i < count; i++) {
            // make sure the row is greater than 0 and equals to the previous column
            int row = prevColumn;
            while(row == 0)
                row = rand.nextInt(count);

            int column;
            while((column = rand.nextInt(count)) == 0);
            
            Matrix m = new Matrix(row, column);
            matrices[i] = m;
            prevColumn = column;
        }
        
        matrices[0] = new Matrix(30, 35);
        matrices[1] = new Matrix(35, 15);
        matrices[2] = new Matrix(15, 5);
        matrices[3] = new Matrix(5, 10);
        matrices[4] = new Matrix(10, 20);
        matrices[5] = new Matrix(20, 25);

        Map<Pair, Integer> map = new TreeMap<Pair, Integer>();
        for (int i = 0; i < count; i++) {
            map.put(new Pair(i, i), 0);
        }

        for (int l = 1; l < count; l++) {
            for (int i = 0; i < count-l+1; i++) {
                int j = i + l - 1;
                map.put(new Pair(i, j), 99999999);
                
                for (int k = i; k < j-1; k++) {
                    int q = map.get(new Pair(i, k)) + 
                            map.get(new Pair(k+1, j)) +
                            matrices[i].getRow() * matrices[k].getRow() * matrices[j].getColumn(); 
                    if(q < map.get(new Pair(i, j))) {
                        map.put(new Pair(i, j), q);
                    }
                }
            }
        }
        
        System.out.println(map.get(new Pair(1, 4)));
        for (int i = 0; i < count; i++) {
            System.out.println(mini(map, matrices, i, count-1));
        }
    }
    
    // Incomplete implementation
    public static int mini(Map<Pair, Integer> map, Matrix[] matrices, int i, int j) {
        int max = 99999999;

        Pair pair = new Pair(i, j);
        if(map.get(pair) != null && map.get(pair) < max)
            return map.get(pair);
        
        if(i == j) {
            map.put(pair, 0);
            return 0;
        } else if(i == j-1) {
            int row1 = matrices[i].getRow();
            int column1 = matrices[i].getColumn();
            int column2 = matrices[j].getColumn();
            int result = row1 * column1 * column2;
            map.put(pair, result);
            return result;
        } else {
            int min = max;
            for (int k = i; k < j; k++) {
                int result = mini(map, matrices, i, k);
                result += mini(map, matrices, k+1, j);
                result += matrices[i].getRow() * matrices[k].getColumn() * matrices[j].getColumn();
                if(result < min) {
                    min = result;
                    map.put(pair, result);
                }
            }
            return min;
        }
    }
}
