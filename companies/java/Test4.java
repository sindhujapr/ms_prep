package interview.java;

import java.util.HashMap;
import java.util.Hashtable;

interface DrivingUtilities {}
class FourWheeler implements DrivingUtilities {}
class Car extends FourWheeler {}
class Truck extends FourWheeler {}
class Bus extends FourWheeler {}
class Crane extends FourWheeler {}

public class Test4 {
    private Test4 t = new Test4();

    public Test4() { System.out.println("ehl");}

    public static void main(String[] args) {
        /* stack overflow. See puzzle 40 of Java Puzzlers */
//      Test4 tt = new Test4();
        
        DrivingUtilities du = null;
        FourWheeler fw = null;
        Truck myTruck = new Truck();
        du =  myTruck;
        fw = new Crane();
//      fw = du;
        
        Hashtable<String, String> hashTable = new Hashtable<String, String>(); 
//      hashTable.put(null, null);
//      System.out.println(hashTable.get(null));
        
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(null, null);
        System.out.println(hashMap.get(null));
        
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().freeMemory());
    }
}