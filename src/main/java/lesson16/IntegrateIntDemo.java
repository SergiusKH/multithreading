package lesson16;

import org.jcsp.lang.*;

/**
 * Created by Sergius on 05.07.2015.
 */
public class IntegrateIntDemo {
    public static void main(String[] args) {
        final One2OneChannelInt to = Channel.one2oneInt();
        final One2OneChannelInt from = Channel.one2oneInt();

        new Parallel(
                new CSProcess[] {
                        new CSProcess() {
                            @Override
                            public void run() {
                                CSTimer timer = new CSTimer();
                                while (true) {
                                    to.out().write(1);
                                    timer.sleep(250);
                                }
                            }
                        },
                        new CSProcess() {
                            @Override
                            public void run() {
                                while (true) {
                                    System.out.println(from.in().read());
                                }
                            }
                        },
                        // ----
                        // родная реализация
                        // org.jcsp.plugNplay.ints.IntegrateInt()
                        new IntegrateInt(to.in(), from.out())

                }
        ).run();
    }
}
