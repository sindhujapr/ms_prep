package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * Dijkstra's algorithm doesn't work for graphs with negative weight edges.
 * For graphs with negative weight edges, Bellman-Ford algorithm can be used.
 * http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
 */
public class DijkstraShortestPath {
    private static int[][] vertices = { { -1, 4, -1, -1, -1, -1, -1, 8, -1 },
            { 4, -1, 8, -1, -1, -1, -1, 11, -1 }, { -1, 8, -1, 7, -1, 4, -1, -1, 2 },
            { -1, -1, 7, -1, 9, 14, -1, -1, -1 }, { -1, -1, -1, 9, -1, 10, -1, -1, -1 },
            { -1, -1, 4, 14, 10, -1, 2, -1, -1 }, { -1, -1, -1, -1, -1, 2, -1, 1, 6 },
            { 8, 11, -1, -1, -1, -1, 1, -1, 7 }, { -1, -1, 2, -1, -1, -1, 6, 7, -1 }, };

    public static void main(String[] args) throws InterruptedException {
        findShortest(vertices);
    }

    public static void findShortest(int[][] vertices) {
        int n = vertices.length;
        
        // used to track all the shorted paths
        Map<Integer, List<Integer>> paths = new TreeMap<Integer, List<Integer>>();

        int dist[] = new int[n];
        for (int i = 1; i < n; i++)
            dist[i] = -1;

        boolean[] spt = new boolean[n];
        int num = 0;
        while (num++ < n) {
            int minIndex = -1;
            int minDist = Integer.MAX_VALUE;
            // we can use heap to keep these distances
            for (int i = 0; i < n; i++) {
                if (!spt[i] && dist[i] >= 0 && dist[i] < minDist) {
                    minIndex = i;
                    minDist = dist[i];
                }
            }

            // The graph is disjoint
            if (minIndex == -1)
                break;

            // Identify vertex <minIndex> as finished
            spt[minIndex] = true;
            for (int j = 0; j < n; j++) {
                if (spt[j] || vertices[minIndex][j] == -1)
                    continue;

                if (dist[j] < 0 || dist[minIndex] + vertices[minIndex][j] < dist[j]) {
                    dist[j] = dist[minIndex] + vertices[minIndex][j];

                    /*
                     * every time we update the distance from a vertex to a visited vertex,
                     * we need to reset the path by copying the paths from the visited vertex,
                     * and then appending the visited vertex. 
                     */
                    List<Integer> path = null;
                    if(paths.containsKey(minIndex)) {
                        path = new ArrayList<Integer>(paths.get(minIndex));
                    } else {
                        path = new ArrayList<Integer>();
                        path.add(minIndex);
                    }

                    path.add(j);
                    paths.put(j, path);
                }
            }
        }

        System.out.println(Arrays.toString(dist));
        for(int key : paths.keySet())
            System.out.println(paths.get(key));
    }
}