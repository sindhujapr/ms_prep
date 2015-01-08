package interview.java;

class A implements Cloneable {
    private int i;
    public A(int value) { i = value; }
    public int geti() { return i; }
    public void f() {
        try {
            /*
             * We can create an instance without calling the constructor
             * More strangely, we're calling super.clone(), not this.clone,
             * though it's the same effect. Thus, by implementing Cloneable interface,
             * we can change the behavior of method clone()
             */
            A instance = (A) super.clone();
            System.out.println(instance instanceof A);
            System.out.println("euqal? " + instance.equals(this));
            System.out.println(this + "\t" + instance);
            System.out.println(instance.geti());
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
    }
    
    /* This clone() will be invoked if we call this.clone(). But this is not a spec-compliant implementation
     * As a rule, if you override the clone() method in a non-final class, an object created by super.clone()
     * should be returned. Also, all classes in the inheritance chain should follow this rule. Thus finally
     * the object created by Object.clone() will be returned. Pls refer to above comment that Object.clone()
     * will return an instance of child class if it implements Cloneable
     */
    @Override
    public A clone() {
        System.out.println("clone() from child class");
        return new A(20);
    }
    
    public boolean equals(Object o) {
        if(o instanceof A)
            return ((A)o).geti() == geti();
        
        return false;
    }
    
    public int hashCode() {
        return 1;
    }
}

public class TestCloneable {
    public static void main(String[] args) {
        A a = new A(10);
        a.f();
    }
}