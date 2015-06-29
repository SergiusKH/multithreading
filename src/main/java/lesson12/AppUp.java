package lesson12;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.Scanner;

/**
 * Created by Sergius on 28.06.2015.
 */
public class AppUp {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef worker = system.actorOf(Props.create(WorkUp.class), "worker");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if ("exit".equals(line)) {
                system.shutdown();
                return;
            }
            worker.tell(line, callback);
//            worker.tell(line, ActorRef.noSender()); // "null object" patterns
        }

    }


}
