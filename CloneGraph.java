package careercup.fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CloneGraph {
	static class UndirectedGraphNode {
		int label;
		ArrayList<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	};

	/*
	 * reference http://leetcode.com/2012/05/clone-graph-part-i.html
	 * adapted from gongxun code. This implementation is more concise than the
	 * below one. Basic idea: for each node that hasn't been cloned, clone it
	 * and its neighbors, then add all the edges and mark this node as complete,
	 * also add the neighbors that haven't been processed into uncompleted list so
	 * that we can process them later.
	 */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)
            return null;
            
        Set<Integer> completed = new HashSet<Integer>();
        Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
        
        List<UndirectedGraphNode> uncompleted = new ArrayList<UndirectedGraphNode>();
        uncompleted.add(node);
        
        while(uncompleted.size() > 0) {
            UndirectedGraphNode curr = uncompleted.remove(0);
            if(!map.containsKey(curr.label))
                map.put(curr.label, new UndirectedGraphNode(curr.label));
            
            map.get(curr.label).neighbors = new ArrayList<UndirectedGraphNode>();
            for(UndirectedGraphNode neighbor : curr.neighbors) {
                if(!map.containsKey(neighbor.label))
                    map.put(neighbor.label, new UndirectedGraphNode(neighbor.label));

                map.get(curr.label).neighbors.add(map.get(neighbor.label));
                
                if(!completed.contains(neighbor.label))
                    uncompleted.add(neighbor);
            }
            
            completed.add(curr.label);
        }
        
        return map.get(node.label);
    }

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return null;
			
		Map<Integer, UndirectedGraphNode> visited = new HashMap<Integer, UndirectedGraphNode>();
		Set<Integer> completed = new HashSet<Integer>();
		List<UndirectedGraphNode> uncompleted = new ArrayList<UndirectedGraphNode>();
		uncompleted.add(node);
		int start_node_label = node.label;

		while (uncompleted.size() > 0) {
			// each time we only process one node in the stack
			UndirectedGraphNode curr = uncompleted.remove(uncompleted.size() - 1);

			// skip nodes that we have finished processing
			if (completed.contains(curr.label))
				continue;

			UndirectedGraphNode copy = visited.get(curr.label);
			if (copy == null) {
				copy = new UndirectedGraphNode(curr.label);
				visited.put(copy.label, copy);
			}

			/*
			 * within each "while" loop, though we may create multiple nodes, we only mark
			 * one node as "completed" since only that node has all its neighbors ready.
			 * All other nodes are only created and kept in visited for later reference
			 */
			for (UndirectedGraphNode temp : curr.neighbors) {
				UndirectedGraphNode neighbor = visited.get(temp.label);
				if(neighbor == null) {
					neighbor = new UndirectedGraphNode(temp.label);
					visited.put(neighbor.label, neighbor);
				}
				
				copy.neighbors.add(neighbor);
			}

			// mark the copy of the current node as "completed"
			completed.add(copy.label);
			for (UndirectedGraphNode temp : curr.neighbors)
				if (!completed.contains(temp.label))
					uncompleted.add(temp);
		}

		return visited.get(start_node_label);
	}

	/*
	 * This is my latest code as of Sep/2014 and it doesn't work due to NPE.
	 * The basic idea is: we have two loops, the first loop is to create all the nodes
	 * and the second loop is to connect the nodes.
	 */
    public UndirectedGraphNode cloneGraph_NPE(UndirectedGraphNode node) {
        List<UndirectedGraphNode> created = new ArrayList<UndirectedGraphNode>();
        Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
        
        List<UndirectedGraphNode> toBeCloned = new ArrayList<UndirectedGraphNode>();
        toBeCloned.add(node);
        
        while(toBeCloned.size() > 0) {
            UndirectedGraphNode curr = toBeCloned.remove(0);
            UndirectedGraphNode clone = new UndirectedGraphNode(curr.label);
            created.add(clone);
            map.put(curr.label, clone);
            
            for(UndirectedGraphNode neighbor : curr.neighbors) {
                if(!created.contains(neighbor)) {
                    toBeCloned.add(neighbor);
                }
            }
        }
        
        toBeCloned.add(node);
        while(toBeCloned.size() > 0) {
            UndirectedGraphNode curr = toBeCloned.remove(0);
            UndirectedGraphNode clone = map.get(curr.label);
            
            if(curr.neighbors == null)
                continue;
            
            clone.neighbors = new ArrayList<UndirectedGraphNode>();
            for(UndirectedGraphNode neighbor : curr.neighbors) {
                clone.neighbors.add(map.get(neighbor.label));
            }
        }
        
        return map.get(node.label);
    }
}
