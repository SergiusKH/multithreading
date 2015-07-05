package lesson16;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutputInt;

/**
 * Created by Sergius on 05.07.2015.
 */
public class NoMultiples implements CSProcess {
    private final int n;
    private final ChannelInputInt in;
    private final ChannelOutputInt out;


    public NoMultiples(final int n, final ChannelInputInt in, final ChannelOutputInt out) {
        this.n = n;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        while (true) {
            int a = in.read();
            if ((a % n) != 0) {
                out.write(a);
            }
        }
    }
}
