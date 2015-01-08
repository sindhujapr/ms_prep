package geeksforgeeks.bit;

public class AddOne {
    public static void main(String[] args) {
        for(int i = 1; i < 100; i++)
            if(addOne(i) != i+1 || addOne2(i) != i+1)
                System.out.println(i);
    }
    
    public static int addOne(int num) {
        int i = 0;
        for(; i < 32 && (num & (1 << i)) != 0; i++)
            num ^= 1 << i;
            
        return num | (1 << i);
    }
    
    public static int addOne2(int num) {
        return -(~num);
    }
}