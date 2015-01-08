package javautils;

interface Root {
    public void f();
}

abstract class SuperClass1 {
    abstract public void g();
}

abstract class SuperClass2 {
    abstract public void h();
}

/*
 * simulated multi-inheritance
 */
public class EnclosingClass extends SuperClass1 implements Root {
    private static final int cnt = 10;
    private final int size = 100;

    static class NestedClass extends SuperClass2 {
    private static final int cnt = 20;

    @Override
    public void h() {
        System.out.println("Nested class: " + cnt);
    }
    }

    class InnerClass extends SuperClass2 {
    private static final int cnt = 30;

    @Override
    public void h() {
        /*
         * this doesn't work. we must use EnclosingClass.this.g(): this.g();
         */
        EnclosingClass.this.g();
        System.out.println(EnclosingClass.this.size);
        /*
         * this is ok but there is a warning.
         */
        System.out.println(EnclosingClass.this.cnt);
        System.out.println("Inner class: " + cnt + "\t" + size);
    }
    }

    @Override
    public void f() {
    System.out.println("Enclosing class f(): " + cnt);
    }

    @Override
    public void g() {
    System.out.println("Enclosing class g(): " + cnt);
    }

    public void h() {
    InnerClass i = new InnerClass();
    i.h();

    /*
     * anonymous inner class
     */
    Root r = new Root() {
        /*
         * i cannot be static but can be static final. static (only) members
         * can only be defined in enclosing class.
         */
        private static final int j = 1;

        @Override
        public void f() {
        System.out.println("inside " + j);
        // cannot access non-final variable defined in enclosing block
//      i.h();
        }
    };
    r.f();

    /*
     * local inner class
     */
    class RootChild implements Root {
        /*
         * see comment for static member in above anonymous class
         */
        private static final int j = 5;

        @Override
        public void f() {
        System.out.println("RootChild.f() " + j);
        
        // cannot access non-final variable defined in enclosing block
//      i.h();
        }
    }
    Root r1 = new RootChild();
    r1.f();
    }
    
    public static void main(String[] args) {
    EnclosingClass instance = new EnclosingClass();
    instance.f();
    instance.g();
    instance.h();

    NestedClass n = new EnclosingClass.NestedClass();
    n.h();

    InnerClass i = instance.new InnerClass();
    i.h();

    int p = Integer.class.cast((int)'a');
    System.out.println(p);
    }
}