package test;

/**
 * Created by qingcunz on 10/7/14.
 */
public class FindDiff {
    public static void main(String[] args) {

    }

    public void findDiff(int[] a1, int[] a2) {
        for(int i = 0, j = 0; i < a1.length || j < a2.length;) {
            if(i == a1.length) {
                System.out.println(a2[j++]);
            } else if(j == a2.length) {
                System.out.println(a1[i++]);
            } else {
                if(a1[i] < a2[j])
                    System.out.println(a1[i++]);
                else if(a1[i] > a2[j])
                    System.out.println(a2[j++]);
                else {
                    i++;
                    j++;
                }
            }
        }
    }
}
