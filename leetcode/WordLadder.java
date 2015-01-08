package lc2;

import java.util.HashSet;
import java.util.Set;

public class WordLadder {
    /*
     * recursion version. it's strange the test result is different from that on
     * LeetCode for the below two inputs: "hit", "cog",
     * ["hot","hit","cog","dot","dog"] 0 5 "leet", "code",
     * ["lest","leet","lose","code","lode","robe","lost"] 0 6
     */

    public int ladderLength1(String start, String end, HashSet<String> dict) {
        Set<String> allWords = new HashSet<String>();
        Set<String> curr = new HashSet<String>();
        curr.add(start);
        allWords.addAll(curr);
        
        return ladder(end, curr, allWords, dict, 0);
    }
    
    private int ladder(String end, Set<String> curr, Set<String> allWords, HashSet<String> dict, int depth) {
        if(curr.size() == 0)
            return 0;
            
        if(curr.contains(end))
            return ++depth;
            
        Set<String> next = new HashSet<String>();
        for(String word : curr) {
            Set<String> adjWords = adjacentWords(word, allWords, dict);
            next.addAll(adjWords);
        }
        
        allWords.addAll(next);
        return ladder(end, next, allWords, dict, depth+1);
    }

    public int ladderLength(String start, String end, HashSet<String> dict) {
        Set<String> allWords = new HashSet<String>();
        
        Set<String> curr = new HashSet<String>();
        curr.add(start);
        allWords.addAll(curr);
        int jump = 1;
        
        while(true) {
            Set<String> next = new HashSet<String>();
            for(String word : curr) {
                Set<String> adjWords = adjacentWords(word, allWords, dict);
                for(String word : curr) {
                    Set<String> adjWords = adjacentWords(word, allWords, dict);
                    if(adjWords.contains(end))
                        return ++jump;
                
                    next.addAll(adjWords);
                }   
            }
            
            if(next.size() == 0)
                return 0;
            curr = next;
            allWords.addAll(next);
            jump++;
        }
    }

    private Set<String> adjacentWords(String word, Set<String> allWords, Set<String> dict) {
        Set<String> res = new HashSet<String>();
        StringBuilder builder = new StringBuilder(word);
        for(int i = 0; i < builder.length(); i++) {
            char orig = builder.charAt(i);
            for(char ch = 'a'; ch <= 'z'; ch++) {
                if(ch != orig) {
                    builder.setCharAt(i, ch);
                    if(dict.contains(builder.toString()) && !allWords.contains(builder.toString()))
                       res.add(builder.toString()); 
                }
            }
            builder.setCharAt(i, orig);
        }
        return res;
    }

    public static void main(String[] args) {
//      WordLadder instance = new WordLadder();
        // HashSet<String> dict = instance.convertArrayToSet("hot", "dog");
        // System.out.println(instance.ladderLength("hot", "dog", dict));
    }
}
