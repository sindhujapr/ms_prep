package careercup;

import java.util.ArrayList;
import java.util.List;

/*
 * http://blog.csdn.net/v_july_v/article/details/6897097
 * Check section 3.4 of the above link and find alternative to construct
 * the suffix tree based on the PREFIXES. Take string "palindrome" for
 * example, the suffix tree of "palindrome" can be constructed based on
 * suffix tree of "palindrom", and append character 'e' to each path, then
 * insert a new node "e". However, we need to pay attention to problem 
 * described in section 3.5, we need to split the compacted suffix in certain
 * circumstances.
 */
class SuffixTreeNode {
    List<Character> keys = new ArrayList<Character>();
    List<SuffixTreeNode> children = new ArrayList<SuffixTreeNode>();

    public SuffixTreeNode(char c) {
    keys.add(c);
    }

    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(keys + "\t");
    return sb.toString();
    }
}

public class LongestPalindrome {
    SuffixTreeNode root = new SuffixTreeNode((char) 0);

    public static void main(String[] args) {
    String string = "xcpcxf";
    String reverseString = reverse(string);
    LongestPalindrome instance = new LongestPalindrome();

    /*
     * build the generalized suffix tree by inserting the suffixes of the
     * original string and reversed string
     */
    for (int i = 0; i < string.length(); i++) {
        /*
         * check the class comment for alternative to construct the suffix tree
         */
        instance.insert(string.substring(i), '$');
        /*
         * we could improve the code so that the longest palindrome can be
         * found when inserting the suffixes of the reversed string. see
         * insertAndFindLCA() below
         */
        instance.insert(reverseString.substring(i), '#');
    }

    instance.compact();
    instance.findLCA();

//  for (int i = 0; i < string.length(); i++) {
//      String substr = reverseString.substring(i);
//      instance.insertAndFindLCA(substr);
//  }
    }

    /*
     * Insert each char of the word into the tree and at last append a suffix
     */
    public void insert(String word, char suffix) {
    List<SuffixTreeNode> nodes = root.children;

    boolean isFound = false;
    for (int i = 0; i < word.length(); i++) {
        for (int j = 0; j < nodes.size(); j++) {
        if (nodes.get(j).keys.contains(Character.toLowerCase(word
            .charAt(i)))) {
            isFound = true;
            nodes = nodes.get(j).children;
        }
        }

        /*
         * Character doesn't exist along the path and we need to create a
         * new node for the character
         */
        if (!isFound) {
        SuffixTreeNode childNode = new SuffixTreeNode(
            Character.toLowerCase(word.charAt(i)));
        nodes.add(childNode);
        nodes = childNode.children;
        }
        
        /*
         * don't forget to reset the flag.
         */
        isFound = false;
    }

    /*
     * append the suffix to indicate whether this is for the original string
     * or the reversed string
     */
    nodes.add(new SuffixTreeNode(suffix));
    }

    public void insertAndFindLCA(String word) {
    List<SuffixTreeNode> nodes = root.children;

    boolean isFound = false;
    int i;
    for (i = 0; i < word.length(); i++) {
        int j;
        for (j = 0; j < nodes.size(); j++) {
        if (nodes.get(j).keys.contains(Character.toLowerCase(word
            .charAt(i)))) {
            isFound = true;
            break;
        }
        }
        
        if(isFound) {
        nodes = nodes.get(j).children;
        isFound = false;
        } else {
        System.out.println(word.substring(0, i));
        return;
        }
    }
    
    /*
     * here we have found all characters in the word (isFound is set to true
     * for each character) and thus we can output the whole word.
     */
    if(i == word.length())
        System.out.println(word);
    }

    /**
     * Compact the path if the nodes in the path each has only one child
     */
    public void compact() {
    List<SuffixTreeNode> children = root.children;
    for (SuffixTreeNode child : children)
        compact(child);
    }

    private void compact(SuffixTreeNode node) {
    List<SuffixTreeNode> children = node.children;
    List<Character> keys = node.keys;

    while (children.size() == 1) {
        SuffixTreeNode child = children.get(0);
        keys.addAll(child.keys);
        children = child.children;
    }

    /*
     * recursively compact child nodes
     */
    node.children = children;
    for (SuffixTreeNode child : children)
        compact(child);
    }

    /**
     * Find the longest common ancestor for each child of the root node
     */
    public void findLCA() {
    List<SuffixTreeNode> children = root.children;
    List<Character> word = new ArrayList<Character>();

    for (SuffixTreeNode child : children)
        findLCA(child, word);
    }

