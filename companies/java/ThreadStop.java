package interview.java;

public class ThreadStop {
    public static void sneakyThrow(Throwable t) {
        Thread.currentThread().stop(t);
    }
}