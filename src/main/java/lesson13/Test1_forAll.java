package lesson13;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import static akka.pattern.Patterns.ask;

/**
 * Created by Sergius on 29.06.2015.
 */
public class Test1_forAll {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef supervisor = system.actorOf(Props.create(SupervisorForAll.class), "supervisor");

        ActorRef childA = (ActorRef) blockingAsk(supervisor,Props.create(Child.class));
        ActorRef childB = (ActorRef) blockingAsk(supervisor,Props.create(Child.class));

        {
            childA.tell(42, ActorRef.noSender());
            Integer state = (Integer) blockingAsk(childA, "get");
            System.out.println("state = " + state);
        }
        {
            childB.tell(43, ActorRef.noSender());
            Integer state = (Integer) blockingAsk(childB, "get");
            System.out.println("state = " + state);
        }
//        {
//            child.tell(new ArithmeticException(), ActorRef.noSender());
//            Integer state = (Integer) blockingAsk(child, "get");
//            System.out.println("state = " + state);
//        }
        {
            childA.tell(new NullPointerException(), ActorRef.noSender());
            Integer state = (Integer) blockingAsk(childA, "get");
            System.out.println("state = " + state);
        }
        {
            Integer state = (Integer) blockingAsk(childB, "get");
            System.out.println("state = " + state);
        }
//        {
//            child.tell(new Exception(), ActorRef.noSender());
//            child.tell(new IllegalArgumentException(), ActorRef.noSender());
//            Integer state = (Integer) blockingAsk(child, "get");
//            System.out.println("state = " + state);
//        }

        system.shutdown();
    }

    public static Object blockingAsk(ActorRef actor, Object msg) throws Exception{
        Timeout timeout = new Timeout(Duration.create(5, "seconds"));
        Future<Object> future = ask(actor, msg, timeout);
        return Await.result(future, timeout.duration());
    }
}
