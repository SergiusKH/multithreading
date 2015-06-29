package lesson12;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static akka.actor.ActorRef.noSender;
import static akka.actor.Props.create;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static lesson12.AkkaUtils.msg;

/**
 * Created by Sergius on 29.06.2015.
 */
public class MRApp_getAll {
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef container = system.actorOf(create(MRContainer.class), "container");
        ActorRef callback = system.actorOf(create(Callback.class), "callback");

        container.tell(msg("put", "keyA", "valueA"), noSender());
        container.tell(msg("put", "keyB", "valB"), noSender());
        container.tell(msg("put", "keyC", "valueC"), noSender());
        container.tell(msg("put", "keyD", "valD"), noSender());

        Mapper<Map.Entry, Map> mapper = entry -> singletonMap(entry.getKey(), entry.getValue());

        Reducer<Map> reducer = (left, right) -> {
            HashMap result = new HashMap(left);
            result.putAll(result);
            return result;
        };

        Map<String, String> initElem = emptyMap();
        container.tell(msg("map-reduce", mapper, reducer, initElem), callback);
        System.in.read();
    }
}
