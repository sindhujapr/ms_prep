package interview.ms;

public class StringBufferPerf {
    public static void main(String[] args) {
        String str = makeSentence(new String[] {"hello", "world"});
    }

    public static String makeSentence(String[] words) {
        StringBuffer sentence = new StringBuffer();
        for (String w : words)
            sentence.append(w);
        
        return sentence.toString();
    }
}
