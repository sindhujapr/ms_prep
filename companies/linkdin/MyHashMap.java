package interview.linkdin;

/*
 * this implementation doesn't consider null key. Also it doesn't use
 * Object.equals() to evaluate the equality of two entries.
 */
public class MyHashMap {
    private static final double ratio = 0.75;

    // initial bucket number
    private int size = 1024;
    private Entry[] buckets = null;
    private int count = 0;
    
    // we can keep the hash code as a field
    private static class Entry {
        int hash, key, val;
        Entry next;
        public Entry(int hash, int key, int val) {
            this.hash = hash;
            this.key = key;
            this.val = val;
        }
    }
    
    public MyHashMap() {
        buckets = new Entry[size];
    }

    private int hashcode(int key) {
        int res = 0;
        while(key > 0) {
            res = res * 11 + key % 10;
            key /= 10;
        }
        return res;
    }
    
    private int indexOfHash(int hash, int size) {
        return hash & (size-1);
    }
    
    public int get(int key) {
        int hash = hashcode(key);
        int index = indexOfHash(hash, size);
        for(Entry entry = buckets[index]; entry != null; entry = entry.next) {
            if(entry.key == key)
                return entry.val;
        }
        
        return Integer.MIN_VALUE;
    }
    
    public int put(int key, int val) {
        int hash = hashcode(key);
        int index = indexOfHash(hash, size);
        for(Entry entry = buckets[index]; entry != null; entry = entry.next) {
            if(entry.key == key) {
                int temp = entry.val;
                entry.val = val;
                return temp;
            }
        }
        
        Entry entry = new Entry(hash, key, val);
        addEntry(entry);
        return Integer.MIN_VALUE;
    }
    
    private void addEntry(Entry entry) {
        double actualRatio = (double)count/(double) size;
        if(Double.compare(actualRatio, ratio) > 0)
            resize();

        // here we need to caculate the index based on the new size
        int index = indexOfHash(entry.hash, size);
        entry.next = buckets[index];
        buckets[index] = entry;
        count++;
    }
    
    private void resize() {
        int newSize = size << 1;
        Entry[] newBuckets = new Entry[newSize];
        
        for(int i = 0; i < size; i++) {
            for(Entry entry = buckets[i]; entry != null; ) {
                Entry next = entry.next;
                
                int index = indexOfHash(entry.hash, newSize);
                entry.next = newBuckets[index];
                newBuckets[index] = entry;
                
                entry = next;
            }
        }

        size = newSize;
        buckets = newBuckets;
        System.err.println("resized on count " + count);
    }
    
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        for(int i = 0; i < 1025; i++)
            map.put(i, i*i);
        for(int i = 0; i < 1025; i++) {
            System.err.print(map.get(i) + " ");
            if(i % 50 == 0)
                System.err.println();
        }
    }
}
