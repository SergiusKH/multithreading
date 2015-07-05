package lesson16;

import org.jcsp.lang.*;
import org.jcsp.plugNplay.ints.NumbersInt;

/**
 * Created by Sergius on 05.07.2015.
 */
public class Primes implements CSProcess {
    private final ChannelOutputInt out;

    public Primes(ChannelOutputInt out) {
        this.out = out;
    }

    @Override
    public void run() {
        out.write(2);
        One2OneChannelInt c = Channel.one2oneInt();
        new Parallel(
                new CSProcess[] {
                        new NumbersForm(3, 2, c.out()),
                        new Sieve(c.in(), out)
                }
        ).run();
    }
}
