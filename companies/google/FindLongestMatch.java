package interview.google;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Given some words, eg, abacus, deltoid, gaff, giraffe, microphone. reef, qar.
 * Also given a collection of letters. E.g., {a, e, f, f, g, i, r, q}.
 * please find the longest word in the dictionary that consists of the lettters.
 * For example, the correct answer is �giraffe�. (Note that �reef� is not a 
 * possible answer, because the set of letters contains only one �e�.)
 * 
 * This problem is different from LCS as described in:
 * http://blog.csdn.net/v_JULY_v/article/details/6110269
 */

/* 
 * Among all of the 5 implementations, #5 is the fastest
 */
public class FindLongestMatch {
    private static char[] letters = { 'a', 'e', 'f', 'f', 'g', 'i', 'r', 'q' };
    private static int loopCnt = 100000;
    private static List<String> words = new ArrayList<String>();

    static {
    try {
        BufferedReader br = new BufferedReader(new FileReader(new File(
            "words.txt")));

        String word = null;
        while ((word = br.readLine()) != null)
        words.add(word);
    } catch (Exception e) {
        System.out.println(e);
    }
    }

    public static List<String> find1(List<String> words) {
    boolean visited[] = new boolean[letters.length];
    List<String> result = new LinkedList<String>();
    // System.out.println(Arrays.toString(visited));

    String longest = null;

    for (int j = 0; j < words.size(); j++) {
        String str = words.get(j);
        char[] chs = str.toCharArray();
        for (char c : chs) {
        int i = 0;
        for (; i < letters.length; i++) {
            if (c == letters[i] && visited[i] == false) {
            visited[i] = true;
            break;
            }
        }
        if (i == letters.length) {
            // System.out.println("Letter " + c + " in word \"" + str +
            // "\" not found in the array");
            continue;
        }
        }

        int cnt = 0;
        for (boolean visit : visited)
        cnt += (visit == true) ? 1 : 0;
        if (cnt == str.length()) {
        result.add(str);
        // System.out.println("found matched word \"" + str + "\"");
        if (longest == null || cnt > longest.length())
            longest = str;
        }

        Arrays.fill(visited, false);
    }

    // System.out.println("Find1 largest match \"" + longest + "\"");
    return result;
    }

    public static List<String> find2(List<String> words) {
    Map<Character, Integer> letterCnt = scan(letters);
    List<String> result = new LinkedList<String>();

    String largest = null;

    for (int j = 0; j < words.size(); j++) {
        String str = words.get(j);
        Map<Character, Integer> stringCnt = scan(str.toCharArray());
        Set<Character> keys = stringCnt.keySet();
        boolean found = true;
        for (Character character : keys) {
        if (letterCnt.get(character) == null
            || stringCnt.get(character) > letterCnt.get(character)) {
            found = false;
            break;
        }
        }
        int length = (largest != null) ? largest.length() : 0;

        if (found == true) {
        result.add(str);
        // System.out.println("found words \"" + str + "\"");
        if (str.length() > length)
            largest = str;
        }
    }

    // System.out.println("Find2 largest match \"" + largest + "\"");
    return result;
    }

    public static List<String> find3(List<String> words) {
    List<String> result = new LinkedList<String>();

    Arrays.sort(letters);
    String letterString = new String(letters);
    // System.out.println(letters);
    String largest = null;
    for (int j = 0; j < words.size(); j++) {
        String str = words.get(j);
        char[] chs = str.toCharArray();
        Arrays.sort(chs);
        StringBuilder sb = new StringBuilder(30);
        for (int i = 0; i < chs.length;) {
        char ch = chs[i];
        int pos = i++;
        while (i < chs.length && chs[i] == ch)
            i++;
        sb.append(String.format(".*%c{%d,}", ch, (i - pos)));
        }
        sb.append(".*");
        // System.out.println(sb.toString());

        if (letterString.matches(sb.toString())) {
        result.add(str);
        // System.out.println("found words \"" + str + "\"");
        int length = (largest != null) ? largest.length() : 0;
        if (str.length() > length)
            largest = str;
        }
    }

    // System.out.println("Find3 largest match \"" + largest + "\"");
    return result;
    }

