package interview.java;

/* http://stackoverflow.com/questions/404804/java-covariants
 * This is because in Java member variables don't override, they shadow (unlike methods).
 * Both A and B have a variable x. Since c is declared to be of type CovarientTest, 
 * the return of getObj() is implicitly an A, not B, so you get A's x, not B's x.
 */
public class CovariantTest {
    public AA getObj() {
        return new AA();
    }
    
    public static void main(String[] args) {
        CovariantTest c = new SubCovariantTest();
        System.out.println(c.getObj().x);
        System.out.println(c.getObj().get());

        SubCovariantTest sub = (SubCovariantTest) c;
        System.out.println(sub.getObj().x);
        System.out.println(sub.getObj().get());
    }
}

class SubCovariantTest extends CovariantTest {
    public BB getObj() {
        return new BB();
    }
}

class AA {
    public int x = 5;
    public int get() { return x; }
}

class BB extends AA {
	// This field shadows the one from parent class
    public int x = 6;
    @Override
    public int get() { return x; }
}