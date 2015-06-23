package lesson8;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Sergius on 19.06.2015.
 */
public class App03 {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        Stream<Integer> stream0 = set.stream();
        Stream<String> stream1 = stream0.map(k -> k.toString());
        List<String> list = stream1.collect(Collectors.toList());

        System.out.println(list);
    }
}
