package lesson12;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static lesson12.AkkaUtils.msg;

/**
 * Created by Sergius on 29.06.2015.
 */
public class MRBucket extends UntypedActor {
    private Map data = new HashMap<>();

    @Override
    public void onReceive(Object msg) throws Exception {
        Object[] msgArr = (Object[]) msg;
        String command = (String) msgArr[0];
        String key;
        switch (command) {
            case "put":
                key = (String) msgArr[1];
                String value = (String) msgArr[2];
                data.put(key,value);
                break;
            case "remove":
                key = (String) msgArr[1];
                data.remove(key);
                break;
            case "get":
                key = (String) msgArr[1];
                ActorRef originalSender = (ActorRef) msgArr[2];
                Object[] response = msg("get/result", key, data.get(key), originalSender);
                getSender().tell(response, getSelf());
                break;
            case "map-reduce":
                Object id = msgArr[1];
                Mapper mapper = (Mapper) msgArr[2];
                Reducer reducer = (Reducer) msgArr[3];
                Object initElem = msgArr[4];

                Object result = initElem;
                for (Map.Entry entry : (Set<Map.Entry>) data.entrySet()) {
                    result = reducer.reduce(result, mapper.map(entry));
                }
                Object[] respons = {"map-reduce/result", id, result};
                getSender().tell(respons, getSelf());
                break;
        }
    }
}
