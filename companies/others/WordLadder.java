package test;

import java.util.*;

/**
 * http://n00tc0d3r.blogspot.com/2013/07/word-ladder-ii.html
 */
public class WordLadder {
    public List<List<String>> findLadders(String start, String end, HashSet<String> dict) {
        List<List<String>> paths = new ArrayList<List<String>>();
        if (start == null || end == null || start.length() == 0)
            return paths;

        // maintain a hashmap for visited words
        Map<String, List<Node>> visited = new HashMap<String, List<Node>>();
        // BFS to find the minimum sequence length
        getMinLength(start, end, dict, visited);
        // DFS to back trace paths from end to start
        buildPaths(end, start, visited, new LinkedList<String>(), paths);

        return paths;
    }

    /* Use BFS to find the minimum transformation sequences length from start to end.
      Also store parent nodes from previous level for each visited valid word. */
    private void getMinLength(String start, String end, HashSet<String> dict, Map<String, List<Node>> visited) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(start, 1));
        dict.add(end);
        int lastLevel = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            // find transformable words in next level
            for (int i = 0; i < node.word.length(); ++i) {
                StringBuilder sb = new StringBuilder(node.word);
                char original = sb.charAt(i);
                for (char c='a'; c<='z'; ++c) {
                    if (c == original)
                        continue;

                    sb.setCharAt(i, c);
                    String s = sb.toString();
                    // if hits end, mark the current depth as the last level
                    if (s.equals(end) && lastLevel == 0)
                        lastLevel = node.depth + 1;

                    if (dict.contains(s) && !s.equals(start)) {
                        List<Node> pres = visited.get(s);
                        if (pres == null) {
                            // enqueue unvisited word
                            queue.add(new Node(s, node.depth+1));
                            pres = new ArrayList<Node>();
                            visited.put(s, pres);
                            pres.add(node);
                        } else if (pres.get(0).depth == node.depth) {
                            // parent nodes should be in the same level - to avoid circle in graph
                            pres.add(node);
                        }
                    }
                }
            }
        }
    }

    /* Use DFS to back trace all paths from end to start. */
    private void buildPaths(String s, String start, Map<String, List<Node>> visited, LinkedList<String> path, List<List<String>> paths) {
        if (s == null || visited == null || path == null || paths == null)
            return;

        path.add(0, s);
        if (s.equals(start)) {
            List<String> p = new ArrayList<String>(path);
            paths.add(p);
        } else {
            List<Node> pres = visited.get(s);
            if (pres != null) {
                for (Node pre : pres) {
                    buildPaths(pre.word, start, visited, path, paths);
                }
            }
        }
        path.remove(0);
    }

    private class Node {
        String word;
        int depth;
        public Node(String w, int d) {
            word = w; depth = d;
        }
    }
}