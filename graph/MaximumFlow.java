/*
 * Ford-Fulkerson algorithm:
 * https://www.youtube.com/watch?v=7jFoyLk2VjM
 * http://www.sce.carleton.ca/faculty/chinneck/po/Chapter9.pdf
 * https://www.youtube.com/results?search_query=maximal+flow
 * See here for time complexity http://en.wikipedia.org/wiki/Ford–Fulkerson_algorithm
 * 
 * an interesting application: http://www.geeksforgeeks.org/maximum-bipartite-matching/
 *
 * http://blog.csdn.net/smartxxyx/article/details/9293805
 * http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=maxFlowRevisited#1
 * FordFulkerson
 * Another algorithm for maximum flow: edmondsKarp
 */
public class FordFulkerson {
    private int residualNetwork[][] = null;
    private int flowNetwork[][] = null;

    public final int N;
    int parent[];
    public FordFulkerson(int N) {
        this.N = N;
        parent = new int[N];
    }

    public int edmondsKarpMaxFlow(int graph[][], int s, int t) {
        int length = graph.length;
        int f[][] = new int[length][length];
        int r[][] = residualNetwork(graph, f);
        int result = augmentPath(r, s, t);

        int sum = 0;
        while(result != -1) {
            int cur = t;
            while(cur != s) {
                f[parent[cur]][cur] += result;
                f[cur][parent[cur]] = -f[parent[cur]][cur];
                r[parent[cur]][cur] -= result;
                r[cur][parent[cur]] += result;
                cur = parent[cur];
            }

            sum+ = result;
            result = augmentPath(r, s, t);
        }

        residualNetwork = r;
        flowNetwork = f;

        return sum;
    }

    // deep copy
    private int[][] residualNetwork(int c[][], int f[][]) {
        int length = c.length;
        int r[][] = new int[length][length];
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                r[i][j] = c[i][j] - f[i][j];
            }
        }

        return r;
    }

    // BFS, searching for argumenting path (shortest argumenting path)
    public int augmentPath(int graph[][], int s, int t) {
        int maxflow = Integer.MAX_VALUE;
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        parent[s] = s;

        while(!queue.isEmpty()) {
            int p = queue.poll();
            if(p == t)
                break;
            for(int i = 0; i < graph.length; i++) {
                if(i != p && parent[i] == -1 && graph[p][i] > 0) {
                    if(maxflow > graph[p][i])
                        maxflow = graph[p][i];
                    //flow[i] = Math.min(flow[p], graph[p][i]);
                    parent[i] = p;
                    queue.add(i);
                }
            }
        }
        if(parent[t] == -1)
            return -1;
        return  maxflow;
    }
}
