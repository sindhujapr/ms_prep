package lc;

public class AddBinary {
	public static void main(String[] args) {
		AddBinary instance = new AddBinary();
		System.out.println(instance.addBinary("0", "1"));
	}
	
    public String addBinary(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int carrier = 0;
        for(int i = a.length()-1, j = b.length()-1; i >= 0 || j >= 0 || carrier > 0; i--, j--) {
            int v1 = (i >= 0 ? (a.charAt(i) - '0') : 0);
            int v2 = (j >= 0 ? (b.charAt(j) - '0') : 0);
            int sum = v1+v2+carrier;
            builder.insert(0, sum % 2);
            carrier = sum / 2;
        }
        
        return builder.toString();
    }
}
