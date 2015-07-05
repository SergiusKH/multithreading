package lesson16;

import org.jcsp.lang.Any2AnyChannelInt;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.Parallel;

/**
 * Created by Sergius on 05.07.2015.
 */
public class App05_any2any {
    public static void main(String[] args) {
        Any2AnyChannelInt chan = Channel.any2anyInt();
        new Parallel(
                new CSProcess[] {
                        new Producer(chan.out(), 0),
                        new Producer(chan.out(), 1000),
                        new Consumer(chan.in(), "A"),
                        new Consumer(chan.in(), "B")
                }

        ).run();
    }
}
