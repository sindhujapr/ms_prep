package interview.linkdin;

/*
 * http://www.glassdoor.com/Interview/Consider-an-X-x-Y-array-of-1-s-and-0s-The-X-axis-represents-influences-meaning-that-X-influences-Y-So-for-example-i-QTN_498161.htm
 * 
 * Refer to UniqCelebrity for a better solution.
 */
public class Influencer {
	//if vec[i][j] == 0 then i is not an influence
	//if vec[i][j] == 1 then j is not an influence
	// the time complexity seems still O(n*n)
	public boolean find_influences(boolean[][] vec) {
	    int n = vec.length;
	    boolean[] notInflu = new boolean[n];
	    for (int i = 0; i < n; ++i) {
	    	// skip non-influencer
	        if (notInflu[i])
	            continue;
	        
	        int j;
	        // here we skipped vec[i][i]
	        for (j = i + 1; j < n; ++j) {
	        	// i is not an influencer
	            if (!vec[i][j])
	                break;
	            else
	            	notInflu[j] = true;
	        }
	        
	        // it means i influences [i+1, n-1]
	        if (j == n) {
	            for (j = i - 1; j >= 0; --j) {
	                if (!vec[i][j])
	                    break;
	                    
	                notInflu[j] = true;
	            }
	            
	            if (j < 0) {
	            	boolean ret = true;
	            	for(int k = 0; k < n; k++) {
	            		if(k != i && vec[k][i]) {
	            			ret = false;
	            			break;
	            		}
	            	}
	            	if(ret)
	            		return true;
	            }
	        }

	        // if i is an influencer, it should have returned true above
	        notInflu[i] = true;
	    }
	    
	    return false;
	}
	
	public static void main(String[] args) {
		boolean[][] vec = {{false, false, false, true}, {true, true, true, true}, {true, false, true, true}, {false, false, true, false}};
		System.out.println(new Influencer().find_influences(vec));
	}
}