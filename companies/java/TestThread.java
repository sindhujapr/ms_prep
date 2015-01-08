package interview.java;

abstract class SuperTest
{
    static int i = 10;
}


public class TestThread extends SuperTest implements Inter {
    final static int i;
    static {i =9; }
    public void f() {
        System.out.println("I is " + i);
    }
    
    public static void main(String[] args) {
        Inter t = new TestThread();
        t.f();
    }
}

interface Inter {
    int i = 1;
    void f();
}
