package euler;

public class MultiplesOf3And5 {
    private static final int limit = 1000;

    public static void main(String[] args) {
    int sum = 0;
    for (int i = 1; i < limit; i++) {
        if (i % 3 == 0 || i % 5 == 0)
        sum += i;
    }

    System.out.println(sum);
    }
}