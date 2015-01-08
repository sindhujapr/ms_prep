public class Read4 {
    /*
    * read len chars to buf, which is supposed to be large enough.
    * Didn't test with leetcode
    */
    public int read(char[] buf, int len) {
        int readBytes = 0;
        char[] chs = new char[4];
        while(readBytes < len) {
            int size = read4(chs);

            /*
             * if size == 4 and len-readBytes >= 4, we copy 4 chars
             * else we copy Math.min(size, len-readBytes) chars
             */
            int bytes = size == 4 && len-readBytes >= 4 ? 4 : Math.min(size, len-readBytes);
            System.arraycopy(chs, 0, buf, readBytes, bytes);
            readBytes += bytes;

            if(size < 4)
                break;
        }

        return readBytes;
    }

    private int read4(char[] chs) {
        return 4;
    }
}
