package careercup.fb;

/*
 * http://www.careercup.com/question?id=5978392075698176
` */
public class TravelIslands {
    public static void main(String[] args) {
        /*
         * -1 denotes no edge
         * otherwise denotes the weight of the edge
         */
        int[][] islands = {{1, 4, 2}, {1, 3, 5}, {1, 4, -1}};
        boolean[] toDeliver = {true, true};
        boolean[] danger = { false, true, false };
        travelIslands(islands, danger, toDeliver);
    }
    
    /*
     * greedy algorithm
     */
    public static void travelIslands(int[][] islands, boolean[] danger, boolean[] toDeliver) {
        /*
         * we need to keep track of the minimum distance for each island
         * Initially, all distances are Integer.MAX_VALUE. At each step,
         * we try to find a mininum-distance island and identify it as
         * "visited" and then update all adjacent islands. The trick is,
         * we are not seeking the shortest sum distance from the single
         * source.
         */
        int[] dist = new int[islands.length];
        for(int i = 0; i < dist.length; i++)
            dist[i] = Integer.MAX_VALUE;
        
        boolean isVisited[] = new boolean[islands.length];
        
        // suppose we start at island 0
        dist[0] = 0;
        isVisited[0] = true;
        /* 
         * we should loop until no next island is available.
         */
        for(int i = 0; i < islands.length; i++) {
            int minIndex = findNext(dist, danger, isVisited);
            if(minIndex == -1) {
                    int sum = 0;
                    for(int j = 0; j < dist.length; j++) {
                        if(toDeliver[j] && dist[j] < Integer.MAX_VALUE) {
                            sum += dist[j];
                        } else if(toDeliver[j] && dist[j] == Integer.MAX_VALUE) {
                            System.out.println("NOT ALL ISLANDS ARE REACHABLE");
                            return;
                        }
                    }
                    System.out.println("shortest sum distance: " + sum);
                    return;
            }
            
            isVisited[minIndex] = true;
            for(int j = 0; j < islands.length; j++) {
                if(islands[i][j] > 0 && !danger[j])
                    /*
                     * here is the problem:
                     * if two islands are in different branches separated from the same parent 
                     * island, how do we count the return path?
                     */
                    dist[j] = Math.min(dist[j], islands[i][j]);
            }
        }
    }
    
    private static int findNext(int[] dist, boolean danger[], boolean[] isVisited) {
        int minIndex = -1;
        for(int i = 0; i < dist.length; i++)
            if(dist[i] != Integer.MAX_VALUE && !danger[i] && !isVisited[i] && (minIndex == -1 || dist[i] < dist[minIndex]))
                minIndex = i;
        return minIndex;
    }
}