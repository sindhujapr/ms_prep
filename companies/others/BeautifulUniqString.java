package test;

/**
 * Created by qingcunz on 10/8/14.
 */
public class BeautifulUniqString {
    public static void main(String[] args) {
        System.out.println(new BeautifulUniqString().gen("helloworld"));
    }

    public String gen(String s) {
        int[] flags= new int[8];
        for(int i = 0; i < s.length(); i++)
            setBit(flags, s.charAt(i));

        StringBuilder builder = new StringBuilder();
        for(int i = flags.length-1; i >= 0; i--) {
            for(int j = 31; j >= 0; j--) {
                if(isBitOn(flags[i], j))
                    builder.append((char)(i*32 + j));
            }
        }
        return builder.toString();
    }

    private void setBit(int[] flags, char ch) {
        int index = (int) ch / 32;
        flags[index] |= 1 << ((int)ch % 32);
    }

    private boolean isBitOn(int value, int bit) {
        return (value & (1 << bit)) != 0;
    }
}
