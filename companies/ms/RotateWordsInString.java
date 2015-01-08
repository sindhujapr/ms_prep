package interview.ms;

/*
 * Reverse the sequences of words in the sentence, but not the characters.
 * eg, the output of "I am a student" should be "student a am I".
 */
public class RotateWordsInString {
    
    public static void main(String[] args) {
	String sentence = "I am a student fuck ccp";
	rotate(sentence);
    }

    private static void rotate(String sentence) {
	char[] words = sentence.toCharArray();
	
	/*
	 * here we set the lower limit of the index to -1. if we set it to 0,
	 * the first word in the sentence will not get a chance to output.
	 */
	for (int i = words.length-1; i >= -1; i--) {
	    if(i >= 0 && words[i] != ' ') {
		continue;
	    } else {
		for (int j = i+1; j < words.length && words[j] != ' '; j++) {
		    System.out.print(words[j]);
		}
		System.out.print(' ');
	    }
	}
    }
}