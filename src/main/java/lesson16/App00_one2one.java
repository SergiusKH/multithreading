package lesson16;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;

/**
 * Created by Sergius on 05.07.2015.
 */
public class App00_one2one {

    public static void main(String[] args) {
        One2OneChannelInt c = Channel.one2oneInt();
        new Parallel(
                new CSProcess[] {
                   new Producer(c.out(), 0), new Consumer(c.in(), "dst")
                }
        ).run();

    }
}
