package lesson16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sergius on 05.07.2015.
 */
public class EratosthenesIter implements Iterator<Integer> {
    private List<Integer> primes = new ArrayList<>(Arrays.asList(2));

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int k= primes.get(primes.size() - 1);
        next_probe:
        while (true) {
            k++;
            for (int prime : primes) {
                if (k % prime == 0) {
                    continue next_probe;
                }
            }
            primes.add(k);
            return k;
        }
    }
}
