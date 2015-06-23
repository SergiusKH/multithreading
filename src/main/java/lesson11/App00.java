package lesson11;

import java.util.Optional;

import static java.util.stream.Stream.*;

/**
 * Created by Sergius on 23.06.2015.
 */
public class App00 {
/*
    Редукция не на монойде, а исключительно на ассоциативной операции
    необходимо приводит к монаде Optionnal / Maybe
 */
    public static void main(String[] args) {
        // Редукция на монаде (ассоциативный оператор + нетралный элемент)
        Integer sum0 = of(1, 2, 3).reduce(0, (x, y) -> x + y);
        System.out.println(sum0);

        // МОНАДА: Optional / Just
        //Редукция на ассоциативной оперции
        Optional<Integer> sum1 = of(1, 2, 3).reduce((x, y) -> x + y);
        System.out.println(sum1);

        //МАНАДА: Optional / Nothing
        //Редукция на ассоциативной оперции
        Optional<Integer> sum2 = of(1, 2, 3).filter(x -> x > 10).reduce((x, y) -> x + y);
        System.out.println(sum2);
    }
}
