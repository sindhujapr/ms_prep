package geeksforgeeks.bit;

public class BigLittleEndian {
    public static void main(String[] args) {
        endian();
    }
    
    public static void endian() {
        int num = 0x00000001;
        
        num >>= 24;
        if(num == 0x00000001)
            System.out.println("little endian");
        else
            System.out.println("big endian");
    }
}