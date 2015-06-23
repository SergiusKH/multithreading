package lesson8;

import java.util.Arrays;

/**
 * Created by Sergius on 17.06.2015.
 */
public class App00 {

    public static void main(String[] args) {
        new Thread(App00::printHello).start();

        new Thread(()-> System.out.println("Hello!"));

        Arrays.asList("A", "BB", "CCC")
                .parallelStream()
                .map(str -> str.length())
                .filter(k -> k % 2 == 1)
                .forEach(System.out::println);
    }

    public static void printHello() {
        System.out.println("Hello!");
    }
}
