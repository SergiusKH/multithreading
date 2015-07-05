package lesson15;

import java.util.stream.LongStream;

/**
 * Created by Sergius on 05.07.2015.
 */
public class App04_stream_1_non_assoc {

    public static void main(String[] args) {

        long result = LongStream.range(0, 1_000_000)
                .filter(x -> x % 3 != 0)
                .filter(x -> x % 5 != 0)
                .reduce(0, (x, y) -> 31 * x + y);
        System.out.println(result);

        // +* min max: assoc + comm
        // 1 + (2 + 3) == (1 + 2) + 3
        //       1 + 2 == 2 + 1

        // "" + "": assoc + !comm
        // "hello " + ("wo" + "rld") == ("hello " + "wo") + "rld"
        //       "hello " + "world" != "world" + "hello"

        // 31 * x + y: !assoc + !comm

    }
}
