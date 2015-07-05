package lesson16;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.CSTimer;
import org.jcsp.lang.ChannelOutputInt;

/**
 * Created by Sergius on 05.07.2015.
 */
public class Producer implements CSProcess {
    private final ChannelOutputInt out;
    private int k;

    public Producer(ChannelOutputInt out, int i) {
        this.out = out;
        this.k = i;
    }

    @Override
    public void run() {
        CSTimer timer = new CSTimer();
        while (true) {
            out.write(k++);
            timer.sleep(250);
        }

    }
}
