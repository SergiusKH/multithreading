package lesson13;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import lesson12.Callback;

/**
 * Created by Sergius on 29.06.2015.
 */
public class Test03_actorSelection_many {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        system.actorOf(Props.create(Callback.class), "callback-0");
        system.actorOf(Props.create(Callback.class), "callback-1");
        system.actorOf(Props.create(Callback.class), "callback-2");

        ActorSelection selection = system.actorSelection("user/*");
        selection.tell("Hello!", ActorRef.noSender());

        system.shutdown();
    }


}
