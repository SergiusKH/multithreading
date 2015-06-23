package lesson6;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Sergius on 08.06.2015.
 */
public class Cache {
//    private Map<Integer, String> map = new Hashtable<>();
//    private Map<Integer, String> map = Collections.synchronizedMap(new HashMap<Integer, String>());
    private Map<Integer, String> map = new ConcurrentHashMap<>();

    public String get(Object key) {
        return map.get(key);
    }

    public String put(Integer key, String value) {
        return map.put(key, value);
    }

    public <K, V> Map<K, V> synchronizedMap(Map<K, V> m) {
        return new MyMap<>(m);
    }

    private static class MyMap<T0, T1> implements Map<T0, T1> {

        private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
        private final Lock rLock = rwLock.readLock();
        private final Lock wLock = rwLock.writeLock();
        private final Map<T0, T1> m;

        public MyMap(Map<T0, T1> m) {
            this.m = m;
        }

        @Override
        public int size() {
            rLock.lock();
            try {
                return m.size();
            } finally {
                rLock.unlock();
            }
        }

        @Override
        public boolean isEmpty() {
            rLock.lock();
            try {
                return m.isEmpty();
            } finally {
                rLock.unlock();
            }
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public T1 get(Object key) {
            return null;
        }

        @Override
        public T1 put(T0 key, T1 value) {
            return null;
        }

        @Override
        public T1 remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends T0, ? extends T1> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<T0> keySet() {
            return null;
        }

        @Override
        public Collection<T1> values() {
            return null;
        }

        @Override
        public Set<Entry<T0, T1>> entrySet() {
            return null;
        }
    }
}

class X {
    public static void main(String[] args) throws InterruptedException {

    }
}
