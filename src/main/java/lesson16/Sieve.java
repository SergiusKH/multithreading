package lesson16;

import org.jcsp.lang.*;

/**
 * Created by Sergius on 05.07.2015.
 */
public class Sieve implements CSProcess {
    private final ChannelOutputInt out;
    private final ChannelInputInt in;

    public Sieve(ChannelInputInt in, ChannelOutputInt out) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void run() {
        int n = in.read();
        out.write(n);
        One2OneChannelInt c = Channel.one2oneInt();
        new Parallel(
                new CSProcess[] {
                        new NoMultiples(n, in, c.out()),
                        new Sieve(c.in(), out)
                }
        ).run();
    }
}
