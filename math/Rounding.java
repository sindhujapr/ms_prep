package math;

// http://stackoverflow.com/questions/9902968/why-does-math-round0-49999999999999994-return-1
public class Rounding {
    static void print(double d) {
        System.out.printf("%016x\n", Double.doubleToLongBits(d));
    }

    public static void main(String args[]) {
        double a = 0.5;
        /* 
         * try to remove the last '4' and see.
         * try to change '4' to '1' to see.
         */
        double b = 0.49999999999999994;

        print(a);
        print(b);
        print(a+b);
        print(1.0);
    }
}