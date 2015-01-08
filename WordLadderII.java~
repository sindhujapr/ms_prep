package lc2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WordLadderII {
    public static List<List<Integer>> init() {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(7);
        result.add(list1);

        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(2);
        list2.add(3);
        result.add(list2);

        List<Integer> list3 = new ArrayList<Integer>();
        list3.add(4);
        list3.add(5);
        result.add(list3);

        List<Integer> list4 = new ArrayList<Integer>();
        list4.add(6);
        result.add(list4);

        return result;
    }

    public static List<List<Integer>> allPaths1(List<List<Integer>> input) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (input.size() == 0)
            return result;

        List<Iterator<Integer>> stack = new ArrayList<Iterator<Integer>>();
        for (List<Integer> lst : input) {
            stack.add(lst.iterator());
        }

        List<Integer> one = new ArrayList<Integer>();
        do {
            while (one.size() < input.size()) {
                Iterator<Integer> it = stack.get(one.size());

                /*
                 * if we can add the next element, then add it. otherwise, we
                 * need to reset the iterators thereafter. after this (inner)
                 * loop, the last element will be removed from the stack, such
                 * that the next element of the corresponding iterator will be
                 * added.
                 */
                if (it.hasNext()) {
                    one.add(it.next());
                } else {
                    for (int i = one.size(); i < input.size(); i++)
                        stack.set(i, input.get(i).iterator());
                    break;
                }
            }

            if (one.size() == input.size())
                result.add(new ArrayList<Integer>(one));

            one.remove(one.size() - 1);
        } while (one.size() > 0 || stack.get(0).hasNext());

        System.out.println("==========result 1============");
        for (List<Integer> lst : result)
            System.out.println(lst);
        return result;
    }

    public static List<List<Integer>> allPaths2(List<List<Integer>> input) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (input.size() == 0)
            return result;

        List<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < input.size(); i++) {
            indices.add(0);
        }

        List<Integer> one = new ArrayList<Integer>();
        do {
            while (one.size() < input.size()) {
                int index = indices.get(one.size());

                if (index < input.get(one.size()).size()) {
                    indices.set(one.size(), index + 1);
                    one.add(input.get(one.size()).get(index));
                } else {
                    for (int i = one.size(); i < input.size(); i++)
                        indices.set(i, 0);
                    break;
                }
            }

            if (one.size() == input.size())
                result.add(new ArrayList<Integer>(one));

            one.remove(one.size() - 1);
        } while (one.size() > 0 || indices.get(0) < input.get(0).size());

        System.out.println("==========result 2============");
        for (List<Integer> lst : result)
            System.out.println(lst);
        return result;
    }

    private static List<List<Integer>> result = new ArrayList<List<Integer>>();

    /*
     * recursion edition and more concise
     */
    public static void allPaths3(List<List<Integer>> input) {
        List<Iterator<Integer>> iters = new ArrayList<Iterator<Integer>>();
        for (List<Integer> list : input) {
            iters.add(list.iterator());
        }

        List<Integer> one = new ArrayList<Integer>();
        allPaths3_i(input, iters, one);

        System.out.println("==========result 3============");
        for (List<Integer> lst : result)
            System.out.println(lst);
    }

    private static void allPaths3_i(List<List<Integer>> input, List<Iterator<Integer>> iters,
            List<Integer> one) {
        if (one.size() == iters.size()) {
            result.add(new ArrayList<Integer>(one));
            return;
        }

        Iterator<Integer> it = iters.get(one.size());
        while (it.hasNext()) {
            /*
             * for each loop, we add the next element and use recursion to reach
             * the next List, then remove the added element so that we can add
             * the remaining ones.
             */
            one.add(it.next());
            allPaths3_i(input, iters, one);
            one.remove(one.size() - 1);
        }

        /*
         * only after all elements of the current List have been evaluated, we
         * can reset the iterator
         */
        iters.set(one.size(), input.get(one.size()).iterator());
    }

    public static void main(String[] args) {
        List<List<Integer>> list = init();
        allPaths1(list);
        allPaths2(list);
        allPaths3(list);
    }
}

/*
 * this code doesn't pass compilation due to Iterator
 */
class Solution1 {
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();

        List<Set<String>> result = new ArrayList<Set<String>>();
        Set<String> set = new HashSet<String>();
        set.add(start);

        while (true) {
            Set<String> collection = result.get(result.size() - 1);
            Set<String> adjacentWords = new HashSet<String>();

            for (String word : collection) {
                Set<String> adjacent = adjacentWords(word, dict);
                adjacentWords.addAll(adjacent);
            }

            purify(result, adjacentWords);

            // no path...
            if (adjacentWords.isEmpty())
                return ret;

            result.add(adjacentWords);
            if (adjacentWords.contains(end)) {
                break;
            }
        }

        extract(start, end, result, dict);

        combine(result, ret);
        return ret;
    }

    private void combine(List<Set<String>> result, ArrayList<ArrayList<String>> ret) {
        List<String> list = new ArrayList<String>();
        List<Iterator<String>> iters = new ArrayList<Iterator<String>>();
        for (int i = 0; i < result.size(); i++)
            iters.add(result.get(i).iterator());

        combine_i(result, iters, list, ret);
    }

    private void combine_i(List<Set<String>> result, List<Iterator<String>> iters, List<String> list,
            ArrayList<ArrayList<String>> ret) {
        if (list.size() == result.size())
            ret.add(new ArrayList<String>(list));

        Iterator<String> iter = iters.get(list.size());
        while (iter.hasNext()) {
            list.add(iter.next());
            combine_i(result, iters, list, ret);
            list.remove(list.size() - 1);
        }

        iters.set(list.size(), result.get(list.size()).iterator());
    }

    private void extract(String start, String end, List<Set<String>> result, HashSet<String> dict) {
        Set<String> words = new HashSet<String>();
        words.add(end);

        for (int i = result.size() - 1; i >= 0; i--) {
            Set<String> copy = new HashSet<String>(result.get(i));

            for (String word : copy) {
                if (!words.contains(word))
                    result.get(i).remove(word);
            }

            words.clear();
            for (String word : result.get(i))
                words.addAll(adjacentWords(word, dict));
        }
    }

    private boolean isAdjacent(String word1, String word2) {
        boolean isAdjacent = false;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (!isAdjacent)
                    isAdjacent = true;
                else
                    return false;
            }
        }

        return true;
    }

    private void purify(List<Set<String>> result, Set<String> words) {
        for (Set<String> set : result) {
            for (String word : set) {
                if (words.contains(word))
                    words.remove(word);
            }
        }
    }

    private Set<String> adjacentWords(String word, HashSet<String> dict) {
        Set<String> adjacent = new HashSet<String>();
        StringBuilder sb = new StringBuilder(word);

        for (int i = 0; i < word.length(); i++) {
            char original = word.charAt(i);
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == original)
                    continue;

                sb.setCharAt(i, ch);
                if (dict.contains(sb.toString()))
                    adjacent.add(sb.toString());
            }
            sb.setCharAt(i, original);
        }

        return adjacent;
    }
}