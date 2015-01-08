package dynamic.programming;

import java.util.Arrays;

/*
 * http://blog.csdn.net/v_JULY_v/article/details/6110269
 * dynamic programming. there should be other approaches
 */

enum decreaseDir {
    kInit, kLeft, kUp, kLeftUp
};

public class LCS {
    private char[] pStr1 = null;
    private char[] pStr2 = null;
    public LCS(char[] str1, char[] str2) {
    pStr1 = str1;
    pStr2 = str2;
    }

    public int longestCommonString() {
    if (pStr1 == null || pStr2 == null)
        return 0;

    int length1 = pStr1.length;
    int length2 = pStr2.length;
    if (length1 == 0 || length2 == 0)
        return 0;

    int[][] lcs_matrix = new int[length1][];
    int[][] lcs_direction = new int[length1][];

    for (int i = 0; i < length1; ++i) {
        lcs_matrix[i] = new int[length2];
        lcs_direction[i] = new int[length2];
        for (int j = 0; j < length2; ++j) {
        lcs_matrix[i][j] = 0;
        lcs_direction[i][j] = decreaseDir.kInit.ordinal();
        }
    }

    for (int i = 0; i < length1; ++i) {
        for (int j = 0; j < length2; ++j) {
        if (i == 0 || j == 0) {
            if (pStr1[i] == pStr2[j]) {
            lcs_matrix[i][j] = 1;
            lcs_direction[i][j] = decreaseDir.kLeftUp.ordinal();
            } else {
            if (i > 0) {
                lcs_matrix[i][j] = lcs_matrix[i-1][j];
                lcs_direction[i][j] = decreaseDir.kUp.ordinal();
            }
            if (j > 0) {
                lcs_matrix[i][j] = lcs_matrix[i][j-1];
                lcs_direction[i][j] = decreaseDir.kLeft.ordinal();
            }
            }
        } else if (pStr1[i] == pStr2[j]) {
            // common char from the left up entry
            lcs_matrix[i][j] = lcs_matrix[i-1][j-1] + 1;
            lcs_direction[i][j] = decreaseDir.kLeftUp.ordinal();
        } else if (lcs_matrix[i-1][j] > lcs_matrix[i][j-1]) {
            // it comes from the up entry in the direction matrix
            lcs_matrix[i][j] = lcs_matrix[i-1][j];
            lcs_direction[i][j] = decreaseDir.kUp.ordinal();
        } else {
            // it comes from the left entry in the direction matrix
            lcs_matrix[i][j] = lcs_matrix[i][j-1];
            lcs_direction[i][j] = decreaseDir.kLeft.ordinal();
        }
        }
    }
    
    System.out.println(Arrays.deepToString(lcs_matrix));
    System.out.println(Arrays.deepToString(lcs_direction));

    LCS_Print(lcs_direction, length1-1, length2-1);
    return lcs_matrix[length1-1][length2-1];
    }

    void LCS_Print(int[][] LCS_direction, int row, int col) {
    int length1 = pStr1.length;
    int length2 = pStr2.length;

    if (length1 == 0 || length2 == 0 || !(row < length1 && col < length2))
        return;

    if (LCS_direction[row][col] == decreaseDir.kLeftUp.ordinal()) {
        if (row > 0 && col > 0)
        LCS_Print(LCS_direction, row-1, col-1);
        System.out.print(pStr1[row]);
    } else if (LCS_direction[row][col] == decreaseDir.kLeft.ordinal()) {
        LCS_Print(LCS_direction, row, col-1);
    } else if (LCS_direction[row][col] == decreaseDir.kUp.ordinal()) {
        LCS_Print(LCS_direction, row-1, col);
    }
    }

    public static void main(String[] args) {
//  LCS instance = new LCS("abcdexf".toCharArray(), "acdef".toCharArray());
    LCS instance = new LCS("ade".toCharArray(), "acbde".toCharArray());
    instance.longestCommonString();
    }
}