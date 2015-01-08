package careercup.fb;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.careercup.com/question?id=5922416572235776
 */
public class PrintGraphPaths {
    public static void main(String[] args) {
        boolean[][] connected = {{false, true, false, true}, {true, false, true, true}, {false, true, false, true}, {true, true, true, false}};
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
//      findPaths(connected, result);
        findPaths_iterative(connected, result);
        System.out.println(result.size() + " different paths:");
        for(List<Integer> path : result)
            System.out.println(path);
    }
    
    public static void findPaths(boolean[][] connected, List<List<Integer>> result) {
        List<Integer> existing = new ArrayList<Integer>();
        
        // the path may start from any vertex
        for(int i = 0; i < connected.length; i++) {
            existing.add(i);
            findPaths(connected, existing, result);
            existing.remove(existing.size()-1);
        }
        
    }
    
    /**
     * @param connected each element denotes whether two nodes are connected
     * @param existing existing edges that have been added
     */
    private static void findPaths(boolean[][] connected, List<Integer> existing, List<List<Integer>> result) {
        // get the last vertex that has been visited
        int k = existing.get(existing.size()-1);
        List<Integer> next = new ArrayList<Integer>();
        for(int i = 0; i < connected.length; i++) {
            if(connected[k][i] && !existing.contains(i))
                next.add(i);

        }
        
        if(next.size() == 0) {
            result.add(new ArrayList<Integer>(existing));
            return;
        }
        
        for(Integer vertex : next) {
            existing.add(vertex);
            findPaths(connected, existing, result);
            existing.remove(existing.size()-1);
        }
    }
    
    private static void findPaths_iterative(boolean[][] connected, List<List<Integer>> result) {
        assert connected != null && connected.length > 0;
        
        
        List<Integer> visited = new ArrayList<Integer>();
        for(int i = 0; i < connected.length; i++) {
            visited.add(i);
            findPaths_iterative(connected, visited, result);
            visited.remove(visited.size()-1);
        }
    }
    
    private static void findPaths_iterative(boolean[][] connected, List<Integer> visited, List<List<Integer>> result) {
        int next = findNext(connected, visited);
        do {
            boolean isAdded = false;
            while(next != -1) {
                visited.add(next);
                isAdded = true;
                next = findNext(connected, visited);
            }
            
            // we don't keep partial path
            if(isAdded)
                result.add(new ArrayList<Integer>(visited));
            
            int j = visited.remove(visited.size()-1);
            int k = visited.get(visited.size()-1);
            int i;
            // Next selected elements should be greater than the current one
            for(i = j+1; i < connected.length; i++) {
                if(connected[k][i] && !visited.contains(i) && i > j) {
                    next = i;
                    break;
                }
            }
        } while(visited.size() > 1 || next != -1);
    }
    
    private static int findNext(boolean[][] connected, List<Integer> visited) {
        int k = visited.get(visited.size()-1);
        for(int i = 0; i < connected.length; i++) {
            if(connected[k][i] && !visited.contains(i))
                return i;
            
        }
        return -1;
    }
}