package interview.linkdin;

/*
 * check careercup.
 * 
 * Transform a+b*c-d to abc*d-+
 */
public class InorderPostOrderTransformation {
	/*
	 * suppose we have only "+-*\/" operators and the input is valid
	 * what if we have parenthesis?
	 */
	public String transform(String input) {
		StringBuilder builder = new StringBuilder();
		char[] ops = new char[1024];
		int index = 0;
		for(int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if(!isOperator(ch)) {
				builder.append(ch);
				continue;
			}
			
			while(index > 0 && precedence(ops[index-1], ch)) {
				char op = ops[--index];
				builder.append(op);
			}
			
			ops[index++] = ch;
		}
		
		while(index > 0)
			builder.append(ops[--index]);
		
		return builder.toString();
	}
	
	private boolean precedence(char ch1, char ch2) {
		if(ch1 == '+' || ch1 == '-')
			return false;
		else if(ch1 == '*' || ch1 == '/')
			return ch2 == '+' || ch2 == '-';
		
		return false;
	}
	
	private boolean isOperator(char ch) {
		return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	}
	
	public static void main(String[] args) {
		System.out.println(new InorderPostOrderTransformation().transform("a+b*c-d"));
		System.out.println(new InorderPostOrderTransformation().transform("a+b*c-d/e*f"));

	}
}