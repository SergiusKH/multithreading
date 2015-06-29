package lesson13;

import akka.actor.*;
import lesson12.Callback;

/**
 * Created by Sergius on 29.06.2015.
 */
public class Test03_actorSelection_alive {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ActorSelection selection = system.actorSelection("user/*");

        ActorRef callback0 = system.actorOf(Props.create(Callback.class), "callback-0");
        selection.tell("0!", ActorRef.noSender());

        callback0.tell(PoisonPill.getInstance(), ActorRef.noSender());
        selection.tell("1!", ActorRef.noSender());

        ActorRef callback1 = system.actorOf(Props.create(Callback.class), "callback-1");
        selection.tell("2!", ActorRef.noSender());

        system.shutdown();
    }

}
