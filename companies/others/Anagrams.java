package test;

import java.util.*;

/**
 * Created by qingcunz on 9/17/14.
 */
public class Anagrams {
    public static void main(String[] args) {
//        List<String> res = new Anagrams().anagrams(new String[] {"", ""});
//        System.out.println(res.size());
//        for(String str : res)
//            System.out.println(str);

        new Anagrams().flip();
    }

    public void flip() {
        boolean[] array = new boolean[100];
        for(int skip = 1; skip <= 100; skip++) {
            for(int i = 0; i < 100; i += skip) {
                array[i] = !array[i];
            }
        }

        for(boolean val : array)
            System.out.print(val + " ");
    }

    public List<String> anagrams(String[] strs) {
        List<String> res = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        boolean used[] = new boolean[strs.length];

        for(int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] arr = str.toCharArray();
            Arrays.sort(arr);

            Integer index = map.get(arr.toString());
            if(index != null) {
                res.add(str);
                used[i] = true;

                if(!used[index]) {
                    res.add(strs[index]);
                    used[index] = true;
                }
            } else {
                map.put(arr.toString(), i);
            }
        }

        return res;
    }
}
