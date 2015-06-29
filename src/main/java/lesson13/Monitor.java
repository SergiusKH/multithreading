package lesson13;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;

/**
 * Created by Sergius on 29.06.2015.
 */
public class Monitor extends UntypedActor {
    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof ActorRef) {
            ActorRef actor = (ActorRef) msg;
            getContext().watch(actor);
        }
        if (msg instanceof Terminated) {
            System.out.println("TEMINATED: " + ((Terminated) msg).getActor());
        } else {
            unhandled(msg);
        }
    }
}
