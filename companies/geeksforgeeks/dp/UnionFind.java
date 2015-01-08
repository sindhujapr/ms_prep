package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/union-find/
 * 
 * Basic idea:
 * An array parent[] is maintained to keep the root or direct parent of each vertex.
 * For each edge, we check if the two vertices (src and dest) have the same root.
 * If not, then we set parent[dest] = parent[src] or parent[dest] = src. This is the
 * union operation. 
 */
public class UnionFind {
    
}