package interview.java;

class Type11 {
    public static void main(String[] args) {
        String s = null;
        System.out.println(s instanceof String);
    }
}

class Type22 {
    public static void main(String[] args) {
    	Type22 t = new Type22();
    	System.out.println(t instanceof Type22);
    	// Doesn't compile. Type22 and String must be one another's subtype
//        System.out.println(t instanceof String);
    }
}

public class Type3 {
    public static void main(String args[]) {
        Type3 t3 = (Type3) new Object();
    }
}