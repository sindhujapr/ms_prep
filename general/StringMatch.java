package general;

/*
 * input:   abcadefg
 * rule:    a?c
 * output:  abc
 * 
 * input:   newsadfanewfdadsf
 * rule:    new
 * output:  new new
 * 
 * input:   breakfastfood
 * rule:    f*d
 * output:  fastfood
 * 
 * input:   breakfastfood
 * rule:    f*x*d
 * output:  null
 */
public class StringMatch {
    /*
     * since we need the longest match, we can match the first letter and
     * the last letter of the rule from the beginning and end, respectively,
     * of the input.
     * if the first letter of rule doesn't have a match, fail.
     * if the last letter of rule doesn't have a match, fail.
     * if the first/last letter matches at the first try but there isn't a
     * match for some intermediate letters, then either the first letter
     * or the last letter move to the next occurrence
     * 
     */
    public static void find(char[] input, char[] rule, char[] output) {
    if(rule.length == 0)
        return;
    
    int start = 0;
    while(rule[start++] == '*');
    
    
    start = indexOf(input, start, rule[start]);
    int end = lastIndexOf(input, start, input.length-1, rule[rule.length-1]);
    if(start == -1 || end == -1)
        return;
    
    }
    
    /*
     * check if rule[start2, ..., end2] exists in input[start1, .., end1]
     */
    public boolean find(char[] input, int start1, int end1, char[] rule, int start2, int end2) {
    int s1 = start1;
    int e1 = end1;
    int s2 = start2;
    int e2 = end2;

    /*
     * for '*' in the rule, we don't need to match any char in the input array
     */
    while(rule[s2] == '*' && s2 < e2)
        s2++;
    while(rule[e2] == '*' && s2 < e2)
        e2--;

    if(s2 >= e2)
        return true;
    
    s1 = indexOf(input, s1, rule[s2]) + 1;
    e1 = lastIndexOf(input, s1, e2, input[e2]) - 1;
    if(s1 == -1 || e1 == -1 || s1 > e1)
        return false;

    if(!find(input, s1, e1, rule, s2, e2)) {
        
    }

    return false;
    }
    
    private static int indexOf(char[] array, int start, char ch) {
    for (int i = start; i < array.length; i++) {
        if(array[i] == ch || ch == '?')
        return i;
    }
    return -1;
    }

    private static int lastIndexOf(char[] array, int start, int end, char ch) {
    for (int i = end; i >= end; i--) {
        if(array[i] == ch || ch == '?')
        return i;
    }
    return -1;
    }


    public static void main(String[] args) {
    char[] output = new char[1024];
    find("breakfastfood".toCharArray(), "f*d".toCharArray(), output);
    }
}