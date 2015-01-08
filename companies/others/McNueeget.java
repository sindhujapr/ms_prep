package test;

/**
 * Created by qingcunz on 10/10/14.
 */
public class McNueeget {
    public static void main(String[] args) {
        McNueeget instance = new McNueeget();
        for(int i = 0; i < 100; i++)
            if(instance.McNuggets(i))
                System.out.println(i);
    }

    public boolean McNuggets(int target) {
        boolean[] flag = new boolean[target+1];
        flag[0] = true;

        for(int i = 1; i < flag.length; i++) {
            flag[i] = (i >= 6 && flag[i-6]) || (i >= 9 && flag[i-9]) || (i >= 20 && flag[i-20]);
        }

        return flag[target];
    }
}
