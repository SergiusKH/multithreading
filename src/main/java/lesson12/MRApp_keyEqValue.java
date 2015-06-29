package lesson12;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.IOException;
import java.util.Map;

import static akka.actor.ActorRef.*;
import static lesson12.AkkaUtils.*;

/**
 * Created by Sergius on 29.06.2015.
 */
public class MRApp_keyEqValue {

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef container = system.actorOf(Props.create(MRContainer.class), "container");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

        container.tell(msg("put", "keyA", "valueA"), noSender());
        container.tell(msg("put", "keyB", "valB"), noSender());
        container.tell(msg("put", "keyC", "valueC"), noSender());
        container.tell(msg("put", "keyD", "valD"), noSender());

        Mapper<Map.Entry<String, String>, Integer> mapper = new Mapper<Map.Entry<String, String>, Integer>() {
            @Override
            public Integer map(Map.Entry<String, String> entry  ) {
                return entry.getKey().length() == entry.getValue().length() ? 1 : 0;
            }
        };

        Reducer<Integer> reducer = new Reducer<Integer>() {
            @Override
            public Integer reduce(Integer left, Integer right) {
                return left + right;
            }
        };

        Integer initElem = 0;
        container.tell(msg("map-reduce", mapper, reducer, initElem), callback);
        System.in.read();
    }
}
