package interview.thefind;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * To perform efficient query, we need to load the dictionary to memory.
 * 
 * we have three different data structures for choice:
 * 1. hash set 2. binary tree 3. trie tree
 * 
 * HashSet is not efficient regarding both performance and memory footprint:
 * 1. To check whether a string exists in the set, we need to compare the hash codes which
 * are based on the entire strings.
 * 2. memory usage is not optimal since the first two chars in "ab" and "abc" will be
 * duplicated in the set.
 * 
 * Binary search tree is even worse than HashSet since:
 * 1. the time complexity is O(lgn), which is worse than HashSet's O(1).
 * 2. same as HashSet, the memory usage is not optimal.
 * 
 * Trie tree is the best choice:
 * 1. To find whether a word exists in the dictionary, we only need to check at most
 * word.length() characters in the trie tree.
 * 2. duplicate characters in the same prefixes can be avoided. But there are still 
 * some marginal costs. See TrieNode implementation for details. However, we can further
 * optimize this part.
 */
class TrieTree {
    // the char in the root node can be arbitrary
    private static final TrieNode root = new TrieNode('0');
    
    static class TrieNode {
        private char ch;
        /*
         * This is to indicate whether we have a word (from the root) here.
         * Or we can do a little trick: use negative value (ch) to
         * indicate this is a word and thus save a bit memory. 
         */
        private boolean isWord;
        // to save a bit memory, this can be created on-demand
        private List<TrieNode> children = new ArrayList<TrieNode>();
        
        public TrieNode(char ch) {
            this.ch = ch;
        }
        
        // find the index of char (ch) in the children list, return -1 if not found
        private int indexOf(char ch) {
            char lch = Character.toLowerCase(ch);
            for(int i = 0; i < children.size(); i++) {
                if(Character.toLowerCase(children.get(i).ch) == lch)
                    return i;
            }
            return -1;
        }
        
        private void addChild(TrieNode child) {
            children.add(child);
        }
        
        private TrieNode getChild(int index) {
            return children.get(index);
        }
        
        // turn on the flag of the node to indicate it's the end of a word
        public void setWord() {
            this.isWord = true;
        }
        
        public boolean isWord() {
            return isWord;
        }
    }
    
    // insert the word into the trie tree
    public void add(String word) {
        TrieNode node = root;
        
        for(int i = 0, len = word.length(); i < len; i++) {
            char ch = word.charAt(i);
            int index = node.indexOf(ch);

            if(index == -1) {
                TrieNode child = new TrieNode(ch);
                if(i == len-1)
                    child.setWord();
                node.addChild(child);
                
                node = child;
            } else {
                node = node.getChild(index);
            }
        }
    }
    
    // find the word in the trie tree
    public boolean find(String word) {
        TrieNode node = root;
        for(int i = 0, len = word.length(); i < len; i++) {
            int index = node.indexOf(word.charAt(i));
            if(index == -1)
                return false;

            node = node.getChild(index);
            if(i == len-1 && node.isWord())
                return true;
        }
        return false;
    }
}

public class ValidWords {
    private final String dictPath;
    private final String gridPath;

    public ValidWords(String dictPath, String gridPath) {
        this.dictPath = dictPath;
        this.gridPath = gridPath;
    }
    
    private char[][] readGrid() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(gridPath));
            String line = br.readLine();
            String[] cols = line.split(" ");
            // for debug purpose only. Better to throw exception for production code.
            assert cols.length >= 2;
            
            int nRow = Integer.valueOf(cols[0]), nCol = Integer.valueOf(cols[1]);
            assert nRow > 0;
            
            char[][] grid = new char[nRow][nCol];
            for(int i = 0; i < nRow; i++) {
                line = br.readLine();
                assert line != null;
                
                line = line.trim();
                // suppose there is only one blank space between characters
                for(int j = 0; j < nCol; j++) {
                    grid[i][j] = line.charAt(j << 1);
                }
            }
            
            return grid;
        } finally {
            if(br != null)
                br.close();
        }
    }
    
    // initialize the dictionary
    private TrieTree readDictTrie() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(dictPath));
            String line;
            
            TrieTree tTree = new TrieTree();
            while((line = br.readLine()) != null)
                tTree.add(line.trim());
            
            return tTree;
        } finally {
            if(br != null)
                br.close();
        }
    }
    
    private Set<String> readDictSet() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(dictPath));
            String line;
            
            Set<String> set = new HashSet<String>();
            while((line = br.readLine()) != null)
                set.add(line.trim());
            
            return set;
        } finally {
            if(br != null)
                br.close();
        }
    }
    
    // find all words that occur in the dictionary
    public void findWords() throws IOException {
        char[][] grid = readGrid();
        TrieTree dict = readDictTrie();

        /*
         * use tree set to keep the lexicographical order. Otherwise we need to
         * implement Comparable interface 
         */
        TreeSet<String> res = new TreeSet<String>();
        int m = grid.length, n = grid[0].length;
        
        boolean used[][] = new boolean[m][n];
        long start = System.currentTimeMillis();
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                findWords(grid, i, j, used, new StringBuilder(), res, dict);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(res.size());
        for(String word : res)
            System.out.println(word);
    }
    
    /*
     * use backtrack to check all possible words in the grid. The implementation uses recursion.
     * To avoid stack overflow, we can use our own stack for backtrack.
     */
    private void findWords(char[][] grid, int row, int col, boolean[][] used, StringBuilder builder, TreeSet<String> res, TrieTree dict) {
        int m = grid.length, n = grid[0].length;
        used[row][col] = true;

        builder.append(Character.toLowerCase(grid[row][col]));
        if(dict.find(builder.toString()))
            res.add(builder.toString());
        
        if(row < m-1 && !used[row+1][col])
            findWords(grid, row+1, col, used, builder, res, dict);
        
        if(row > 0 && !used[row-1][col])
            findWords(grid, row-1, col, used, builder, res, dict);
        
        if(col < n-1 && !used[row][col+1])
            findWords(grid, row, col+1, used, builder, res, dict);
        
        if(col > 0 && !used[row][col-1])
            findWords(grid, row, col-1, used, builder, res, dict);
        
        if(row > 0 && col > 0 && !used[row-1][col-1])
            findWords(grid, row-1, col-1, used, builder, res, dict);
        
        if(row > 0 && col < n-1 && !used[row-1][col+1])
            findWords(grid, row-1, col+1, used, builder, res, dict);
        
        if(row < m-1 && col > 0 && !used[row+1][col-1])
            findWords(grid, row+1, col-1, used, builder, res, dict);
        
        if(row < m-1 && col < n-1 && !used[row+1][col+1])
            findWords(grid, row+1, col+1, used, builder, res, dict);
        
        builder.deleteCharAt(builder.length()-1);
        used[row][col] = false;
    }
    
    public static void main(String[] args) {
        try {
            // change them for your test
            String dictPath = "/Users/szhou/Documents/workspace/Practice/src/interview/thefind/englishwords.txt";
            String gridPath = "/Users/szhou/Documents/workspace/Practice/src/interview/thefind/grid.txt";
            ValidWords instance = new ValidWords(dictPath, gridPath);
            instance.findWords();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}