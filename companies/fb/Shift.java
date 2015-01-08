
// http://www.careercup.com/question?id=4909367207919616
public class Shift {
    public static void shift(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n; i++)
            arr[i] += (arr[arr[i]] % n) * n;

        for(int i = 0; i < n; i++)
            arr[i] /= n;

        System.out.println(Arrays.toString(arr));
    }
}
