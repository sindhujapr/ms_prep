package fk;

/**
 * Created by qingcunz on 10/17/14.
 */
public class ExceptionTest {
    public static void main(String[] args) {
        ExceptionTest instance = new ExceptionTest();
        int val = instance.throwException(1);
        System.out.println(val);
    }

    public int throwException(int val) {
        try {
            if(val == 1)
                return f(val);
        } catch (Exception e) {
                System.out.println("Exception");
        }

        return 2;
    }

    public int f(int val) throws Exception {
        if(val == 1)
            throw new Exception("fuck");
        return 1;
    }
}
