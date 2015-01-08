package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

interface Computable<K, V> {
    public V compute(K arg);
}

// Java Concurrency in Practice
public class Memoizer<K, V> {
    private final ConcurrentHashMap<K, Future<V>> cache = new ConcurrentHashMap<K, Future<V>>();
    private final Computable<K, V> c;
    
    public Memoizer(Computable<K, V> c) { this.c = c; }
    
    public V compute(final K key) throws InterruptedException {
        // why use while(true)?
        while(true) {
            Future<V> f = cache.get(key);
            if(f == null) {
                Callable<V> eval = new Callable<V>() {
                    public V call() throws InterruptedException {
                        return c.compute(key);
                    }
                };
                
                // FutureTask implements Future interface and provides method run()
                FutureTask<V> ft = new FutureTask<V>(eval);
                // only one thread will be successful and thus null as return value
                f = cache.putIfAbsent(key, ft);
                if(f == null) {
                    // why we need to assign ft to f? It seems useless
                    f = ft;
                    ft.run();
                }
            }
            
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(key, f);
            } catch (ExecutionException ex) {
                throw new InterruptedException(ex.getMessage());
            }
        }
    }
}