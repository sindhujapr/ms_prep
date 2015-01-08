package lc;

public class ZigZagConversion {
    public static void main(String[] args) {
        ZigZagConversion instance = new ZigZagConversion();
        System.out.println(instance.convert("", 1));
    }
    
    public String convert(String s, int nRows) {
        if(nRows == 1)
            return s;
   
        int num = s.length()/(nRows+nRows-2) + 1;
        int nCols = num * (1+nRows-2);
        int[][] matrix = new int[nRows][nCols];
        int len = s.length();
        
        int rIndex = 0;
        int cIndex = 0;
        int index = 0;
        while(index < len) {
            while(index < len && rIndex < nRows) {
                matrix[rIndex][cIndex] = s.charAt(index);
                index++;
                rIndex++;
            }
            
            rIndex -= 2;
            cIndex++;
            while(index < len && rIndex >= 1) {
                matrix[rIndex--][cIndex++] = s.charAt(index);
                index++;
            }
            
            rIndex = 0;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nRows; i++)
            for(int j = 0; j < nCols; j++)
                if(matrix[i][j] != 0)
                    sb.append((char)matrix[i][j]);
        return sb.toString();
    }
}