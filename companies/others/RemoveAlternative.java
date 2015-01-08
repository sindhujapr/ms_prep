package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingcunz on 11/12/14.
 */
public class RemoveAlternative {
    public static void main(String[] args) {
        for(int i = 20; i < 40; i++)
            remove(i);
    }

    public static void remove(int cap) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < cap; i++)
            list.add(i+1);

        int index = 0;
        while(list.size() > 1) {
            while(index < list.size() && list.size() > 1) {
                list.remove(index);
                index++;
            }

            if(index == list.size())
                index = 0;
            else
                index = 1;
        }
        System.out.println(list.get(0));
    }
}