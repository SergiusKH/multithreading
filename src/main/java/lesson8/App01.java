package lesson8;

import java.util.stream.Stream;

/**
 * Created by Sergius on 18.06.2015.
 */
public class App01 {

    public static void main(String[] args) {
        Stream.iterate(0L, k -> k + 1)
                .filter(k -> k % 3 == 2)
                .map(k -> "~" + k)
                .limit(20)
                .forEach((k) -> System.out.println(k));
    }
}
