package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-32-word-break-problem/
 */
public class WordBreak {
	public static void main(String[] args) {
		String dictionary[] = {"mobile","samsung","sam","sung","man","mango", "icecream","and","go","i","like","ice","cream"};
		String input = "ilikesamsung";
		System.out.println(parse(input, dictionary));
	}
    
	private static boolean contains(String[] words, String str) {
		for(String word : words)
			if(word.equals(str))
				return true;
		
		return false;
	}
	
	public static boolean parse(String input, String[] dict) {
		int n = input.length();
		// start end i <= j
		int[][] flags = new int[n][n+1];
		for(int i = 0; i < n; i++)
			flags[i][i] = 1;
		
		for(int i = 1; i < input.length()+1; i++) {
			for(int j = 0; j <= i; j++) {
				if(flags[0][j] == 0)
					continue;
				
				for(int k = j+1; k <= i; k++)  {
					String word = input.substring(j, k);
					if(flags[j][k] == 0 && contains(dict, word)) {
						flags[j][k] = 1;
						if(flags[0][j] == 1)
							flags[0][k] = 1;
					}
				}
			}
		}
		return flags[0][n] == 1;
	}
}