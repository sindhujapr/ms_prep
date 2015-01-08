package general;

import java.util.HashMap;
import java.util.Map;

/*
 * http://gongxuns.blogspot.com/2013/11/design-and-implement-data-structure-for.html
 */
public class LRU<K, V> {
    private Map<K, V> cache = new HashMap<K, V>();
    // Must sort according to the count of each key
    private Map<K, Integer> counter = new HashMap<K, Integer>();
    private final int bound;
    
    public LRU(int bound) {
        this.bound = bound;
    }

    public V get(K key) {
        if(!cache.containsKey(key))
            return null;

        counter.put(key, counter.get(key)+1);
        return cache.get(key);
    }
    
    public void set(K key, V value) {
        // remove the entry with least count
        if(bound == cache.size()) {
            Map.Entry<K, Integer> ent = null;
            int min = Integer.MAX_VALUE;
            
            for(Map.Entry<K, Integer> entry : counter.entrySet()) {
                if(entry.getValue() < min) {
                    ent = entry;
                    min = entry.getValue();
                }
            }
            
            counter.remove(ent);
            cache.remove(ent.getKey());
        }
        
        cache.put(key, value);
        counter.put(key, 1);
    }
    
    public void print() {
        for(Map.Entry<K, V> entry : cache.entrySet())
            System.out.println(entry.getKey() + "\t" + entry.getValue());
    }
    
    public static void main(String[] args) {
        LRU<Integer, Integer> lru = new LRU<Integer, Integer>(4);
        
        lru.set(1, 101);
        lru.set(2, 102);
        lru.set(3, 103);
        lru.set(4, 104);
        
        System.out.println(lru.get(2));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
        
        lru.print();
        lru.set(5, 105);
        lru.print();
    }
}