package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/
 * 
 * Basic idea: For the two inner loops, we try to get to the shortest distance by way of the 
 * vertex given in the outer loop.
 * 
 * Let's suppose we have vertices K0, K1, ..., Kn. After the first outer loop, we have the
 * shortest distances kept in dist[i][j] by way of K0, including the shortest distance
 * dist[i][K1], which is min(dist[i][K1], dist[i][K0]+dist[K0][K1]). For the second outer loop, 
 * we try to find the shortest distances by way of K1. Since we already have the shortest
 * distance dist[i][K1] so far, we can find the shortest distance dist[i][j]:
 * min(dist[i][j], dist[i][K1]+dist[K1][j])
 */
public class FloydWarshall {
	// pay attention to the loop sequence!
}