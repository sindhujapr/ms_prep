package interview.java;


public class StackOverflow {
    /*
     * If we define a member of the same type as the enclosing class, it should be declared as static.
     * Otherwise there will be stack overflow exception.
     * Usually it's also declared as final, as we do in singleton pattern.
     */
    private StackOverflow internalInstance = new StackOverflow();

    public StackOverflow() {
//      throw new Exception("I'm not coming out");
    }
    
    public static void main(String[] args) {
        try {
            StackOverflow b = new StackOverflow();
            System.out.println("surprised");
        } catch (Exception e) {
            System.out.println("I told you so");
        }
    }
}