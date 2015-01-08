package interview.java;

public class LongDivision {
    public static void main(String[] args) {
        // By default, 24 is int but not long. We need to explicitly convert it to long by appending 'L'
        final long MICRO_PER_DAY = 24L * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24L * 60 * 60 * 1000;
        
        System.out.println(MICRO_PER_DAY / MILLIS_PER_DAY);
        
        // It's 'l' but not number 'one'!!!
        System.out.println(12345 + 5432l);
        
        // Avoid using mixed-type computation
        // mixed-type computation: long and int
        System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));
    }
}