package lesson16;

import java.util.Arrays;

/**
 * Created by Sergius on 05.07.2015.
 */
public class EratosthenesSeq {
    public static void main(String[] args) {
        int N = 1000;

        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, 2, N, true);

        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i; i * j <= N ; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        System.out.println(Arrays.toString(isPrime));
    }
}
