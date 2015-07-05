package lesson16;

import org.jcsp.lang.*;
import org.jcsp.plugNplay.ints.Delta2Int;
import org.jcsp.plugNplay.ints.PlusInt;
import org.jcsp.plugNplay.ints.PrefixInt;

/**
 * Created by Sergius on 05.07.2015.
 */
public class IntegrateInt implements CSProcess {
    private final ChannelInputInt in;
    private final ChannelOutputInt out;

    public IntegrateInt(ChannelInputInt in, ChannelOutputInt out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        One2OneChannelInt a = Channel.one2oneInt();
        One2OneChannelInt b = Channel.one2oneInt();
        One2OneChannelInt c = Channel.one2oneInt();

        new Parallel(
                new CSProcess[] {
                        new PlusInt(in, c.in(), a.out()),
                        new Delta2Int(a.in(), out, b.out()),
                        new PrefixInt(0, b.in(), c.out())
                }
        ).run();
    }
}
