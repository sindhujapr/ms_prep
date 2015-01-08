package careercup.fb;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.careercup.com/question?id=6193320829124608
 * doesn't allow cycle
 */
public class DistinctPaths {
    public static void main(String[] args) {
        boolean[][] connected = {{false, true, false, true}, {true, false, true, true}, {false, true, false, true}, {true, true, true, false}};
        
        List<List<Integer>> result = findPaths(connected, 0, 3);
        System.out.println(result.size() + " different paths:");
        for(List<Integer> path : result)
            System.out.println(path);
        
        result = findPaths_iterative(connected, 0, 3);
        System.out.println(result.size() + " different paths:");
        for(List<Integer> path : result)
            System.out.println(path);
    }
    
    public static List<List<Integer>> findPaths(boolean[][] connected, int source, int destination) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        List<Integer> visited = new ArrayList<Integer>();
        visited.add(source);
        findPaths(connected, result, visited, source, destination);
        return result;
    }

    private static void findPaths(boolean[][] connected, List<List<Integer>> result, List<Integer> visited,
            int source, int target) {
        int k = visited.get(visited.size()-1);
        for(int i = 0; i < connected.length; i++) {
            if (visited.contains(i) || !connected[k][i])
                continue;
        
            visited.add(i);
            if(i == target) {
                result.add(new ArrayList<Integer>(visited));
                visited.remove(visited.size()-1);
                continue;
            }

            findPaths(connected, result, visited, source, target);
            visited.remove(visited.size()-1);
        }
    }
    
    public static List<List<Integer>> findPaths_iterative(boolean[][] connected, int source, int target) {
        assert source >= 0 && source < connected.length;
        assert target >= 0 && target < connected.length;
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        List<Integer> visited = new ArrayList<Integer>();
        visited.add(source);
        int next = findNext(connected, visited, source);
        do {
            while(next != -1) {
                visited.add(next);
                if(next == target)
                    break;
                next = findNext(connected, visited, next);
            }
            
            if(next == target) {
                result.add(new ArrayList<Integer>(visited));
                /*
                 *  this is a must because:
                 *  1. in the above loop, we terminate upon not only next = -1, but also upon next = target
                 *  2. in the outer loop, we rely on the value of next != -1, but next may not be overridden 
                 *  in the below for loop
                 */
                next = -1;
            }
            
            int j = visited.remove(visited.size()-1);
            int i = visited.get(visited.size()-1);
            // starting from the next vertex of the previous last vertex
            for(int k = j+1; k < connected.length; k++) {
                if(connected[i][k] && !visited.contains(k)) {
                    next = k;
                    break;
                }
            }
        } while(visited.size() > 1 || next != -1);
        
        return result;
    }

    private static int findNext(boolean[][] connected, List<Integer> visited, int source) {
        for(int i = 0; i < connected.length; i++)
            if(!visited.contains(i) && connected[source][i])
                return i;
        
        return -1;
    }
}