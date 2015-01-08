package javautils;

class NativeCode {
    public native void displayHelloWorld();

    static {
    System.loadLibrary("hello");
    }

    public static void main(String[] args) {
    new NativeCode().displayHelloWorld();
    }
}