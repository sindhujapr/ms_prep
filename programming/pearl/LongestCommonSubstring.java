package programming.pearl;


/*
 * See Column 15 of programming pearl
 */
public class LongestCommonSubstring {
    public static void main(String[] args) {
        String str = "ask not what your country can do for you but ask what you can do for your country";
        long start = System.nanoTime();
        System.out.println(find1(str));
        long end = System.nanoTime();
        System.out.println(end - start);
        
        end = System.nanoTime();
        System.out.println(find2(str));
        System.out.println(System.nanoTime()-end);
    }
    
    /*
     * this algorithm doesn't seem better than find2()
     */
    public static String find1(String str) {
        int n = str.length();
        String[] list = new String[n];
        for(int i = 0; i < n; i++)
            list[i] = str.substring(i);

        qsort(list, 0, list.length-1);
        
        String maxStr = null; 
        for(int i = 0; i < n-1; i++) {
            String curstr = comLen(list[i], list[i+1]);
            if(maxStr == null || curstr.length() > maxStr.length())
                maxStr = curstr;
        }
        
        return maxStr;
    }
    
    private static void qsort(String[] list, int low, int high) {
        if(low >= high)
            return;

        int j = low;
        for(int i = low+1; i <= high; i++) {
            if(compare(list[i], list[low]) < 0)
                swap(list, i, ++j);
        }
        swap(list, low, j);
        
        qsort(list, low, j-1);
        qsort(list, j+1, high);
    }
    
    private static int compare(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        for(int i = 0; i < m && i < n; i++) {
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(i);
            if(ch1 != ch2)
                return ch1 - ch2;
        }
        
        if(m == n)
            return 0;
        else if(m > n)
            return 1;
        else
            return -1;
    }
    
    private static void swap(String[] array, int i, int j) {
        if(i == j)
            return;
        
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    private static String comLen(String str1, String str2) {
        for(int i = 0; i < str1.length() && i < str2.length(); i++) {
            if(str1.charAt(i) != str2.charAt(i))
                return str1.substring(0, i);
        }
        return str1.length() > str2.length() ? str2: str1;
    }
    
    public static String find2(String str) {
        String max = "";
        
        int n = str.length();
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                int k = i;
                int p = j;
                while(p < n && str.charAt(k) == str.charAt(p)) {
                    k++;
                    p++;
                }

                if(k-i > max.length())
                    max = str.substring(i, k);
            }
        }
        
        return max;
    }
}