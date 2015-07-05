package lesson15;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sergius on 05.07.2015.
 */
public class App06_CHM {
    public static void main(String[] args) {
        ConcurrentHashMap<Long, Long> map = new ConcurrentHashMap<>();
        int cpuCount = Runtime.getRuntime().availableProcessors();

        Long result0 = map.search(cpuCount,
                (key, value) -> key.equals(value) ? key : null);

        Long result1 = map.reduceKeys(cpuCount,
                (key0, key1) -> key0 + key1);
    }
}
