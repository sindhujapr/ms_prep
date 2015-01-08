package greedy;

/*
 * http://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-minimum-spanning-tree-mst-2/
 * 
 * This is a typical greedy algorithm:
 * every time we pick the edge with the minimum weight. After picking that weight, we will
 * need to update the remaining edges with the new visited vertex. For each remaining vertex,
 * maintain a value that reflects the shortest path (minimum weight) connecting to the visited vertices.
 * 
 * This definitely cannot fit into dynamic problem, which requires two characteristics: optimal
 * substructure and overlapping subproblem. This problem doesn't have the latter characteristic.
 * 
 * implementation difference between MST algorithm and Dijkstra's algorithm:
 * MST focuses on constructing a tree with minimum total weight, while Dijkstra focuses on find
 * the shortest paths from one single source to all other vertices. Thus MST always selects the 
 * edge with minimum weight, while Dijkstra always selects the vertex that is closest to the source.
 * Due to this difference, MST maintains an array that keeps minimum weights from remaining vertices
 * to visited vertices, and Dijkstra maintains an array that keeps minimum distances from remaining
 * vertices to the source. After picking one shortest edge, both algorithms need to update the two 
 * arrays, respectively, to reflect to most up-to-date status for the next pick.
 * 
 */
public class PrimeMST {
    private static int[][] graph = 
        {{0, 2, 0, 6, 0},
        {2, 0, 3, 8, 5},
        {0, 3, 0, 0, 7},
        {6, 8, 0, 0, 9},
        {0, 5, 7, 9, 0},
       };
    
    public static void main(String[] args) {
        prime(graph);
    }
    
    public static void prime(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        
        // should be big enough to hold all edges
        int dist[] = new int[n];
        dist[0] = 0;
        for(int i = 1; i < n; i++)
            dist[i] = -1;   // INFINITE
        
        for(int i = 0; i < n; i++) {
            int minIndex = -1;
            int minDist = Integer.MAX_VALUE;
            for(int j = 0; j < n; j++) {
                if(!visited[j] && dist[j] >= 0 && dist[j] < minDist) {
                    minIndex = j;
                    minDist = dist[j];
                }
            }
            
            // No edge with minimum weight found
            if(minIndex == -1)
                break;
            
            visited[minIndex] = true;

            for(int j = 0; j < n; j++) {
                /*
                 * be careful about the if clause, we can only update parent[j] if key[j] is set to graph[minIndex][j].
                 * thus we should better put the conditions in the "if" but not use Math.min().
                 */
                if(!visited[j] && graph[minIndex][j] > 0 && (dist[j] > graph[minIndex][j] || dist[j] < 0)) {
                    // key[j] may already have been set a value
                    dist[j] =  graph[minIndex][j];
                    parent[j] = minIndex;
                }
            }
        }
        
        // vertex 0 doesn't have parent (default to 0 itself) since it's the starting point.
        for(int i = 1; i < n; i++)
            System.out.println(parent[i] + "\t" + i);
    }
}