    public static List<String> find4(List<String> words) {
    List<String> result = new LinkedList<String>();
    Arrays.sort(letters);

    StringBuilder sb = new StringBuilder(30);
    int length = letters.length;
    for (int i = 0; i < length;) {
        char ch = letters[i];
        int pos = i++;
        while (i < length && letters[i] == ch)
        i++;
        sb.append(ch);
        sb.append("{0,");
        sb.append(i - pos);
        sb.append('}');
        /* poor performance */
        // sb.append(String.format("%c{0,%d}", ch, (i-pos)));
    }
    // System.out.println(sb.toString());
    String regex = sb.toString();

    String largest = null;
    int size = words.size();
    for (int j = 0; j < size; j++) {
        String str = words.get(j);
        char[] chs = str.toCharArray();
        Arrays.sort(chs);
        String ordered = new String(chs);

        if (ordered.matches(regex)) {
        result.add(str);
        // System.out.println("found words \"" + str + "\"");
        int len = (largest != null) ? largest.length() : 0;
        if (str.length() > len)
            largest = str;
        }
    }

    // System.out.println("Find4 largest match \"" + largest + "\"");
    return result;
    }

    // Best performance
    public static List<String> find5(List<String> words) {
    List<String> result = new ArrayList<String>();
    Arrays.sort(letters);

    String largest = null;
    int size = words.size();
    for (int j = 0; j < size; j++) {
        String word = words.get(j);
        char[] chs = word.toCharArray();
        Arrays.sort(chs);

        /*
         * Don't use Pattern. Instead, I'll match myself. For each char in
         * the String, I'll check if it's available in the letters array
         */
        int k = 0;
        int length1 = chs.length;
        int length2 = letters.length;
        boolean found = true;
        for (int i = 0; i < length1; i++) {
        char ch = chs[i];
        int pos1 = i++;
        while (i < length1 && chs[i] == ch)
            i++;
        /* Now char ch repeated #cnt1 times in the word */
        int cnt1 = i - pos1;

        /* This is to skip redundant chars in the letters array */
        while (k < length2 && letters[k] != ch && letters[k] < ch)
            k++;
        if (k == length2 || letters[k] > ch) {
            found = false;
            break;
        }

        /*
         * Then we're sure char ch exists in letters array and we need
         * to count the occurrences
         */
        int pos2 = k;
        while (k < length2 && letters[k] == ch)
            k++;
        /* The same char ch repeated #cnt2 in the letter */
        int cnt2 = k - pos2;

        if (cnt1 <= cnt2) {
            // System.out.println("Found enough char " + ch +
            // " in letters");
            continue;
        } else {
            // System.out.println("Char " + ch +
            // " doesn't match lettes");
            found = false;
            break;
        }
        }

        if (true == found) {
        result.add(word);
        int len = (largest != null) ? largest.length() : 0;
        if (word.length() > len)
            largest = word;
        // System.out.println("matched word " + words.get(j));
        }
        // else
        // System.out.println("Doesn't match " + words.get(j));
    }

    // System.out.println("Find5 largest match \"" + largest + "\"");
    return result;
    }

    private static Map<Character, Integer> scan(char[] letters) {
    Map<Character, Integer> letterCnt = new HashMap<Character, Integer>();
    for (char ch : letters) {
        if (letterCnt.containsKey(ch))
        letterCnt.put(ch, letterCnt.get(ch) + 1);
        else
        letterCnt.put(ch, 1);
    }

    return letterCnt;
    }

    public static void verifyResult() {
    List<String> correctResult = new ArrayList<String>();
    correctResult.add("gaff");
    correctResult.add("giraffe");
    correctResult.add("qar");
    correctResult.add("aeffgirq");

    System.out.println("Find1 correct? "
        + correctResult.equals(find1(words)));
    System.out.println("Find2 correct? "
        + correctResult.equals(find2(words)));
    System.out.println("Find3 correct? "
        + correctResult.equals(find3(words)));
    System.out.println("Find4 correct? "
        + correctResult.equals(find4(words)));
    System.out.println("Find5 correct? "
        + correctResult.equals(find5(words)));
    }

    public static void perfTest() {
    long start = 0, end = 0;

    start = System.currentTimeMillis();
    for (int i = 0; i < loopCnt; i++) {
        find1(words);
    }
    end = System.currentTimeMillis();
    System.out.println("find1\t" + (end - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < loopCnt; i++) {
        find2(words);
    }
    end = System.currentTimeMillis();
    System.out.println("find2\t" + (end - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++) {
        find3(words);
    }
    end = System.currentTimeMillis();
    System.out.println("find3\t" + (end - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < loopCnt; i++) {
        find4(words);
    }
    end = System.currentTimeMillis();
    System.out.println("find4\t" + (end - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < loopCnt; i++) {
        find5(words);
    }
    end = System.currentTimeMillis();
    System.out.println("find5\t" + (end - start));
    }

    public static void main(String[] args) throws Exception {
    System.out.println(words);
    verifyResult();
    perfTest();
    }
}