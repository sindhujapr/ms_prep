package interview.fb;

/*
 * http://blog.yxwang.me/2012/12/job-hunting-in-usa-2/
 */
public class FindDifferentElementsInSortedArrays {
    //my latest code
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

    /*
     * O(m+n)
     */
    public static void findDiffElements(int[] array1, int[] array2) {
    int i = 0;
    int j = 0;
    for (; i < array1.length; i++) {
        for (; j < array2.length; j++) {
        if(array2[j] >= array1[i]) {
            break;
        } else {
            System.out.println("array2\t" + array2[j]);
        }
        }
        
        /*
         * we have to make sure j is less than array2.length
         */
        if(j < array2.length && array2[j] != array1[i]) {
        System.out.println("array1\t" + array1[i]);
        } else if(j < array2.length) {
        /*
         * i will be increased in the outer loop
         */
        j++;
        } else {
        /*
         * j >= array2.length
         * thus we need to output the remaining part of array1
         */
        System.out.println("array1\t" + array1[i]);
        }
    }   
    
    /*
     * in the case that array2 is longer than array1
     */
    for (; j < array2.length; j++)
        System.out.println("array2\t" + array2[j]);
    }
    
    public static void main(String[] args) {
    int[] array1 = {1, 2, 4, 6, 7, 9, 12, 15, 18, 19, 20, 21};
    int[] array2 = {3, 4, 7, 8, 10, 12, 15, 17, 18};
    findDiffElements(array1, array2);
    }
}
