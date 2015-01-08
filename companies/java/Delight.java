package interview.java;

public class Delight {
    public static void main(String[] args) {
        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            if (b == 0x90) // 9*16=144, Byte.MAX_VALUE=127
                System.out.print("Joy!");
            System.out.print(b);
        }
        
        System.out.println("finished");
    }
}