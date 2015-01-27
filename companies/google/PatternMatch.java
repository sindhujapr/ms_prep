// check if a pattern occurs in a character stream, using circular buffer
public class PatternMatch {
    public static boolean compare(String src, String pattern) {
        CircularBuffer cBuf = new CircularBuffer(pattern.length());
        for(char ch : src.toCharArray()) {
            cBuf.append(ch);
            if(cBuf.compare(pattern))
                return true;
        }
        return false;
    }

    private static class CircularBuffer {
        private char[] buf = null;
        private int start = 0, len = 0;
        public CircularBuffer(int size) {
            buf = new char[size];
        }

        public boolean compare(String str) {
            if(len < str.length())
                return false;

            for(int i = 0, j = 0; i < len && j < str.length(); i++, j++) {
                if(buf[(start+i)%buf.length] != str.charAt(j))
                    return false;
            }
            return true;
        }

        public void append(char ch) {
            int end = (start + len) % buf.length;

            if(len < buf.length) {
                len++;
            } else {
                start++;
                start %= buf.length;
            }
            buf[end] = ch;
        }
    }
}
