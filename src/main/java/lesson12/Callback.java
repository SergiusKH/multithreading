package lesson12;

import akka.actor.UntypedActor;

import java.util.Arrays;

/**
 * Created by Sergius on 28.06.2015.
 */
public class Callback extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof Object[]) {
            System.out.println("result: " + Arrays.toString((Object[]) o));
        } else {
            System.out.println("result: " + o);
//            System.out.println("result: " + getSender() + "/" + o);

        }
    }
}
