package leetcode;

import java.util.HashSet;
import java.util.Set;

public class WordLadder {
    /*
     * recursion version. it's strange the test result is different from that
     * on LeetCode for the below two inputs:
     * "hit", "cog", ["hot","hit","cog","dot","dog"]    0   5
     * "leet", "code", ["lest","leet","lose","code","lode","robe","lost"]   0   6
     */
    private Set<String> allExaminedWords = new HashSet<String>();
    
    public int ladderLength1(String start, String end, HashSet<String> dict) {
        Set<String> next = adjacentWords(start, dict);
        // this is necessary
        allExaminedWords.add(start);
        return ladderLength1(next, end, dict, 1);
    }
    
    private int ladderLength1(Set<String> next, String end, HashSet<String> dict, int depth) {
        if(next.isEmpty())
            return 0;

        if(next.contains(end))
            return ++depth;
        
        /*
         * here next doesn't contain the target word and we can say
         * all words within it have been examined.
         */
        allExaminedWords.addAll(next);

        Set<String> nextWords = new HashSet<String>();
        for(String word : next) {
            Set<String> words = adjacentWords(word, dict);
            for(String element : words) {
                if(allExaminedWords.contains(element))
                    continue;
                nextWords.add(element);
            }
        }

        return ladderLength1(nextWords, end, dict, depth+1);
    }

    public int ladderLength(String start, String end, HashSet<String> dict) {
    Set<String> allExaminedWords = new HashSet<String>();
    Set<String> first = new HashSet<String>();
    first.add(start);
    allExaminedWords.addAll(first);

    int loopCnt = 1;
    while (true) {
        Set<String> nextWords = new HashSet<String>();

        for (String word : first) {
        Set<String> adjacentWords = adjacentWords(word, dict);

        for (String next : adjacentWords) {
            if (next.equals(end))
            return ++loopCnt;

            /*
             * we cannot remove elements from adjacent. Otherwise there
             * will be ConcurrentModificationException.
             */
            if (allExaminedWords.contains(next))
            continue;

            nextWords.add(next);
        }
        }

        /*
         * here we cannot move forward since we're in a loop, that means all
         * words in the next step have already been evaluated before.
         */
        if (nextWords.isEmpty()) {
        return 0;
        }

        allExaminedWords.addAll(nextWords);
        first = nextWords;
        loopCnt++;
    }
    }

    private Set<String> adjacentWords(String word, HashSet<String> dict) {
        Set<String> adjacent = new HashSet<String>();
        StringBuilder sb = new StringBuilder(word);

        for(int i = 0; i < word.length(); i++) {
            char original = word.charAt(i);
            for(char ch = 'a'; ch <= 'z'; ch++) {
                if(ch == original)
                    continue;
                
                sb.setCharAt(i, ch);
                if(dict.contains(sb.toString()))
                    adjacent.add(sb.toString());
            }
            sb.setCharAt(i, original);
        }
        
        return adjacent;
    }

    public HashSet<String> convertArrayToSet(String... array) {
    HashSet<String> set = new HashSet<String>();
    for (String str : array)
        set.add(str);
    return set;
    }

    public static void main(String[] args) {
    WordLadder instance = new WordLadder();
//  HashSet<String> dict = instance.convertArrayToSet("hot", "dog");
//  System.out.println(instance.ladderLength("hot", "dog", dict));
    
    HashSet<String> dict = instance.convertArrayToSet("hot","hit","cog","dot","dog");
    System.out.println(instance.ladderLength1("hit", "cog", dict));
    }
}