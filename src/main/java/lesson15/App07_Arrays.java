package lesson15;

import java.util.Arrays;

/**
 * Created by Sergius on 05.07.2015.
 */
public class App07_Arrays {
    public static void main(String[] args) {
        int[] array = {5, 2, 3, 0 ,1, 4};
        System.out.println(Arrays.toString(array));

        Arrays.parallelSort(array);
        System.out.println(Arrays.toString(array));

        // ---
        // [a b c d] -> [a a+b a+b+c a+b+c+d]
        Arrays.parallelPrefix(array, (x, y) -> x + y);
        System.out.println(Arrays.toString(array));

        // ---
        // [10 + 0, 10 + 1, 10 + 2, 10 + 3]
        Arrays.parallelSetAll(array, index -> 10 + index);
        System.out.println(Arrays.toString(array));


    }
}
