package lesson12;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;

import static lesson12.AkkaUtils.*;

/**
 * Created by Sergius on 29.06.2015.
 */
public class Bucket extends UntypedActor {
    private Map data = new HashMap<>();
    @Override
    public void onReceive(Object msg) throws Exception {
        Object[] msgArr = (Object[]) msg;
        String command = (String) msgArr[0];
        String key = (String) msgArr[1];
        switch (command) {
            case "put": Object value = msgArr[2];
                data.put(key,value);
                break;
            case "remove": data.remove(key);
                break;
            case "get":
                ActorRef originalSender = (ActorRef) msgArr[2];
                Object[] response = msg("get/result", key, data.get(key), originalSender);
                getSender().tell(response, getSelf());
                break;
        }
    }
}
