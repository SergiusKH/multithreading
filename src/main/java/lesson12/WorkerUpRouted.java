package lesson12;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Created by Sergius on 28.06.2015.
 */
public class WorkerUpRouted extends UntypedActor {
    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            String response =((String) msg).toUpperCase();
            ActorRef sender = getSender();
            sender.tell(response, getSelf());
        } else {
            unhandled(msg);
        }
    }
}
