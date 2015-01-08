package search.sorting;

/*
 * http://www.careercup.com/question?id=15420859
 */
public class BinaryNumbersSum {
    public static void main(String[] args) {
        sum("110", "01101");
    }
    
    public static void sum(String s1, String s2) {
        assert s1 != null && s2 != null;
        
        // maybe we should trim the strings here
        StringBuilder builder = new StringBuilder();
        for(int carrier = 0, i = s1.length()-1, j = s2.length()-1; carrier > 0 || i >= 0 || j >= 0; i--, j--) {
            int v1 = i >= 0 && s1.charAt(i) == '1' ? 1 : 0;
            int v2 = j >= 0 && s2.charAt(j) == '1' ? 1 : 0;
            int sum = v1+v2 + carrier;
            carrier = sum / 2;
            sum %= 2;
            
            builder.insert(0, sum == 0 ? '0' : '1');
        }
        
        System.out.println(builder.toString());
    }
}