package lesson16;

import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInputInt;

/**
 * Created by Sergius on 05.07.2015.
 */
public class Consumer implements CSProcess {
    private final ChannelInputInt in;
    private String name;

    public Consumer(ChannelInputInt in, String dst) {
        this.in = in;
        this.name = dst;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(name + ": " + in.read());
        }

    }
}
