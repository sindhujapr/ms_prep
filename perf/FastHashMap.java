package perf;

import java.util.HashMap;

import cern.colt.map.OpenIntIntHashMap;
import cern.colt.map.OpenIntObjectHashMap;
import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.map.hash.TIntObjectHashMap;

public class FastHashMap {

    public static void main(String args[]) {

        System.out.println("1st line: time used(s)\n2nd line: heap memory used so far(MB)");

        int n = 10000000;

        long startTime = System.nanoTime();
        long startHeapSize = Runtime.getRuntime().freeMemory();

        // BEGIN: benchmark for Java's built-in hashmap
        System.out.println("\n===== Java's built-in HashMap =====");
        HashMap<Integer, Object> jIntIntMap = new HashMap<Integer, Object>();

        System.out.println("\n-- " + n + " puts(key, value) --");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            jIntIntMap.put(i, new float[] { 0f, 1f, 2f, 3f, 4f });
        }
        System.out.println((System.nanoTime() - startTime) / 1000000000.0);
        System.out.println((startHeapSize - Runtime.getRuntime().freeMemory()) / 1048576.0);

        System.out.println("\n-- " + n + " gets(key) --");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            jIntIntMap.get(i);
        }
        System.out.println((System.nanoTime() - startTime) / 1000000000.0);
        System.out.println((startHeapSize - Runtime.getRuntime().freeMemory()) / 1048576.0);

        System.out.println("\n-- " + n + " containsKey(key) --");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            jIntIntMap.containsKey(i);
        }
        System.out.println((System.nanoTime() - startTime) / 1000000000.0);
        System.out.println((startHeapSize - Runtime.getRuntime().freeMemory()) / 1048576.0);
        // END

        // BEGIN: benchmark for Trove's TIntIntHashMap
        System.out.println("\n===== Trove's TIntIntHashMap =====");
        TIntObjectHashMap<float[]> tIntIntMap = new TIntObjectHashMap<float[]>();

        System.out.println("\n-- " + n + " puts(key, value) --");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            tIntIntMap.put(i, new float[] { 0f, 1f, 2f, 3f, 4f });
        }
        System.out.println((System.nanoTime() - startTime) / 1000000000.0);
        System.out.println((startHeapSize - Runtime.getRuntime().freeMemory()) / 1048576.0);

        System.out.println("\n-- " + n + " gets(key) --");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            tIntIntMap.get(i);
        }
        System.out.println((System.nanoTime() - startTime) / 1000000000.0);
        System.out.println((startHeapSize - Runtime.getRuntime().freeMemory()) / 1048576.0);

        System.out.println("\n-- " + n + " containsKey(key) --");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            tIntIntMap.containsKey(i);
        }
        System.out.println((System.nanoTime() - startTime) / 1000000000.0);
        System.out.println((startHeapSize - Runtime.getRuntime().freeMemory()) / 1048576.0);
        // END

        // BEGIN: benchmark for Colt's OpenIntIntHashMap
        System.out.println("\n===== Colt's OpenIntIntHashMap =====");
        OpenIntObjectHashMap cIntIntMap = new OpenIntObjectHashMap();

        System.out.println("\n-- " + n + " puts(key, value) --");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            cIntIntMap.put(i, new float[] { 0f, 1f, 2f, 3f, 4f });
        }
        System.out.println((System.nanoTime() - startTime) / 1000000000.0);
        System.out.println((startHeapSize - Runtime.getRuntime().freeMemory()) / 1048576.0);

        System.out.println("\n-- " + n + " gets(key) --");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            cIntIntMap.get(i);
        }
        System.out.println((System.nanoTime() - startTime) / 1000000000.0);
        System.out.println((startHeapSize - Runtime.getRuntime().freeMemory()) / 1048576.0);

        System.out.println("\n-- " + n + " containsKey(key) --");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            cIntIntMap.containsKey(i);
        }
        System.out.println((System.nanoTime() - startTime) / 1000000000.0);
        System.out.println((startHeapSize - Runtime.getRuntime().freeMemory()) / 1048576.0);
        // END

    }

}