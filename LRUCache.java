package lc;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    static class Entry {
        int key, val;
        private Entry next, prev;
        public Entry(int key, int val) { this.key = key; this.val = val; }
    }

    private Entry head, tail;
    private int capacity;
    /*
     * HashMap is used for quick lookup (where is the node) and it
     * also provides the size of the linked list
     */
    private Map<Integer, Entry> map = new HashMap<Integer, Entry>();
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = this.tail = null;
    }
    
    public int get(int key) {
        Entry entry = map.get(key);
        
        if(entry == null)
            return -1;

        enQueue(entry);
        return entry.val;
    }
    
    public void set(int key, int value) {
        Entry entry = map.get(key);
        if(entry == null) {
            entry = new Entry(key, value);
            map.put(key, entry);
        } else {
            entry.val = value;
        }
        enQueue(entry);    
        
        if(map.size() > capacity) {
            map.remove(tail.key);

            // here we're sure tail.prev is not null
            tail = tail.prev;
            tail.next = null;
        }
    }
    
    /*
     * Move/insert the entry to the head of the list
     * entry may be new (prev = next = null) or from the existing linked list
     */
    private void enQueue(Entry entry) {
    	if(entry == head)
    		return;
    	
        if(head == null) {
            head = tail = entry;
            return;
        }
        
        if(entry.prev != null)
            entry.prev.next = entry.next;
                
        if(entry == tail)
            tail = entry.prev;
        else if(entry.next != null)
            entry.next.prev = entry.prev;
                
        entry.next = head;
        head.prev = entry;
        head = entry;
        head.prev = null; 
    }
    
    public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		cache.set(2,1);
		cache.set(2,2);
		System.out.println(cache.get(2));
		cache.set(1,1);
		cache.set(4,1);
		System.err.println(cache.get(2));
	}
}