    private void findLCA(SuffixTreeNode node, List<Character> word) {
    word.addAll(node.keys);

    StringBuilder sb = new StringBuilder(word.size());
    for (Character ch : word)
        sb.append(ch);

    /*
     * We need to make sure the node is the ancestor of the suffixes from
     * both string. Also, we need to make sure the string in the path so
     * far is palindrome. Sometimes the string isn't.
     * Sometimes the non-longest palindrome may be ignored. For example,
     * for string "cppc", substring "pp" is palindrome but will not be checked
     * because we only check substring "cppc" in the 2nd-level node.
     */
    if (checkIfCommonAncestor(node) && isPalindrome(sb.toString()))
        System.out.println("found:\t" + sb.toString());

    /*
     * Recursively check the children
     */
    for (int i = 0; i < node.children.size(); i++) {
        SuffixTreeNode child = node.children.get(i);
        findLCA(child, word);
    }

    /* Remove the chars from this node */
    int size = word.size();
    for (int i = size - 1; i >= size - node.keys.size(); i--)
        word.remove(i);
    }

    /*
     * Only if the node is the ancestor of a suffix of both the original
     * string and the reversed string, it MAY be the palindrome. Thus, we
     * will check if the suffix '#' and '$' can be found from the children. 
     */
    private boolean checkIfCommonAncestor(SuffixTreeNode node) {
    List<SuffixTreeNode> children = node.children;
    boolean flag1 = false;
    boolean flag2 = false;

    for (SuffixTreeNode child : children) {
        if (findSuffix(child, '#'))
        flag1 = true;

        if (findSuffix(child, '$'))
        flag2 = true;

        if (flag1 & flag2)
        return true;
    }

    return true;
    }

    /*
     * Check if we can find the suffix ('#' or '$') in the specified node
     * or the children node.
     */
    public boolean findSuffix(SuffixTreeNode node, char suffix) {
    if (node.keys.contains(suffix))
        return true;

    for (SuffixTreeNode child : node.children)
        if (findSuffix(child, suffix))
        return true;

    return false;
    }

    public static String reverse(String str) {
    StringBuilder sb = new StringBuilder(str.length());
    for (int i = str.length() - 1; i >= 0; i--) {
        sb.append(str.charAt(i));
    }
    return sb.toString();
    }

    /*
     * String with one char is a palindrome. This routine works for both
     * "xcx" and "xppx".
     */
    public static boolean isPalindrome(String str) {
    for (int i = 0; i < str.length() / 2; i++) {
        if (str.charAt(i) != str.charAt(str.length() - 1 - i))
        return false;
    }

    return true;
    }
}

// public class LongestPalindrome {
// public static void main(String[] args) {
// // String string = "HELLOFUCKCUFOLCCPPCC";
// String string = "abaccabadefg";
// LongestPalindrome instance = new LongestPalindrome(string);
// instance.calculateLongestPalindrome();
//
// System.out.println("longest palindrome: " + instance.palindrome);
// System.out.println("length: " + instance.palindrome.length());
// System.out.println("Counter is: " + instance.PalindromeCounter);
// }
//
// String originalString;
// String palindrome;
// int palindromeLength;
// int PalindromeCounter;
//
// public LongestPalindrome(String s) {
// this.PalindromeCounter = 0;
// this.palindrome = "";
// this.palindromeLength = palindrome.length();
// this.originalString = s;
// }
//
// public void calculateLongestPalindrome() {
// if (originalString.length() == 1) {
// palindrome = originalString;
// palindromeLength = palindrome.length();
// }
// parseForPalindrome(originalString);
// }
//
// public void parseForPalindrome(String s) {
// PalindromeCounter++;
// if (s.length() <= 1) {
// return;
// }
// String foo = s;
//
// /*
// * if foo is not palindrome, check its substring
// */
// if (!isPalindrome(foo)) {
// parseForPalindrome(foo.substring(1));
// parseForPalindrome(foo.substring(0, foo.length() - 1));
// } else {
// if (palindrome.length() < foo.length()) {
// palindrome = foo;
// palindromeLength = palindrome.length();
// }
// return;
// }
// }
//
// public boolean isPalindrome(String s) {
// String foo = s;
// foo = foo.toLowerCase();
//
// if (foo.equals("")) {
// return false;
// }
// String reverseFoo = reverseString(foo);
// for (int i = 0; i < foo.length(); i++) {
// if (foo.charAt(i) != reverseFoo.charAt(i)) {
// return false;
// }
// }
// return true;
// }
//
// public String reverseString(String s) {
// StringBuffer sb = new StringBuffer();
// for (int i = s.length() - 1; i > -1; i--) {
// sb.append(s.charAt(i));
// }
// return sb.toString();
// }
// }