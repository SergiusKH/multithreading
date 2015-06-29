package lesson12;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by Sergius on 29.06.2015.
 */
public class SumApp {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");

        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");
        ActorRef kernel = system.actorOf(Props.create(SumKernel.class, callback), "sumKernel");

        kernel.tell(new int[] {0, 10}, ActorRef.noSender());
    }
}
