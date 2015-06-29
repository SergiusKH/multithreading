package lesson12;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.SmallestMailboxPool;

import java.util.Scanner;

/**
 * Created by Sergius on 28.06.2015.
 */
public class AppUpRouted {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef routed = system.actorOf(new SmallestMailboxPool(5)
                .props(Props.create(WorkerUpRouted.class)), "workers");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if ("exit".equals(line)) {
                system.shutdown();
                return;
            }
            routed.tell(line, callback);
        }

    }
}
