package consistent.hashing;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

interface HashFunction {
    public int hash(Object key);
}

/*
 * https://weblogs.java.net/blog/tomwhite/archive/2007/11/consistent_hash.html
 * http://www8.org/w8-papers/2a-webserver/caching/paper2.html#chash1
 */
public class ConsistentHash<T> {

    private final HashFunction hashFunction;
    private final int numberOfReplicas;
    
    /*
     * This map only holds the hash values of cache nodes. Each node is mapped
     * to an interval of hash values of objects.
     */
    private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;

        for (T node : nodes)
            add(node);
    }

    /*
     * This is so-called "virtual node". Replica (virtual node) #i will
     * be mapped to hashFunction.hash(node.toString()+i). In practice, the
     * # of replicas for each cache node should be adjusted according to
     * the capacity of the node. That means, powerful node should have
     * larger # of replicas.
     */
    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++)
            circle.put(hashFunction.hash(node.toString() + i), node);
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++)
            circle.remove(hashFunction.hash(node.toString() + i));
    }

    /*
     * return the cache node for a given object
     */
    public T get(Object key) {
        if (circle.isEmpty())
            return null;

        int hash = hashFunction.hash(key);
        /*
         * Most of the time there will not be a node that has the same hash value as
         * the object. But if there is, just return the cache node.
         */
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            // treat the map as a circle
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }

        return circle.get(hash);
    }

}