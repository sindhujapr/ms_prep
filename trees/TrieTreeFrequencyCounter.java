package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieTreeNode {
    char key;
    int counter = 0;
    List<TrieTreeNode> children = new ArrayList<TrieTreeNode>();

    public TrieTreeNode(char c) {
    this.key = c;
    }

    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(key + "\t");
    sb.append(counter);
    return sb.toString();
    }
}

public class TrieTreeFrequencyCounter {
    TrieTreeNode root = new TrieTreeNode((char) 0);

    public void insert(char[] word) {
    List<TrieTreeNode> nodes = root.children;
    TrieTreeNode lastNode = null;
    boolean isFound = false;
    for (int i = 0; i < word.length; i++) {
        int j;
        for (j = 0; j < nodes.size(); j++) {
        if (Character.toLowerCase(nodes.get(j).key) == Character
            .toLowerCase(word[i])) {
            isFound = true;
            lastNode = nodes.get(j);
            nodes = nodes.get(j).children;
        }
        }

        if (!isFound) {
        TrieTreeNode childNode = new TrieTreeNode(
            Character.toLowerCase(word[i]));
        lastNode = childNode;
        nodes.add(childNode);
        nodes = childNode.children;
        }
        isFound = false;
    }

    lastNode.counter++;
    }

    public int findWordCount(char[] word) {
    List<TrieTreeNode> nodes = root.children;
    TrieTreeNode lastNode = null;
    boolean isFound = false;
    for (int i = 0; i < word.length; i++) {
        int j;
        for (j = 0; j < nodes.size(); j++) {
        if (nodes.get(j).key == word[i]) {
            isFound = true;
            lastNode = nodes.get(j);
            nodes = nodes.get(j).children;
        }
        }

        if (!isFound) {
        System.out.println(new String(word) + "\t\t: 0");
        return 0;
        }

        isFound = false;
    }

    System.out.println(new String(word) + "\t\t: " + lastNode.counter);
    return lastNode.counter;
    }

    public void statistics() {
    // DFS
    visit();
    }

    static Map<String, Integer> stats = new HashMap<String, Integer>();

    public void visit() {
    List<TrieTreeNode> children = root.children;
    List<Character> word = new ArrayList<Character>();

    for (TrieTreeNode child : children)
        visit(child, word);
    }

    private void visit(TrieTreeNode node, List<Character> word) {
    word.add(node.key);

    if (node.children.size() == 0 && node.counter > 0) {
        StringBuilder sb = new StringBuilder(word.size());
        for (Character ch : word)
        sb.append(ch);
        System.out.println(sb.toString() + "\t\t: " + node.counter);
        stats.put(sb.toString(), node.counter);
    } else {
        for (TrieTreeNode child : node.children)
        visit(child, word);
    }

    word.remove(word.size() - 1);
    }

    public static void main(String[] args) {
    TrieTreeFrequencyCounter counter = new TrieTreeFrequencyCounter();
    counter.insert("hello".toCharArray());
    counter.insert("Hello".toCharArray());
    counter.insert("helf".toCharArray());
    counter.insert("world".toCharArray());
    counter.insert("world".toCharArray());
    counter.insert("world".toCharArray());
    counter.insert("world".toCharArray());
    counter.insert("Android".toCharArray());

    counter.visit();
    System.out.println(stats);

    counter.findWordCount("hello".toCharArray());
    counter.findWordCount("helf".toCharArray());
    counter.findWordCount("word".toCharArray());
    counter.findWordCount("world".toCharArray());
    }
}