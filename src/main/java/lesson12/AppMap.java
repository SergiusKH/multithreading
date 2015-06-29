package lesson12;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.IOException;

import static lesson12.AkkaUtils.msg;

/**
 * Created by Sergius on 29.06.2015.
 */
public class AppMap {

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef container = system.actorOf(Props.create(Container.class), "container");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

        container.tell(msg("put", "KeyA", "ValueA"), ActorRef.noSender());
        container.tell(msg("put", "KeyB", "ValueB"), ActorRef.noSender());
        container.tell(msg("put", "KeyC", "ValueC"), ActorRef.noSender());

        container.tell(msg("remove", "KeyB"), ActorRef.noSender());

        container.tell(msg("get", "KeyA"), callback);
        container.tell(msg("get", "KeyB"), callback);
        container.tell(msg("get", "KeyC"), callback);

        System.in.read();
        system.shutdown();
    }


}
