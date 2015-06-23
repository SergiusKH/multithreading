package lesson9;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Sergius on 21.06.2015.
 */
public class App00 {

    // манойд
    // 1) асациотивный оператор (+ * min       max)
    // 2) нетральный элемент    (0 1 Max_value Min_value)
    // Манада

    public static void main(String[] args) {

        Stream<Integer> stream = Stream.iterate(0, k -> k + 1);
        Optional<Integer> sum = stream
                .limit(0)
                .parallel()
                .reduce((x, y) -> x + y);

        if (sum.isPresent()) {
            System.out.println(sum.get());
        } else {
            System.out.println("Empty");
        }

//        System.out.println(sum);

    }


}
