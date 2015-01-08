package lc;

/*
 * http://qiang129.blogspot.com/2013/05/leetcode-unique-binary-search-trees.html
 */
public class UniqueBinarySearchTree {
	/*
	 * see the first answer from below discussion:
	 * http://discuss.leetcode.com/questions/270/unique-binary-search-trees
	 */
	public int numTrees1(int n) {
	    int c = 1;
	    for (int i = 2; i <= n; i++)
	        c = 2*(2*i-1)*c/(i+1);
	    return c;
	}
	
    public int numTrees(int n) {
        int[] sum = new int[n+1];
        sum[0] = 1;
        sum[1] = 1;
        
        for(int i = 2; i <= n; i++) {
            for(int left = 0; left < i; left++) {
                int right = i - left -1;
                sum[i] += sum[left] * sum[right];
            }
        }
        
        return sum[n];
    }
}
