package lesson16;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutputInt;

/**
 * Created by Sergius on 05.07.2015.
 */
public class NumbersForm implements CSProcess {
    private final ChannelOutputInt out;
    private final int start;
    private final int increment;

    public NumbersForm(final int start, final int increment, final ChannelOutputInt out) {
        this.start = start;
        this.increment = increment;
        this.out = out;
    }

    @Override
    public void run() {
        int n = start;
        while (true) {
            out.write(n);
            n += increment;
        }

    }
}
