public class Read4II {
    /*
     * read len chars to buf, which is supposed to be large enough.
     * Didn't test with leetcode
     * copied from http://www.mitbbs.com/article_t/JobHunting/32836155.html
     * http://blog.csdn.net/a83610312/article/details/12872437
     */
    private List<Character> left = new ArrayList<Character>();

    public int read(char[] buf, int n) {
        int ptr = Math.min(n, left.size());

        for(int i = 0; i < ptr; i++)
            buf[i] = left.get(i);
        left.subList(0, ptr).clear();

        if(n == ptr)
            return n;

        while(ptr < n){
            char[] b4 = new char[4];
            int r = read4(b4);
            if(r == 0)
                return ptr;

            int min2 = Math.min(r, n - ptr);
            for(int i = 0; i < min2; i++)
                buf[ptr++] = b4[i];

            if(min2 < r)
                for(int i = min2; i < r; i++)
                    left.add(b4[i]);
        }

        return ptr;
    }

    private int read4(char[] chs) {
        return 4;
    }
}
