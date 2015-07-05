package lesson16;

import java.util.Iterator;

/**
 * Created by Sergius on 05.07.2015.
 */
public class EratosthenesItesDemo {
    public static void main(String[] args) {
        Iterator<Integer> primes = new EratosthenesIter();
        System.out.println(primes.next() + ", ");
        System.out.println(primes.next() + ", ");
        System.out.println(primes.next() + ", ");
        System.out.println(primes.next() + ", ");
        System.out.println(primes.next() + ", ");
        System.out.println(primes.next() + ", ");
        System.out.println(primes.next() + ", ");
        System.out.println(primes.next() + ", ");
        System.out.println(primes.next() + ", ");
        System.out.println(primes.next() + ", ");
    }
}
