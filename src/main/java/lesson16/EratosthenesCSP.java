package lesson16;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.plugNplay.ints.PrinterInt;

/**
 * Created by Sergius on 05.07.2015.
 */
public class EratosthenesCSP {
    public static void main(String[] args) {
        One2OneChannelInt c = Channel.one2oneInt();
        new Parallel(
                new CSProcess[] {
                        new Primes(c.out()),
                        new PrinterInt(c.in(), "-->", "\n")
                }
        ).run();
    }
}
