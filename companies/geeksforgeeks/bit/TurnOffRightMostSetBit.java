package geeksforgeeks.bit;

public class TurnOffRightMostSetBit {
    public static void main(String[] args) {
        System.out.println(turnOff(12));
    }
    
    public static int turnOff(int num) {
        return num & (num-1);
    }
}