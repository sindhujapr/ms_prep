package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/
 * 
 * Basic idea:
 * 1. If there is a shortest path between vertices u and v, then at most there are |V|-1
 *    vertices before reaching v. So we loop for |V|-1 times.
 * 2. After each above loop, we check each edge to see if we can relax it. So if we finish
 *    the Kth outer loop, dist[] keeps the shortest paths (from source) with at most
 *    K edges.
 */
public class BellmanFord {
	/*
	 * To implement this algorithm, it's better to represent the graph with adjacency list
	 */
	
	public static void main(String[] args) {
		System.out.println(Integer.valueOf(""));
	}
}